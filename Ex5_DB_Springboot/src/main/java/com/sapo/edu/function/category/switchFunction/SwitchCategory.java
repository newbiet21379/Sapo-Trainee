package com.sapo.edu.function.category.switchFunction;

import com.sapo.edu.category.Interface.CategoryServiceInterface;
import com.sapo.edu.category.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SwitchCategory {
    @Autowired
    CategoryServiceInterface categoryServiceInterface;

    public String insertCategory(Category category) throws Exception {
        return categoryServiceInterface.insertCategory(category);
    }
    /**
     * Update Hạng mục
     *
     * @param category
     * @param id
     * @return
     * @throws Exception khi
     */

    public String updateCategory(Category category, String id) throws Exception {
        return categoryServiceInterface.updateCategory(category, id);
    }

    /**
     * Xóa Hạng mục và tất cả Product trong đó nếu có
     *
     * @param id
     * @return
     * @throws Exception
     */

    public String deleteCategory(String id) throws Exception {
        return categoryServiceInterface.deleteCategory(id);
    }

    /**
     * Lấy ra một hạng mục trong database theo id
     *
     * @param id
     * @return Category
     */
    public Category getbyId(String id) throws Exception {
        return categoryServiceInterface.getById(id);
    }
}
