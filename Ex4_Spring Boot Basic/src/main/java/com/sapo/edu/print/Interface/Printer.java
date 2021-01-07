package com.sapo.edu.print.Interface;

import com.sapo.edu.customer.Model.Customer;


public interface Printer {
    void printCustoner(Customer customer);

    void printMessage(String message);
}
