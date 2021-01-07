package com.sapo.edu;

// import com.sapo.edu.category.Interface.CategoryDAOInterface;
// import com.sapo.edu.product.interfaceProduct.ProductDAOInterface;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main implements CommandLineRunner {
    //TODO inject ProductDAOImpl.
    // @Autowired
    // @Qualifier("ProductDAO")
    // private ProductDAOInterface productDAO;
    // @Autowired
    // @Qualifier("CategoryDAO")
    // private CategoryDAOInterface categoryDAO;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Running...");
//        //TODO lấy tất cả product gán vào products.
//        List<Product> products = productDAO.fillAllProduct();
//
//        for (Product i : products) {
//            System.out.println(i.toString());
//        }
//        System.out.println("Total row of Product: " + productDAO.getTotalProductCount());
//        System.out.println("Total row of Category: " + categoryDAO.getTotalCategory());
//        System.out.println(productDAO.getById(1));
//        Product product = (productDAO.getById(1));
//        product.setCategory_id(14);
//        categoryDAO.fillAllCategory().forEach(System.out::println);
//        productDAO.fillAllProductPaging(1,"A",5).forEach(System.out::println);
//        System.out.println(categoryDAO.getTotalCategoryById(14));
//        productDAO.insertProduct(product);


    }
}
