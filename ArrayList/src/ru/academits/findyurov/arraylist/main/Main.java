package ru.academits.findyurov.arraylist.main;

import ru.academits.findyurov.arraylist.arrayList.MyArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list1 = new MyArrayList<>(10);

        list1.add(1);
        list1.add(7);
        list1.add(14);
        list1.add(14);
        list1.add(24);
        list1.add(14);
        list1.add(24);

        list1.print();

        list1.set(2, 100);

        list1.print();

        System.out.println(list1.isEmpty());

        MyArrayList<Integer> list2 = new MyArrayList<>(10);

        System.out.println(list2.isEmpty());

        list2.add(100);
        System.out.println(list1.contains(7));

        System.out.println(Arrays.toString(list1.toArray()));

        list1.remove(100);
        list1.print();
    }
}