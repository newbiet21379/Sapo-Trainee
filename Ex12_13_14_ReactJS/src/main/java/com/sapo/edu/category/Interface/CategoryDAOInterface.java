package com.sapo.edu.category.Interface;

import com.sapo.edu.category.model.Category;


import java.util.List;

public interface CategoryDAOInterface {
    List<Category> fillAllCategory();

    List<Category> fillAllCategoryPaging(int page
            ,String query
            ,int pageSize) throws Exception;

    List<Category> searchByName(String name);

    Category getById(int id);

    int getTotalCategory(String query);

    int insertCategory(Category category) throws Exception;

    int updateCategory(Category category) throws Exception;

    int deleteCategory(int id);

    int getTotalCategoryById(int id);
}
