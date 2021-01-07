package com.sapo.edu.category.model;

import com.sapo.edu.product.model.Product;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private String create_date;
    private String fix_date;
    private String description;
    private List<Product> productList;

    public Category(int id, String name, String create_date, String fix_date, String description) {
        this.id = id;
        this.name = name;
        this.create_date = create_date;
        this.fix_date = fix_date;
        this.description = description;
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

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getFix_date() {
        return fix_date;
    }

    public void setFix_date(String fix_date) {
        this.fix_date = fix_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", create_date='" + create_date + '\'' +
                ", fix_date='" + fix_date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
