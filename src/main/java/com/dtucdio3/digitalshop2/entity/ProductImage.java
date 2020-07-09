package com.dtucdio3.digitalshop2.entity;

import org.springframework.web.multipart.MultipartFile;

public class ProductImage {
    private int id;
    private String name;
    private int quantity;
    private long price;
    private String description;
    private Category category;
    private MultipartFile[] fileData;

    public ProductImage() {
    }

    public ProductImage(int id, String name, int quantity, long price, String description, Category category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.category = category;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public MultipartFile[] getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile[] fileData) {
        this.fileData = fileData;
    }
}
