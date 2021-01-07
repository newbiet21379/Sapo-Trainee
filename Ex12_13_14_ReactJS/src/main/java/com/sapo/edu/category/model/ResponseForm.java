package com.sapo.edu.category.model;

import java.util.List;

public class ResponseForm {

    List<Category> categoryList;
    int totalPages;

    public ResponseForm(List<Category> categoryList, int totalPages) {
        this.categoryList = categoryList;
        this.totalPages = totalPages;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
