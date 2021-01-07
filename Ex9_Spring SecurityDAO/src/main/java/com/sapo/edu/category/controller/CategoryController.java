package com.sapo.edu.category.controller;

import com.sapo.edu.category.Interface.CategoryServiceInterface;
import com.sapo.edu.category.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryServiceInterface categoryServiceInterface;

    /**
     * lấy tất cả các hạng mục
     *
     * @return
     */
    @GetMapping("/admin/category/")
    //ROLE_
    @PreAuthorize("hasRole('Read_Only') or hasRole('Full')")
    public List<Category> fillCategory() {
        return categoryServiceInterface.fillAllCategory();
    }

    /**
     * Thêm một hạng mục vào database
     *
     * @param category
     * @return
     */
    //TODO Sử dụng khi nhét giá trị vào Body ( Object) @ RequestBody
    @PostMapping("/admin/category/")
    @PreAuthorize("hasRole('Full')")
    public String insertCategory(@RequestBody Category category) throws Exception {
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
    @PutMapping("/admin/category/{id}")
    @PreAuthorize("hasRole('Full')")
    public String updateCategory(@RequestBody Category category, @PathVariable String id) throws Exception {
        return categoryServiceInterface.updateCategory(category, id);
    }

    /**
     * Xóa Hạng mục và tất cả Product trong đó nếu có
     *
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/admin/category/{id}")
    @PreAuthorize("hasRole('Full')")
    public String deleteCategory(@PathVariable String id) throws Exception {
        return categoryServiceInterface.deleteCategory(id);
    }

    /**
     * Lấy ra một hạng mục trong database theo id
     *
     * @param id
     * @return Category
     */
    //TODO Sử dụng khi nhét giá trị vào Body ( Object) @ RequestBody
    @GetMapping("/admin/category/{id}")
    @PreAuthorize("hasRole('Read_Only') or hasRole('Full')")
    public Category getbyId(@PathVariable String id) throws Exception {
        return categoryServiceInterface.getById(id);
    }
}
