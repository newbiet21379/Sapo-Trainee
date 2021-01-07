package com.sapo.edu.customer.Function;

import com.sapo.edu.customer.Input.Check;
import com.sapo.edu.atm.interfaceATM.Atm;
import com.sapo.edu.customer.Model.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class Function {
    @Qualifier("bidvAtm")
    private final Atm atm ;
    public Function(Atm atm) {
        this.atm = atm;
    }

    /**
     * Các chức năng theo lựa chọn của KH
     * @param choice
     * @param customer
     */
    public void function(int choice, Customer customer){
        Check check=new Check();
        BigDecimal amount=new BigDecimal(0);
        switch (choice){
            case 1:
                amount=check.inputBigDecimal("Enter amount of withdraw");
                atm.withDraw(customer,amount);
                break;
            case 2:
                amount=check.inputBigDecimal("Enter amount of deposit");
                atm.deposit(customer,amount);
                break;
            case 3:
                atm.printCurrentMoney();
                break;
            case 4:
                atm.displayCustomerInfo(customer);
                break;
        }
    }
}
