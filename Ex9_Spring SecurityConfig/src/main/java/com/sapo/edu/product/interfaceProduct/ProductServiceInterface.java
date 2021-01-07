package com.sapo.edu.product.interfaceProduct;

import com.sapo.edu.product.model.Product;

import java.util.List;

public interface ProductServiceInterface {
    int deleteCategory(int category_id) throws Exception;

    List<Product> fillAllProduct();

    List<Product> fillAllProductPaging(int page, String query, int pageSize) throws Exception;

    List<Product> searchByCategory(String id) throws Exception;

    Product getById(String id) throws Exception;

    int getTotalProductCount();

    String insertProduct(Product product) throws Exception;

    String insertListProduct(List<Product> productList) throws Exception;

    String updateProduct(Product product, String id) throws Exception;

    String deleteProductById(String id) throws Exception;

}
