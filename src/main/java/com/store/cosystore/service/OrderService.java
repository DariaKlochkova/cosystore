package com.store.cosystore.service;

import com.store.cosystore.domain.Order;
import com.store.cosystore.domain.OrderPosition;
import com.store.cosystore.domain.User;
import com.store.cosystore.repos.OrderPositionRepo;
import com.store.cosystore.repos.OrderRepo;
import com.store.cosystore.repos.ProductVersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for (OrderPosition op : order.getOrderPositions()){
            op.getId().setOrder(ord.getId());
            op.setOrder(ord);
            op.setProductVersion(productVersionRepo.findById(op.getId().getProductVersion()));
            orderPositionRepo.save(op);
        }
        return orderRepo.findById(ord.getId());
    }
}
