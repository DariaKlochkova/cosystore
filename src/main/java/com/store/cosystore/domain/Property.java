package com.store.cosystore.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    Set<Value> values;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "option", joinColumns = @JoinColumn(name = "property_id"))
    private Set<String> possibleValues;

    public Property(String name, String possibleValues, Category category) {
        this.name = name;
        Set<String> pv = new HashSet<>(Arrays.asList(possibleValues.split(", ")));
        this.possibleValues = pv;
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

    public Set<Value> getValues() {
        return values;
    }

    public void setValues(Set<Value> values) {
        this.values = values;
    }

    public Set<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(Set<String> possibleValues) {
        this.possibleValues = possibleValues;
    }
}
