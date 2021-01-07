package com.sapo.edu.function;

import com.sapo.edu.append_prepend.Append_Prepend;
import com.sapo.edu.contain.Contains;
import com.sapo.edu.count.Count;
import org.springframework.stereotype.Component;
import com.sapo.edu.upper_lowercase.Upper_Lowercase;

@Component
public class Function {
    public void function(int choice,String text){
        switch (choice){
            case 1:
                Contains contains=new Contains();
                contains.containsAny(text);
                break;
            case 2:
                contains=new Contains();
                contains.containsIgnoreCase(text);
                break;
            case 3:
                Count count=new Count();
                count.countMatches(text);
                break;
            case 4:
                Append_Prepend ap=new Append_Prepend();
                ap.appendIfMissing(text);
                break;
            case 5:
                ap=new Append_Prepend();
                ap.prependIfMissing(text);
                break;
            case 6:
                Upper_Lowercase up=new Upper_Lowercase();
                up.uppercase(text);
                break;
            case 7:
                up=new Upper_Lowercase();
                up.lowercase(text);
                break;
        }
    }
}
