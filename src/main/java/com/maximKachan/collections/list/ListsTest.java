package com.maximKachan.collections.list;



public class ListsTest {
    public static void main(String[] args) {
        MyOwnArrayList<String> list = new MyOwnArrayList<>();
        System.out.println(list.isEmpty());
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        System.out.println(list.contains("aaa"));
        System.out.println(list.contains("a"));
        System.out.println(list.isEmpty());
        System.out.println(list.size());
        System.out.println(list);
    }
}
