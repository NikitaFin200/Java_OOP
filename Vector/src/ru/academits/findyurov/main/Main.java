package ru.academits.findyurov.main;

import ru.academits.findyurov.Vector;

import java.util.Arrays;

public class  Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(2);
        vector1.setCoordinate(0, 1);
        vector1.setCoordinate(1, 0);
        System.out.println("vector 1 = " + vector1);
        System.out.println("Its dimension = " + vector1.getSize());
        System.out.println("Its length = " + vector1.getLength());
        int i = 1;
        System.out.println("His " + (i + 1) + "coordinates = " + vector1.getCoordinate(i));
        System.out.println();

        Vector vector2 = new Vector(vector1);
        System.out.println("vector 2 (copy vector 1) = " + vector2);
        System.out.println("Its dimension = " + vector2.getSize());
        System.out.println("Its length = " + vector2.getLength());
        System.out.println();

        System.out.println("Comparing two vectors.");
        System.out.println("Vector 1 = " + vector1);
        System.out.println("Vector 2 =" + vector2);
        System.out.println(vector1.equals(vector2));
        System.out.println();

        double[] array = {1, 2, 3};
        Vector vector3 = new Vector(array);
        System.out.println("Its dimension = " + vector3.getSize());
        System.out.println("Its length = " + vector3.getLength());
        vector3.subtract(vector1);
        System.out.println("vector 3 = vector 3 - vector 1 = " + vector3);
        System.out.println("vector 1 = " + vector1);
        System.out.println();

        Vector vector4 = new Vector(4, array);
        System.out.println("Its dimension = " + vector4.getSize());
        System.out.println("Its length = " + vector4.getLength());
        vector4.add(vector1);
        System.out.println("vector 4 = vector 4 + vector 1 = " + vector4);
        vector1.add(vector4);
        System.out.println("vector 1 = vector 1 + new vector 4 = " + vector1);
        System.out.println();

        int scalar = 6;
        vector1.multiplyByScalar(6);
        System.out.println("vector 1 * " + scalar + " = " + vector1);
        vector1.turn();
        System.out.println("vector 1 = expanded vector vector1 * " + scalar + " this " + vector1);
        System.out.println();

        System.out.println("static methods");
        Vector vector5 = Vector.getSum(vector1, vector3);
        System.out.println("vector 5 = vector 1 + vector 3 = " + vector5);

        Vector vector6 = Vector.getDifference(vector1, vector3);
        System.out.println("vector 6 = vector 1 - vector 3 = " + vector6);

        vector6.add(new Vector(new double[]{0, 0, 0, 0, 0}));
        System.out.println("vector 6 = " + vector6);
        System.out.println("vector 5 * vector 6 = " + Vector.getScalarProduct(vector5, vector6));
    }
}