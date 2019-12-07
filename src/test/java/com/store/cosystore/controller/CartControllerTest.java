package com.store.cosystore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.store.cosystore.domain.Order;
import com.store.cosystore.repos.CartRepo;
import com.store.cosystore.repos.OrderRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/cart-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/cart-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails(value = "ivan")
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private OrderRepo orderRepo;

    @Test
    public void cartView() throws Exception {
        mockMvc.perform(get("/cart"))
                .andDo(print())
                .andExpect(xpath("//*[@id='cart-content']/div[2]/div[2]/a/span").string("Пакс"));
    }

    @Test
    public void addProductToCart() throws Exception {
        mockMvc.perform(post("/cart/2").with(csrf()))
                .andDo(print());
        Assert.assertNotNull(cartRepo.findByUserIdAndProductVersionId(1, 2));
    }

    @Test
    public void editPositionCount() throws Exception {
        mockMvc.perform(put("/cart/1").with(csrf()).param("count", "3"))
                .andDo(print());
        Assert.assertNotNull(cartRepo.findByUserIdAndProductVersionId(1, 1));
        Assert.assertEquals(3, cartRepo.findByUserIdAndProductVersionId(1, 1).getCount());
    }

    @Test
    public void addOrder() throws Exception {
        Order order = new Order();
        order.setId(1);
        order.setEmail("dasha.klochkova.00@mail.ru");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(order);

        mockMvc.perform(post("/cart").contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .with(csrf().asHeader()))
                .andDo(print());
        Assert.assertNotNull(orderRepo.findById(1));
    }

    @Test
    public void deletePosition() throws Exception {
        Assert.assertNotNull(cartRepo.findByUserIdAndProductVersionId(1, 1));
        mockMvc.perform(delete("/cart/1").with(csrf()))
                .andDo(print());
        Assert.assertNull(cartRepo.findByUserIdAndProductVersionId(1, 1));
    }

}