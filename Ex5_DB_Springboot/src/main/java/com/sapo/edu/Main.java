package com.sapo.edu;

import com.sapo.edu.category.Interface.CategoryDAOInterface;
import com.sapo.edu.category.Interface.CategoryServiceInterface;
import com.sapo.edu.category.model.Category;
import com.sapo.edu.function.category.input.inputCategory;
import com.sapo.edu.product.interfaceProduct.ProductDAOInterface;
import com.sapo.edu.product.interfaceProduct.ProductServiceInterface;
import com.sapo.edu.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {
    //TODO inject ProductDAOImpl.

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
@Autowired
CategoryServiceInterface categoryServiceInterface;
    @Override
    public void run(String... args) throws Exception {
        boolean stop=false;
        Scanner scanner=new Scanner(System.in);
        inputCategory input=new inputCategory();
        int count=0;
        List<Category> categoryList=categoryServiceInterface.fillAllCategory();
        while(!stop){
            System.out.println("Choose your option to start");
            System.out.println("Please input from 1 to 4");
            System.out.println("1. View");
            System.out.println("2. Add");
            System.out.println("3. Edit");
            System.out.println("4. Delete");
            int choice=input.inputNumberInRange(1,4," Input your choice: ");
            switch (choice){
                case 1:
                        categoryList.forEach(System.out::println);
                        break;
                case 2:
                    System.out.println("Input your Category");
                    Category category=input.inputCategory();
                    categoryServiceInterface.insertCategory(category);
                    break;
                case 3:
                    System.out.println("Input your Category");
                    Category categoryEdit=input.inputCategory();
                    System.out.println("Input where to update from ");
                    for (Category i:categoryList
                         ) {
                        if(count>4)
                            System.out.println();
                        System.out.print(i.getId()+"\t");
                        count++;
                    }
                    String id=input.inputText(" Input id: ");
                    categoryServiceInterface.updateCategory(categoryEdit,id);
                    break;
                case 4:
                    System.out.println("Input Delete Category ID from below list ");

                    for (Category i:categoryList
                    ) {
                        if(count>4)
                            System.out.println();
                        System.out.print(i.getId()+"\t");
                        count++;
                    }
                    String delete=input.inputText(" Input id: ");
                    categoryServiceInterface.deleteCategory(delete);
                    break;
            }
        }

    }
}
