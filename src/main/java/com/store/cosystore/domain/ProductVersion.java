package com.store.cosystore.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class ProductVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String article;
    private String versionName;
    private int count;
    private Color color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "image", joinColumns = @JoinColumn(name = "product_version_id"))
    private List<String> images;

    public ProductVersion() {
    }

    public String getMainImg(){
        return images.get(0);
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
