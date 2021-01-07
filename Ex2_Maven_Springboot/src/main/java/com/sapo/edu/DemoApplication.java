package com.sapo.edu;

import com.sapo.edu.check.checkInput;
import com.sapo.edu.function.Function;
import com.sapo.edu.menu.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int choice = -1;
        String text = "";
        char resume = 'Y';
        System.out.println("Compare to blah blah "+ ("1900-01-01 00:00:00".compareTo("1900-01-03 00:00:00")));
        checkInput check = new checkInput();
        Menu menu = new Menu();
        Function function = new Function();
        while (resume != 'N' && resume != 'n') {
            menu.Menu();
            choice = check.InputChoice();
            text = check.InputText();
            function.function(choice, text);
            resume = check.InputResume();
        }
    }


}
