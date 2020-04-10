package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args){
        ArrayList<List> arr = new ArrayList<>();
        ArrayList<String> arr2 = new ArrayList<>();
        arr2.add("a");
        ArrayList<String> arr3 = new ArrayList<>();
        arr3.add("b");
        arr.add(arr2);
        arr.add(arr3);
        arr.get(0).add("b");
        System.out.println(arr);
    }

}
