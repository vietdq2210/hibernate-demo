package com.vn.generic;

import com.vn.generic.CustomList;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        CustomList customList = new CustomList();
        customList.add(10);
        customList.add(5);
        customList.add(0);
        customList.add(3);

        System.out.println(customList.toString());
    }
}
