package com.store.cosystore.service;

import com.store.cosystore.domain.*;
import com.store.cosystore.repos.OrderPositionRepo;
import com.store.cosystore.repos.OrderRepo;
import com.store.cosystore.repos.ProductVersionRepo;
import com.store.cosystore.session.SessionCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderPositionRepo orderPositionRepo;
    @Autowired
    ProductVersionRepo productVersionRepo;
    @Autowired
    CartService cartService;
    @Autowired
    private MailSender mailSender;

    public String addOrder(Order order, User user, SessionCart sessionCart) {
        Set<Cart> cart;
        if (user != null){
            order.setUser(user);
            cart = cartService.cart(user.getId());
        } else {
            cart = cartService.sessionCart(sessionCart);
        }

        Set<OrderPosition> orderPositions = new HashSet<>();

        for (Cart c : cart){
            ProductVersion productVersion = c.getProductVersion();
            productVersion.decreaseCount(c.getCount());
            productVersionRepo.save(productVersion);
            orderPositions.add(new OrderPosition(productVersion, c.getCount()));
        }

        order.setOrderPositions(orderPositions);
        Order ord = orderRepo.save(order);
        try {
            mailSender.sendWithCheck(ord);
        } catch (Exception e) {
            e.printStackTrace();
            return "Заказ принят. Не удалось отправить чек на почту, попробуйте снова в личном кабинете";
        }
        return "Заказ принят. Чек отправлен вам на почту";
    }

    public String sendCheck(int orderId){
        Order order = orderRepo.findById(orderId);
        try {
            mailSender.sendWithCheck(order);
        } catch (Exception e) {
            e.printStackTrace();
            return "Не удалось отправить чек на почту";
        }
        return "Чек отправлен вам на почту";
    }

    public LinkedHashSet<Order> getOrders(){
        return orderRepo.findAllByOrderByDateDesc();
    }

    public Object getOrdersByStatus(Integer status) {
        return orderRepo.findByStatusOrderByDateDesc(Status.getByCode(status));
    }

    public Order getOrderById(int orderId) {
        return orderRepo.findById(orderId);
    }

    public LinkedHashSet<Order> getUsersOrders(int userId) {
        return orderRepo.findByUserIdOrderByDateDesc(userId);
    }

    public String changeOrderStatus(int orderId, int newStatus) {
        Order order = orderRepo.findById(orderId);
        order.setStatus(Status.getByCode(newStatus));
        orderRepo.save(order);
        return order.getStatus().getName();
    }

    public void cancelOrder(int orderId) {
        Order order = orderRepo.findById(orderId);
        order.setStatus(Status.CANCELED);
        orderRepo.save(order);
    }

    public String denyOrder(int orderId, String message) {
        Order order = orderRepo.findById(orderId);
        try {
            mailSender.send(order.getEmail(), "Отказ от заказа №" + orderId, message);
            changeOrderStatus(orderId, Status.DENIED.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            return "Не удалось отправить сообщение пользователю";
        }
        return "Заказ отменён";
    }

    public int getNewCount() {
        return orderRepo.findByStatus(Status.NEW).size();
    }
}
