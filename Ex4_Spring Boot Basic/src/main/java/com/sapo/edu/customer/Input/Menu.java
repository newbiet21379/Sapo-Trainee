package com.sapo.edu.customer.Input;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("menu")
public class Menu {

    /**
     * Menu
     */
    public void Menu() {
        System.out.println("Choose which task you want to use");
        System.out.println("1. Withdraw");// Rút tiền
        System.out.println("2. Deposit");// Gửi tiền
        System.out.println("3. Check Bank Balance");// Kiểm tra số dư tài khoản
        System.out.println("4. View Account Information");// Kiểm tra thông tin tài khoản
    }
}
