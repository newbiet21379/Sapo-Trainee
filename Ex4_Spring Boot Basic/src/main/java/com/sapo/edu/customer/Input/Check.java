package com.sapo.edu.customer.Input;

import com.sapo.edu.customer.Model.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
@Component
@Qualifier("check")
public class Check {
    final Scanner scanner=new Scanner(System.in);

    /**
     * Nhập vào Text
     * @param messege
     * @return text nhập từ bàn phím
     */
    public String inputText(String messege){
        System.out.println(messege);
        String text=scanner.nextLine();
        return text;
    }
    public String inputPin(String messege){
        System.out.println(messege);
        String text="";
        boolean firstInput=false;
        while(!StringUtils.isNumeric(text)){
            if(firstInput)
                System.out.println("Nhập lại chỉ số");
            text=scanner.nextLine();
            firstInput=true;
        }
        return text;
    }
    /**
     * Nhập giá trị tiền từ bàn phím
     * @param messege
     * @return giá trị nhập từ bàn phím
     */
    public BigDecimal inputBigDecimal(String messege){
        System.out.println(messege);
        BigDecimal input=new BigDecimal(0);
        while(true) {
            try {
                input = new BigDecimal(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please input right money format");
                continue;
            }
        }
        return input;
    }

    /**
     * Nhập lựa chọn từ Menu
     * @return
     */
    public int inputChoice() {
        int choice = -1;
        boolean firstInput=false;
        System.out.println("Input your choice");
        while (choice < 0 || choice > 5) {
            if(firstInput)
                System.out.println("Please input in range 1 - 4");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                firstInput=true;
            } catch (NumberFormatException ex) {
                System.out.println("Please input in range 1 - 4");
                continue;
            }
        }
        return choice;
    }

    /**
     * Nhập nhiều khách hàng thì check lặp
     * @param customers
     * @return
     */
    public Customer inputCustomers(ArrayList<Customer> customers){
        Customer customer;
        while (true) {
            boolean duplicate=false;
            String accNo = inputText("Enter your account name:");
            String pass = inputText("Enter your account password:");
            BigDecimal money = inputBigDecimal("Enter the amount of money in your account");
            for (Customer i : customers
            ) {
                if (i.equals(new Customer(accNo, pass, money))) {
                    System.out.println("Please re-input your information");
                    duplicate=true;
                    break;
                }
            }
            if(duplicate) continue;
           customer=new Customer(accNo,pass,money);
            break;
        }
        return customer;
    }

    /**
     * Nhập khách hàng
     * @return
     */
    public Customer inputCustomer(){
            String accNo = inputText("Enter your account name:");
            String pass = inputPin("Enter your account password:");
            BigDecimal money = inputBigDecimal("Enter the amount of money in your account");
        return  new Customer(accNo,pass,money);
    }

    /**
     * Nhập tiếp tục hay không
     * @return
     */
    public char InputResume(){
        System.out.println("Do you want to continue Y/N ");
        String input = scanner.nextLine();
        while(!input.equalsIgnoreCase("Y")&&!input.equalsIgnoreCase("N")){
            System.out.println("Please input Y/N or y/n only");
            input = scanner.nextLine();
        }
        return input.charAt(0);
    }
}
