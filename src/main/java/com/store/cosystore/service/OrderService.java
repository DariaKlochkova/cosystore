package com.store.cosystore.service;

import com.store.cosystore.domain.Order;
import com.store.cosystore.domain.OrderPosition;
import com.store.cosystore.repos.OrderPositionRepo;
import com.store.cosystore.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderPositionRepo orderPositionRepo;

    public void addOrder(Order order){
        Order ord = orderRepo.save(order);
        for (OrderPosition op : order.getOrderPositions()){
            op.getId().setOrder(ord.getId());
            orderPositionRepo.save(op);
        }
        orderRepo.save(order);
    }
}
