package com.sapo.edu.check;

import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class checkInput {
    Scanner scanner=new Scanner(System.in);
    String input="";
    /**
     * com.sapo.edu.check Input to continue or not
     */
    public char InputResume(){
        System.out.println("Do you want to continue Y/N ");
        input = scanner.nextLine();
        while(!input.equalsIgnoreCase("Y")&&!input.equalsIgnoreCase("N")){
            System.out.println("Please input Y/N or y/n only");
            input = scanner.nextLine();
        }
        return input.charAt(0);
    }
    /**
     * Input Text
     */
    public String InputText(){
        System.out.println("Input the text your want to use: ");
        String text=scanner.nextLine();
        return text;
    }
    /**
     * Check input Choice
     */
    public int InputChoice() {
        int choice = -1;
        System.out.println("Input your choice");
        while (choice < 0 || choice > 7)
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Please input in range 1 - 7");
            }
        return choice;
    }
}
