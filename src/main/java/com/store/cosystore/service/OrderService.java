package com.store.cosystore.service;

import com.itextpdf.text.DocumentException;
import com.store.cosystore.domain.*;
import com.store.cosystore.repos.OrderPositionRepo;
import com.store.cosystore.repos.OrderRepo;
import com.store.cosystore.repos.ProductVersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.LinkedHashSet;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderPositionRepo orderPositionRepo;
    @Autowired
    ProductVersionRepo productVersionRepo;
    @Autowired
    private MailSender mailSender;

    public Order addOrder(Order order, User user) {
        if (user != null)
            order.setUser(user);
        Order ord = orderRepo.save(order);
        for (OrderPosition op : order.getOrderPositions()){
            op.getId()
                    .setOrder(ord.getId());
            op.setOrder(ord);
            ProductVersion productVersion = productVersionRepo.findById(op.getId().getProductVersion());
            op.setProductVersion(productVersion);
            productVersion.decreaseCount(op.getCount());
            productVersionRepo.save(productVersion);
            orderPositionRepo.save(op);
        }
        ord = orderRepo.findById(ord.getId());
        try {
            mailSender.sendWithCheck(ord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ord;
    }

    public LinkedHashSet<Order> getOrders(){
        return orderRepo.findAllByOrderByDateDesc();
    }

    public Order getOrderById(int orderId) {
        return orderRepo.findById(orderId);
    }

    public String changeOrderStatus(int orderId, int newStatus) {
        Order order = orderRepo.findById(orderId);
        if(newStatus > order.getStatus().getCode() && newStatus < Status.COMPLETED.getCode()){
            order.setStatus(Status.getByCode(newStatus));
            orderRepo.save(order);
            return order.getStatus().getName();
        }
        return "Неверный код статуса";
    }
}
