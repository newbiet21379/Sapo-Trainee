package com.sapo.edu.customer.Model;

import java.math.BigDecimal;

public class Customer {

    private String acctNo;
    private String pin;
    private BigDecimal balance;

    public Customer() {
    }

    public Customer(String accNo, String pin, BigDecimal balance) {
        this.acctNo = accNo;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object obj) {
        if(((Customer)obj).acctNo.equalsIgnoreCase(acctNo))
        return true;
        else return false;
    }
}
