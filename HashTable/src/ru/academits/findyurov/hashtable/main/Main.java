package ru.academits.findyurov.hashTable.main;

import ru.academits.findyurov.hashTable.hashTable.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<String> table = new HashTable<String>(5);

        table.add("apple");
        table.add("banana");
        table.add("cherry");

        System.out.println(table.contains("banana")); // true
        System.out.println(table.contains("orange")); // false

        for(String item : table) {
            System.out.println(item);
        }

        table.remove("apple");

        System.out.println(table.size()); // 2

        Object[] array = table.toArray();
        for(Object item : array) {
            System.out.println(item);
        }
    }
}