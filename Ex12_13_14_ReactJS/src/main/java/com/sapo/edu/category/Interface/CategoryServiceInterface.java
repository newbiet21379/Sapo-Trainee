package com.sapo.edu.category.Interface;

import com.sapo.edu.category.model.Category;
import com.sapo.edu.category.model.ResponseForm;

import java.util.List;

public interface CategoryServiceInterface {
    String deleteCategory(String id) throws Exception;

    List<Category> fillAllCategory();

    ResponseForm fillAllCategoryPaging(int page
                                        , String query
            , int pageSize) throws Exception;

    List<Category> searchByName(String name);

    Category getById(String id) throws Exception;

    int getTotalCategory(String query);

    String insertCategory(Category category) throws Exception;

    String updateCategory(Category category, String category_id) throws Exception;

    int getTotalCategoryById(int id);
}
