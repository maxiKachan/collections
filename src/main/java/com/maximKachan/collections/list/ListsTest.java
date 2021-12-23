package com.maximKachan.collections.list;


import com.maximKachan.collections.set.MySet;

import java.util.Arrays;

public class ListsTest {
    public static void main(String[] args) {
//        MyOwnArrayList<String> list = new MyOwnArrayList<>();
//        System.out.println(list.isEmpty());
//        list.add("aaa");
//        list.add("aaa");
//        list.add("aaa");
//        list.add("aaa");
//        list.add("aaa");
//        System.out.println(list.contains("aaa"));
//        System.out.println(list.contains("a"));
//        System.out.println(list.isEmpty());
//        System.out.println(list.size());
//        String[] strings = list.toArray(new String[0]);
//        Object[] objects = list.toArray();
//        System.out.println(Arrays.toString(strings));
//        System.out.println(Arrays.toString(objects));
//        System.out.println(list);
//        list.remove("aaa");
//        System.out.println(list);
//        MyOwnArrayList<String> list2 = new MyOwnArrayList<>();
//        list2.add("bbb");
//        list2.add("ab");
//        System.out.println(list.containsAll(list2));
//        list.addAll(list2);
//        System.out.println(list);
//        list.addAll(2, list2);
//        System.out.println(list);
//        list.removeAll(list2);
//        System.out.println(list);
        MySet<String> mySet = new MySet<>();
        for (int i = 0; i < 11; i++){
            System.out.println(mySet.add("a"));
        }
        mySet.add("b");
        mySet.add("bs");
        mySet.add("bd");
        mySet.add("ba");
        mySet.add("bf");
        mySet.add("bq");
        mySet.add("bw");
        System.out.println(mySet.toString());
        for (String s : mySet){
            System.out.println(s);
        }
    }
}
