package com.store.cosystore.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "option", joinColumns = @JoinColumn(name = "property_id"))
    private List<String> possibleValues;

    public Property(String name, List<String> possibleValues, Category category) {
        this.name = name;
        this.possibleValues = possibleValues;
        this.category = category;
    }

    public Property() {
    }

    public String posValToString(){
        return String.join(", ", possibleValues);
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(List<String> possibleValues) {
        this.possibleValues = possibleValues;
    }
}
