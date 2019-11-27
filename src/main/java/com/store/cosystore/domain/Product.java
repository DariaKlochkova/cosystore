package com.store.cosystore.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int price;
    private int promotionPrice;
    private String generalInf;
    private String description;
    private float height;
    private float width;
    private float depth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "category_id", insertable = false, updatable = false)
    private int categoryId;

    @OneToMany(mappedBy = "product")
    private List<Value> values;

    @ElementCollection(targetClass = Room.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "room", joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    private Set<Room> rooms;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<ProductVersion> productVersions;

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "action_id")
    //private Action action;


    public Product() {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(int promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getGeneralInf() {
        return generalInf;
    }

    public void setGeneralInf(String generalInf) {
        this.generalInf = generalInf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public Set<ProductVersion> getProductVersions() {
        return productVersions;
    }

    public void setProductVersions(Set<ProductVersion> productVersions) {
        this.productVersions = productVersions;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
