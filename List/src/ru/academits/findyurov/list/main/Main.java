package ru.academits.findyurov.list.main;

import ru.academits.findyurov.list.list.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<java.io.Serializable> lis = new LinkedList<>();

        lis.addValue(2);
        lis.addValue(10);
        lis.addValue(11);
        lis.addValue("rrr");

        System.out.print("Print list");
        lis.print();
        System.out.println();

        System.out.print("Get list size:");
        System.out.println(lis.getSize());

        System.out.print("Get first element:");
        System.out.println(lis.getFirstValue());

        System.out.print("Get element for index:");
        System.out.println(lis.getValue(2));

        System.out.println("Zamena");
        System.out.println(lis.setValue(1, 100));

        System.out.println("remove");
        lis.remove(1);
        lis.print();
        System.out.println();

        System.out.println("Adding an element to the beginning");
        lis.enterElementFirst("rr");
        lis.print();
        System.out.println();

        System.out.println("Adding an item by index");
        lis.enterElementForIndex(1, 1000);
        lis.print();
        System.out.println();

        System.out.println("Deleting a node by value");
        System.out.print("Answer: ");
        System.out.println(lis.removeAtValue(2));
        lis.print();
        System.out.println();

        System.out.println("Remove first element");
        System.out.println(lis.removeFirstElement());
        System.out.println();
        lis.print();
        System.out.println();

        lis.addValue(222);
        lis.addValue(221);
        lis.addValue(234);
        System.out.println();

        System.out.println("Reverse");
        lis.reverse();
        lis.print();
        System.out.println();

        System.out.println("Copy");
        lis.print(lis.copy());
    }
}