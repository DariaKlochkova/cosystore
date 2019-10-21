package com.store.cosystore.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String article;
    private String name;
    private int price;
    private int promotionPrice;
    private String generalInf;
    private String description;
    private int count;
    private float height;
    private float width;
    private float depth;
    private Color color;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "image", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> images;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    List<Value> values;

    @ElementCollection(targetClass = Room.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "room", joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    private Set<Room> rooms;

    //private Set<Color> otherColors;

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "action_id")
    //private Action action;


    public Product(Category category, String article, String name, int price, int count, String generalInf, String description, Color color, int height, int width, int depth, List<String> images, Set<Room> rooms) {
        this.category = category;
        this.article = article;
        this.name = name;
        this.price = price;
        this.count = count;
        this.generalInf = generalInf;
        this.description = description;
        this.color = color;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.images = images;
        this.rooms = rooms;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}
