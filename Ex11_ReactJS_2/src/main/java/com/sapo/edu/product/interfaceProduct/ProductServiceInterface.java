package com.sapo.edu.product.interfaceProduct;

import com.sapo.edu.product.model.Product;
import com.sapo.edu.product.model.ResponseProduct;

import java.util.List;

public interface ProductServiceInterface {
    int deleteCategory(int category_id) throws Exception;

    List<Product> fillAllProduct();

   ResponseProduct fillAllProductPaging(int page, int pageSize,int id) throws Exception;

    List<Product> searchByCategory(String id) throws Exception;

    Product getById(String id) throws Exception;

    int getTotalProductCount();

    String insertProduct(Product product) throws Exception;

    String insertListProduct(List<Product> productList) throws Exception;

    String updateProduct(Product product, String id) throws Exception;

    String deleteProductById(String id) throws Exception;

}
