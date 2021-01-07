package com.sapo.edu.product.controller;


import com.sapo.edu.product.interfaceProduct.ProductDAOInterface;
import com.sapo.edu.product.interfaceProduct.ProductServiceInterface;
import com.sapo.edu.product.model.Product;
import com.sapo.edu.product.model.ResponseProduct;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:3000"})
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
    @GetMapping("/admin/products/{id}")
    @PreAuthorize("hasRole('Read_Only') or hasRole('Full')")
    public ResponseProduct getPagingAll(@RequestParam int page, @RequestParam int pageSize,@PathVariable int id) throws Exception {

        return productServiceInterface.fillAllProductPaging(page, pageSize,id);
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
    @PreAuthorize("hasRole('Read_Only') or hasRole('Full')")
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
