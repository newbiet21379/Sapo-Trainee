package com.sapo.edu.product.interfaceProduct;

import com.sapo.edu.product.model.Product;

import java.util.List;

public interface ProductDAOInterface {
    List<Product> fillAllProduct();

    List<Product> fillAllProductPaging(int page, int pageSize,int id);

    List<Product> searchByCategory(int category_id);

    Product getById(int id);

    int getTotalProductCount();

    int getTotalProductCountID(int id);

    int insertProduct(Product product) throws Exception;

    int insertListProduct(List<Product> productList) throws Exception;

    int updateProduct(Product product) throws Exception;

    int deleteProductById(int id);

    int deleteProductByCategory(int id) throws Exception;
    Product getLastProduct();
}
