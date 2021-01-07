package com.sapo.edu;

import com.sapo.edu.customer.Function.Function;
import com.sapo.edu.customer.Input.Check;
import com.sapo.edu.customer.Input.Menu;
import com.sapo.edu.atm.interfaceATM.Atm;
import com.sapo.edu.customer.Model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    private final Atm atm ;
    private final Check check=new Check();
    private final Menu menu=new Menu();

    public Main(Atm atm) {
        this.atm = atm;
    }

    @Override
    public void run(String... args) throws Exception {
        // Nhập từ Console
        Customer customer=check.inputCustomer();
        char checkContinue=' ';
        while(checkContinue!='N'&&checkContinue!='n') {
            menu.Menu();
            int choice = check.inputChoice();
            Function func = new Function(atm);
            func.function(choice, customer);
            checkContinue=check.InputResume();
        }
    }


}
