package com.store.cosystore.service;

import com.store.cosystore.domain.Category;
import com.store.cosystore.domain.CategoryGroup;
import com.store.cosystore.domain.Property;
import com.store.cosystore.repos.CategoryGroupRepo;
import com.store.cosystore.repos.CategoryRepo;
import com.store.cosystore.repos.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    public void editGroup(String oldName, String newName){
        CategoryGroup cg = categoryGroupRepo.findByName(oldName);
        cg.setName(newName);
        categoryGroupRepo.save(cg);
    }

    public void deleteGroup(String name){
        CategoryGroup cg = categoryGroupRepo.findByName(name);
        if(cg.getCategories().size() == 0)
            categoryGroupRepo.delete(cg);
    }

    public void addGroup(String name){
        categoryGroupRepo.save(new CategoryGroup(name));
    }

    public void editCategory(String oldName, String newName){
        Category c = categoryRepo.findByName(oldName);
        c.setName(newName);
        categoryRepo.save(c);
    }

    public void deleteCategory(String name){
        Category c = categoryRepo.findByName(name);
        CategoryGroup cg = categoryGroupRepo.findById(c.getCategoryGroup().getId());
        if(c.getProducts().size() == 0) {
            cg.getCategories().remove(c);
            categoryRepo.delete(c);
        }
    }

    public void addCategory(String groupName, String categoryName){
        CategoryGroup cg = categoryGroupRepo.findByName(groupName);
        cg.getCategories().add(new Category(categoryName, cg));
        categoryGroupRepo.save(cg);
    }

    public void saveProperties(Category category, List<String> propertyName, List<String> propertyValues){
        category.getProperties().clear();
        propertyRepo.deleteAll(propertyRepo.findByCategory_Id(category.getId()));
        for(int i = 0; i < propertyName.size(); i++){
            Property p = new Property(propertyName.get(i), Arrays.asList(propertyValues.get(i).split(", ")), category);
            propertyRepo.save(p);

            category.getProperties().add(p);
        }
        categoryRepo.save(category);
    }
}
