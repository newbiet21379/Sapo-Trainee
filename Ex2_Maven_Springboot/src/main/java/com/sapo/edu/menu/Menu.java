package com.sapo.edu.menu;

import org.springframework.stereotype.Component;

@Component
public class Menu {
    /**
     * Menu
     */
    public void Menu() {
        System.out.println("Choose which task you want to use");
        System.out.println("1. Contains Any");// Có chứa kí tự đó không
        System.out.println("2. Contains Ignore Case");// Có chứa kí tự ko quan tâm Case
        System.out.println("3. Count Matches");// Đếm số lần xuất hiện
        System.out.println("4. Append If Missing");// Cộng cuối xâu nếu chưa có
        System.out.println("5. Prepend If Missing");//Cộng đầu xâu nếu chưa có
        System.out.println("6. Uppercase");// Viết hoa hết xâu
        System.out.println("7. Lowercase");//Viết thường hết xâu
    }
}
