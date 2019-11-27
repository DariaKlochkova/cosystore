package com.store.cosystore.service;

import com.itextpdf.text.DocumentException;
import com.store.cosystore.domain.Order;
import com.store.cosystore.domain.OrderPosition;
import com.store.cosystore.domain.User;
import com.store.cosystore.domain.keys.OrderPositionKey;
import com.store.cosystore.repos.OrderPositionRepo;
import com.store.cosystore.repos.OrderRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepo orderRepo;

    @MockBean
    private OrderPositionRepo orderPositionRepo;

    @MockBean
    private MailSender mailSender;

    @Test
    void addOrder() throws DocumentException, MessagingException, IOException {

//        Order order = new Order();
//        OrderPosition orderPosition = new OrderPosition();
//        orderPosition.setId(new OrderPositionKey());
//        order.setOrderPositions(Collections.singleton(orderPosition));
//        User user = new User();
//        Order ord = orderService.addOrder(order, user);
//
//        Assert.assertNotNull(ord.getUser());
//        Assert.assertFalse(ord.getOrderPositions().isEmpty());
//
//        Mockito.verify(orderRepo, Mockito.times(1)).save(order);
//        Mockito.verify(orderPositionRepo, Mockito.times(order.getOrderPositions().size())).save(orderPosition);
//        Mockito.verify(mailSender, Mockito.times(1)).sendWithCheck(ord);
    }
}