package com.sapo.edu.product.service;

import com.sapo.edu.category.Interface.CategoryDAOInterface;

import com.sapo.edu.category.validate.exception.CategoryNotFoundException;
import com.sapo.edu.product.validate.exception.ProductNotFoundException;
import com.sapo.edu.product.interfaceProduct.ProductDAOInterface;
import com.sapo.edu.product.interfaceProduct.ProductServiceInterface;
import com.sapo.edu.product.model.Product;
import com.sapo.edu.product.model.ResponseProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ProductService")
public class ProductService implements ProductServiceInterface {
    @Autowired
    ProductDAOInterface productDAOInterface;

    @Autowired
    CategoryDAOInterface categoryDAOInterface;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteCategory(int category_id) throws Exception {
        int product_row = 0;

        if (productDAOInterface.searchByCategory(category_id).size() > 0)
            product_row = productDAOInterface.deleteProductByCategory(category_id);
//        if(category_row==0){
//            throw new Exception("There are no Category with id : "+category_id);
//        }
        return product_row;
    }

    @Override
    public List<Product> fillAllProduct() {
        return productDAOInterface.fillAllProduct();
    }

    @Override
    public ResponseProduct fillAllProductPaging(int page, int pageSize,int id) throws Exception {
        double total_item = productDAOInterface.getTotalProductCountID(id);
        int total_page = (int) Math.ceil(total_item / pageSize);
        if (page > total_page || page < 0)
            throw new Exception("Max page range input is: 1 -> " + total_page);
        return new ResponseProduct(productDAOInterface.fillAllProductPaging(page, pageSize,id), total_page) ;
    }

    @Override
    public List<Product> searchByCategory(String id) throws Exception {
        List<Product> products = null;
        try {
            int id_raw = Integer.parseInt(id);
            try {
                products = productDAOInterface.searchByCategory(id_raw);
            } catch (DataAccessException ex) {
                throw new CategoryNotFoundException(id_raw);
            }

        } catch (NumberFormatException ex) {
            throw new Exception("Please input right category id format");
        }
        return products;
    }

    @Override
    public Product getById(String id) throws Exception {
        Product product = null;
        try {
            int id_raw = Integer.parseInt(id);
            try {
                product = productDAOInterface.getById(id_raw);
            } catch (DataAccessException ex) {
                throw new ProductNotFoundException(id_raw);
            }

        } catch (NumberFormatException ex) {
            throw new Exception("Please input right id format");
        }
        return product;
    }

    @Override
    public int getTotalProductCount() {
        return 0;
    }

    @Override
    public String insertProduct(Product product) throws Exception {
        checkInputProduct(product);
        String result = "Add Product failed";
        if (product != null)
            result = "Add Product successfully ";
        result += "\nYou have add " + productDAOInterface.insertProduct(product) + " Product";
        return result;
    }

    @Override
    public String insertListProduct(List<Product> productList) throws Exception {
        String result = "";
        if (productList.isEmpty())
            return "Add Product failed";
        if (!productList.isEmpty())
            result = "Add Product successfully ";
        for (Product i : productList
        ) {
            checkInputProduct(i);
        }
        result += "\nYou have add " + productDAOInterface.insertListProduct(productList) + " Product";
        return result;
    }

    @Override
    public String updateProduct(Product product, String id) throws Exception {
        String result = "";

        try {
            product.setId(Integer.parseInt(id));
            checkInputProduct(product);
            Product old = productDAOInterface.getById(Integer.parseInt(id));

            result += "\nYou have Update " + productDAOInterface.updateProduct(product) + " Product on id= " + id +
                    "\n" + product.toString()
                    + "\n Old Product: " + old.toString()
                    + "\n New Product: " + product.toString();
        } catch (NumberFormatException ex) {
            return "You can't input text into id to search";
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteProductById(String id) throws Exception {
        String result = "";
        try {
            int id_raw = Integer.parseInt(id);
            try {
                int row = productDAOInterface.deleteProductById(id_raw);
                result = "You have deleted " + row + " row(s) from Product with id: " + id_raw;
            } catch (DataAccessException ex) {
                throw new ProductNotFoundException(id_raw);
            }

        } catch (NumberFormatException ex) {
            throw new Exception("Please input right id format");
        }
        return result;
    }

    /**
     * check nhập vào của Product
     *
     * @param product
     * @throws Exception
     */
    private void checkInputProduct(Product product) throws Exception {

        if (product.getQuantity() < 0) {
            throw new Exception("Product " + product.getId() + ": Quantity can't be less than 0");
        }
        if (product.getSale_number() > product.getQuantity()) {
            throw new Exception("Product "+product.getId()+":Sale number must be less than quantity");
        }
        if (product.getSale_number() < 0) {
            throw new Exception("Product "+product.getId()+":Sale number must be more than 0");
        }
        if (product.getCategory_id() < 0 || categoryDAOInterface.getTotalCategoryById(product.getCategory_id()) == 0) {
            throw new Exception("Product "+product.getId()+":Must input a valid category ");
        }

    }
}
