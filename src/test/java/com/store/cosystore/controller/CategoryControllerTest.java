package com.store.cosystore.controller;

import com.store.cosystore.repos.CategoryGroupRepo;
import com.store.cosystore.repos.CategoryRepo;
import com.store.cosystore.repos.PropertyRepo;
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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/admin-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/admin-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails(value = "admin")
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryGroupRepo categoryGroupRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private PropertyRepo propertyRepo;

    @Test
    void categories() throws Exception {
        mockMvc.perform(get("/admin/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Обеденные столы")));
    }

    @Test
    void editGroup() throws Exception {
        mockMvc.perform(put("/admin/categories/group").with(csrf())
                .param("oldGroupName", "Столы")
                .param("editGroupName", "Стулья"))
                .andDo(print());
        Assert.assertEquals("Стулья", categoryGroupRepo.findById(1).getName());
    }

    @Test
    void addGroup() throws Exception {
        mockMvc.perform(post("/admin/categories/group").with(csrf())
                .param("newGroupName", "Шкафы"))
                .andDo(print());
        Assert.assertNotNull(categoryGroupRepo.findByName("Шкафы"));
    }

    @Test
    void deleteGroup() throws Exception {
        Assert.assertNotNull(categoryGroupRepo.findById(2));
        mockMvc.perform(delete("/admin/categories/group").with(csrf())
                .param("groupName", "Диваны"))
                .andDo(print());
        Assert.assertNull(categoryGroupRepo.findById(2));
    }

    @Test
    void editCategory() throws Exception {
        mockMvc.perform(put("/admin/categories").with(csrf())
                .param("oldCategoryName", "Обеденные столы")
                .param("editCategoryName", "Столы обеденные"))
                .andDo(print());
        Assert.assertEquals("Столы обеденные", categoryRepo.findById(1).getName());
    }

    @Test
    void addCategory() throws Exception {
        mockMvc.perform(post("/admin/categories").with(csrf())
                .param("groupName", "Столы")
                .param("newCategoryName", "Письменные столы"))
                .andDo(print());
        Assert.assertNotNull(categoryRepo.findByName("Письменные столы"));
    }

    @Test
    void deleteCategory() throws Exception {
        Assert.assertNotNull(categoryRepo.findById(2));
        mockMvc.perform(delete("/admin/categories").with(csrf())
                .param("categoryName", "Журнальные столики"))
                .andDo(print());
        Assert.assertNull(categoryRepo.findById(2));
    }

    @Test
    void saveProperties() throws Exception {
        String category = "{\"id\":2, \"properties\":[{\"name\":\"Колёсики\", \"possibleValues\":[\"Есть\",\"Нет\"]}]}";
        mockMvc.perform(put("/admin/categories/properties").contentType(MediaType.APPLICATION_JSON)
                .content(category)
                .with(csrf().asHeader()))
                .andDo(print());
        Assert.assertEquals(1, categoryRepo.findById(2).getProperties().size());
    }
}