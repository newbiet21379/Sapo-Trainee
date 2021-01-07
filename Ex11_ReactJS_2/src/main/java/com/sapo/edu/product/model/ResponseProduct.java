package com.sapo.edu.product.model;


import java.util.List;


public class ResponseProduct{
    List<Product> product;
    int totalPages;

    public ResponseProduct(List<Product> product, int totalPages) {
        this.product = product;
        this.totalPages = totalPages;
    }
    public List<Product> getProduct() {
        return product;
    }
    public void setProduct(List<Product> product) {
        this.product = product;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getTotalPages() {
        return totalPages;
    }

}