package com.sapo.edu.product.controller;


import com.sapo.edu.product.interfaceProduct.ProductDAOInterface;
import com.sapo.edu.product.interfaceProduct.ProductServiceInterface;
import com.sapo.edu.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductDAOInterface productDAOInterface;
    @Autowired
    ProductServiceInterface productServiceInterface;

    /**
     * Lấy tất cả Product
     *
     * @return
     */
    @GetMapping("/admin/product/all")
    @PreAuthorize("hasRole('Read_Only') or hasRole('Full')")
    public List<Product> getAll() {
        return productServiceInterface.fillAllProduct();
    }

    /**
     * Lấy tất cả product có paging theo name và page, pagesize
     *
     * @param page
     * @param query
     * @param pageSize
     * @return
     * @throws Exception
     */
    @GetMapping("/admin/product")
    @PreAuthorize("hasRole('ROLE_Read_Only') or hasRole('ROLE_Full')")
    public List<Product> getPagingAll(@RequestParam int page, @RequestParam String query, @RequestParam int pageSize) throws Exception {

        return productServiceInterface.fillAllProductPaging(page, query, pageSize);
    }

    /**
     * Lấy Product theo Category
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/admin/product/category")
    @PreAuthorize("hasRole('Read_Only') or hasRole('Full')")
    public List<Product> getProductByCategoryId(@RequestParam String id) throws Exception {

        return productServiceInterface.searchByCategory(id);
    }

    @GetMapping("/admin/product/{id}")
    public Product getProductById(@PathVariable String id) throws Exception {

        return productServiceInterface.getById(id);
    }

    //TODO Sử dụng khi nhét giá trị vào Body ( Object )
    @PostMapping("/admin/product/")
    @PreAuthorize("hasRole('Full')")
    public String insertProduct(@RequestBody Product product) throws Exception {

        return productServiceInterface.insertProduct(product);
    }

    @PostMapping("/admin/product/list")
    @PreAuthorize("hasRole('Full')")
    public String insertProduct(@RequestBody List<Product> product) throws Exception {

        return productServiceInterface.insertListProduct(product);
    }

    /**
     * Update Product bằng RequestBody và PathVariable
     *
     * @param product
     * @param id
     * @return
     * @throws Exception
     */
    // TODO: 6/4/2020
    @PutMapping("/admin/product/{id}")
    @PreAuthorize("hasRole('Full')")
    public String editProduct(@RequestBody Product product, @PathVariable("id") String id) throws Exception {

        return productServiceInterface.updateProduct(product, id);
    }

    @DeleteMapping("/admin/product/{id}")
    @PreAuthorize("hasRole('Full')")
    public String deleteProduct(@PathVariable String id) throws Exception {
        return productServiceInterface.deleteProductById(id);
    }

}
