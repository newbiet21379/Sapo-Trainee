package com.sapo.edu.contain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class Contains {
    String input="";
    Scanner scanner=new Scanner(System.in);
    public void containsAny(String text){
        System.out.println("1. Contains Any");
        System.out.println("Input character / string to compare ");
        input = scanner.nextLine();
        if(StringUtils.containsAny(text,input))
            System.out.println("\""+text +"\""+ " contains "+"\""+input+"\"");
        else
            System.out.println("\""+text +"\""+ " don't contains any of "+"\""+input+"\"");
    }
    public void containsIgnoreCase(String text){
        System.out.println("2. Contains Ignore Case");
        System.out.println("Input character / string to compare ");
        input = scanner.nextLine();
        if(StringUtils.containsAny(text,input))
            System.out.println("\""+text +"\""+ " contains ignore case "+"\""+input+"\"");
        else
            System.out.println("\""+text +"\""+ " don't contains ignore case "+"\""+input+"\"");
    }
}
