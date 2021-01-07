package com.sapo.edu.count;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class Count {
    String input="";
    Scanner scanner=new Scanner(System.in);
    public void countMatches(String text){
        System.out.println("3. Count Matches");
        System.out.println("Input character / string to compare ");
        input = scanner.nextLine();
        System.out.println("\""+text +"\""+ " contains "+ StringUtils.countMatches(text,input)+" of "+"\""+input+"\"");
    }
}
