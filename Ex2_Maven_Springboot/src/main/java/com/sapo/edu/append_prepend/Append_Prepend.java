package com.sapo.edu.append_prepend;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class Append_Prepend {
    String input="";
    Scanner scanner=new Scanner(System.in);
    public void appendIfMissing(String text){
        System.out.println("4. Append If Missing");
        System.out.println("Input character/string to check");
        input = scanner.nextLine();
        System.out.println("Your input: "+"\""+input+"\""+" and the new String is: "+ StringUtils.appendIfMissing(text,input));
    }
    public void prependIfMissing(String text){
        System.out.println("5. Prepend If Missing");
        System.out.println("Input character/string to check");
        input = scanner.nextLine();
        System.out.println("Your input: "+"\""+input+"\""+" and the new String is: "+StringUtils.prependIfMissing(text,input));
    }
}
