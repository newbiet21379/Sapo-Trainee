package com.sapo.edu.print.function;


import com.sapo.edu.customer.Model.Customer;
import com.sapo.edu.print.Interface.Printer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("printerConsole")
public class PrinterConsole implements Printer {
    /**
     * In thông báo thông tin khách hàng ra màn hình
     * @param customer
     */
    @Override
    public void printCustoner(Customer customer) {
        System.out.println("CustomerId: " + customer.getAcctNo() + ", balance: " + customer.getBalance().toString());
    }
    /**
     * In thông báo thông báo ra màn hình
     *
     */
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
