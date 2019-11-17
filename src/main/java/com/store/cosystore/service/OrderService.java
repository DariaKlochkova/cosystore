package com.store.cosystore.service;

import com.store.cosystore.domain.*;
import com.store.cosystore.repos.OrderPositionRepo;
import com.store.cosystore.repos.OrderRepo;
import com.store.cosystore.repos.ProductVersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderPositionRepo orderPositionRepo;
    @Autowired
    ProductVersionRepo productVersionRepo;

    public Order addOrder(Order order, User user){
        if (user != null)
            order.setUser(user);
        Order ord = orderRepo.save(order);
        ProductVersion productVersion;
        for (OrderPosition op : order.getOrderPositions()){
            op.getId().setOrder(ord.getId());
            op.setOrder(ord);
            productVersion = productVersionRepo.findById(op.getId().getProductVersion());
            op.setProductVersion(productVersion);
            productVersion.decreaseCount(op.getCount());
            productVersionRepo.save(productVersion);
            orderPositionRepo.save(op);
        }
        return orderRepo.findById(ord.getId());
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
