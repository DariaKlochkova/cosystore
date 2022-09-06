package com.store.cosystore.service;

import com.store.cosystore.domain.Category;
import com.store.cosystore.domain.CategoryGroup;
import com.store.cosystore.domain.Color;
import com.store.cosystore.domain.Property;
import com.store.cosystore.repos.CategoryGroupRepo;
import com.store.cosystore.repos.CategoryRepo;
import com.store.cosystore.repos.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.hibernate.cache.spi.support.SimpleTimestamper.next;

@Service
public class CategoryService {

    @Autowired
    private CategoryGroupRepo categoryGroupRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private PropertyRepo propertyRepo;

    public Iterable<CategoryGroup> categoryGroupList(){
        return categoryGroupRepo.findAll();
    }

    public Category getCategory(int id){
        return categoryRepo.findById(id);
    }

    public Category getAnyCategory(){
        Iterator<Category> iterator = categoryRepo.findAll().iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    public Iterable<Property> propertyList(int categoryId){
        return propertyRepo.findByCategoryId(categoryId);
    }

    public void editGroup(String oldName, String newName){
        CategoryGroup cg = categoryGroupRepo.findByName(oldName);
        cg.setName(newName);
        categoryGroupRepo.save(cg);
    }

    public String deleteGroup(String name){
        CategoryGroup cg = categoryGroupRepo.findByName(name);
        if(cg.getCategories().size() == 0) {
            categoryGroupRepo.delete(cg);
            return "Группа \""+name+"\" удалена";
        } else
            return "Нельзя удалить группу, так как в ней есть категории";
    }

    public void addGroup(String name){
        categoryGroupRepo.save(new CategoryGroup(name));
    }

    public void editCategory(String oldName, String newName){
        Category c = categoryRepo.findByName(oldName);
        c.setName(newName);
        categoryRepo.save(c);
    }

    public String deleteCategory(String name){
        Category c = categoryRepo.findByName(name);
        CategoryGroup cg = categoryGroupRepo.findById(c.getCategoryGroup().getId());
        if(c.getProducts().size() == 0) {
            cg.getCategories().remove(c);
            categoryRepo.delete(c);
            return "Категория \""+name+"\" удалена";
        } else
            return "Нельзя удалить категорию, так как в ней есть товары";
    }

    public void addCategory(String groupName, String categoryName){
        categoryRepo.save(new Category(categoryName, categoryGroupRepo.findByName(groupName)));
    }

    public void saveProperties(Category category){
        Category c = categoryRepo.findById(category.getId());
        propertyRepo.deleteAll(propertyRepo.findByCategoryId(c.getId()));
        for (Property p : category.getProperties()){
            p.setCategory(c);
            propertyRepo.save(p);
        }
    }

    public Set<Color> getColorsOfCategory(int categoryId) {
        return new LinkedHashSet<>(categoryRepo.findById(categoryId).getColors());
    }
}
