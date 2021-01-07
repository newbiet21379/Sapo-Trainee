package com.sapo.edu.category.service;

import com.sapo.edu.category.Interface.CategoryDAOInterface;
import com.sapo.edu.category.Interface.CategoryServiceInterface;
import com.sapo.edu.category.validate.exception.CategoryNotFoundException;
import com.sapo.edu.category.model.Category;
import com.sapo.edu.product.interfaceProduct.ProductDAOInterface;
import com.sapo.edu.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CategoryService")
public class CategoryService implements CategoryServiceInterface {
    @Autowired
    ProductDAOInterface productDAOInterface;

    @Autowired
    CategoryDAOInterface categoryDAOInterface;

    /**
     * delete Category theo id có Transactional
     *
     * @return messege báo lỗi
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteCategory(String id) throws Exception {
        String result = "";
        try {
            int id_raw = Integer.parseInt(id);
            Category old = categoryDAOInterface.getById(id_raw);
            List<Product> productList = productDAOInterface.searchByCategory(id_raw);
            int product_row = 0;
            int category_row = 0;
            if (productDAOInterface.searchByCategory(id_raw).size() > 0) {
                product_row = productDAOInterface.deleteProductByCategory(id_raw);
            }
            category_row = categoryDAOInterface.deleteCategory(id_raw);
            if (category_row == 0) {
                throw new Exception("There are no Category with id : " + id);
            }
            int deleted_row = product_row + category_row;
            if (deleted_row > 0) {
                result = "\nYou have Delete " + deleted_row + " with Category id= " + id +
                        "\n" + "Deleted Category:" + old.toString() +
                        "\nDeleted Product:";
                for (Product i : productList) {
                    result += "\n" + i.toString();
                }
            }

        } catch (NumberFormatException ignored) {
            throw new Exception("You can't input text into id to delete, must be number");
        }

        return result;
    }

    /**
     * Trả về List Category
     *
     * @return
     */
    @Override
    public List<Category> fillAllCategory() {
        return categoryDAOInterface.fillAllCategory();
    }

    /**
     * Tìm tất cả các Category theo name
     *
     * @return
     */
    @Override
    public List<Category> searchByName(String name) {

        return categoryDAOInterface.searchByName(name);
    }

    /**
     * Tìm Category theo id
     *
     * @return
     */
    @Override
    public Category getById(String id) throws Exception {
        int id_raw = 0;
        Category category;
        try {
            id_raw = Integer.parseInt(id);
            try {
                category = categoryDAOInterface.getById(id_raw);
            } catch (DataAccessException ex) {
                throw new CategoryNotFoundException(id_raw);
            }
        } catch (NumberFormatException ex) {
            throw new Exception("Cannot input text to search id");
        }
        return category;
    }

    /**
     * Tìm tổng tất cả các Category
     *
     * @return
     */
    @Override
    public int getTotalCategory() {
        return categoryDAOInterface.getTotalCategory();
    }

    /**
     * Add thêm Category theo nhập vào
     *
     * @return
     */
    @Override
    public String insertCategory(Category category) throws Exception {
        String result = "Add Category failed";
        if (category != null)
            result = "Add Category successfully ";
        result += "\nYou have switchFunction " + categoryDAOInterface.insertCategory(category) + " Category";
        return result;
    }

    /**
     * Update Category theo nhập vào và id
     *
     * @return
     */
    @Override
    public String updateCategory(Category category, String id) throws Exception {
        String result = "";
        try {
            category.setId(Integer.parseInt(id));
            Category old = categoryDAOInterface.getById(Integer.parseInt(id));
            result = "Edit Product failed";
            int row = categoryDAOInterface.updateCategory(category);
            if (row > 0)
                result = "\nYou have Update " + row + " Category on id= " + id +
                        "\n" + "Old Category:" + old.toString() +
                        "\nNew Category:" + category.toString();
        } catch (NumberFormatException ignored) {
            return "You can't input text into id to search";
        }
        return result;
    }

    /**
     * Tìm xem có tồn tại category
     *
     * @return int
     * 0 là ko tồn tại
     * 1 là có tồn tại
     */
    @Override
    public int getTotalCategoryById(int id) {
        return categoryDAOInterface.getTotalCategoryById(id);
    }
}
