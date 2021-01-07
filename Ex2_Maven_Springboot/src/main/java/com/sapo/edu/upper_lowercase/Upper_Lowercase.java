package com.sapo.edu.upper_lowercase;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class Upper_Lowercase {
    String input="";
    Scanner scanner=new Scanner(System.in);
    public void uppercase(String text){
        System.out.println("6. Uppercase");
        System.out.println("Your old text: "+text);
        System.out.println("Your new text: "+ StringUtils.upperCase(text));
    }
    public void lowercase(String text){
        System.out.println("7. Lowercase");
        System.out.println("Your old text: "+text);
        System.out.println("Your new text: "+StringUtils.lowerCase(text));
    }
}
