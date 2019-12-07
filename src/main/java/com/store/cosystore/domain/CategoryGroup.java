package com.store.cosystore.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class CategoryGroup {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(mappedBy = "categoryGroup", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Category> categories;

    public CategoryGroup(String name) {
        this.name = name;
    }

    public CategoryGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
