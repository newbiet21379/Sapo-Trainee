package com.sapo.edu.function.category.input;

import com.sapo.edu.category.model.Category;
import org.springframework.cache.support.NullValue;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class inputCategory {
    Scanner scanner=new Scanner(System.in);

    public Category inputCategory() throws Exception {
        boolean check=false;
        int id=inputNumber("Input id: ");
        String name=scanner.nextLine();
        String create_date="";
        String fix_date="";
        while(!check) {
            create_date = inputDate(" Create date");
            fix_date = inputDate("  Fix date");
            if(fix_date.compareTo(create_date)<0)
                continue;
            else{
                break;
            }
        }
        String description=inputText(" description");
        return new Category(id,name,create_date,fix_date,description);
    }

    public int inputNumber(String messege){
        boolean check=false;
        while(!check) {
            System.out.println(messege);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Only input number please");
            }
        }
        return -1;
    }
    public int inputNumberInRange(int start,int end,String messege){
        boolean check=false;
        int input=0;
        while(!check) {
            System.out.println(messege);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input < start || input > end)
                    continue;
            } catch (NumberFormatException ex) {
                System.out.println("Only input number please");
            }
        }
        return input;
    }

    public String inputText(String messege) throws Exception {
        boolean check=false;
        while(!check) {

            String text = scanner.nextLine();
            if (text.isEmpty() || text.equals(""))
                throw new Exception("Please do not left blank"+ messege);
            return scanner.nextLine();
        }
        return "";
    }

    // TODO: 6/9/2020  20\d{2}(-|\/)((0[1-9])|(1[0-2]))(-|\/)((0[1-9])|([1-2][0-9])|(3[0-1]))(\s)(([0-1][0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])
    // TODO: 6/9/2020 DateTime format for later use

    public String inputDate(String messege) throws Exception{
        boolean check=false;
        DateTimeFormatter dateTimeFormatter;

        while(!check) {

            String text = scanner.nextLine();
            if (text.isEmpty() || text.equals(""))
                throw new Exception("Please do not left blank"+ messege);
            if(!text.matches("20\\d{2}(-|\\/)((0[1-9])|(1[0-2]))(-|\\/)((0[1-9])|([1-2][0-9])|(3[0-1]))(\\s)(([0-1][0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])"))
                continue;
            return text;
        }
        return "";
    }
}
