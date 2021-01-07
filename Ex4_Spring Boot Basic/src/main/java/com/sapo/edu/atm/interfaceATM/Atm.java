package com.sapo.edu.atm.interfaceATM;

import com.sapo.edu.customer.Model.Customer;

import java.math.BigDecimal;

public interface Atm {
    void withDraw(Customer customer, BigDecimal amount);

    void printCurrentMoney();

    void deposit(Customer customer, BigDecimal amount);

    void displayCustomerInfo(Customer customer);
}
