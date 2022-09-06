package com.store.cosystore.controller;

import com.store.cosystore.domain.Status;
import com.store.cosystore.repos.OrderRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/orders-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/orders-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("delivery")
class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderRepo orderRepo;

    @Test
    void orders() throws Exception {
        this.mockMvc.perform(get("/orders"))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/orders?status=0"))
                .andExpect(status().isOk());
    }

    @Test
    void orderView() throws Exception {
        this.mockMvc.perform(get("/orders/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void changeOrderStatus() throws Exception {
        this.mockMvc.perform(put("/orders/1").with(csrf())
                .param("newStatus", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Комплектуется")));
        Assert.assertEquals(1, orderRepo.findById(1).getStatus().getCode());
    }

    @Test
    void denyOrder() throws Exception {
        this.mockMvc.perform(post("/orders/1").with(csrf())
                .param("message", "Не получится"))
                .andDo(print())
                .andExpect(status().isOk());
        Assert.assertEquals(Status.DENIED, orderRepo.findById(1).getStatus());
    }
}