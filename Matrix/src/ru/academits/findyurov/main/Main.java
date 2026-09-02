package ru.academits.findyurov.main;

import ru.academits.findyurov.Matrix;
import ru.academits.findyurov.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(4, 4);
        System.out.println(matrix1);
        System.out.println();

        double[][] matrixArray = {
                {1, 2, 7, 21, 7},
                {5, 5, 9, 11, 8},
                {3, 8, 11, 4, 9},
                {2, 3, 1, 12, 10}
        };

        Matrix matrix2 = new Matrix(matrixArray);
        System.out.println(matrix2);
        System.out.println();

        System.out.println(matrix2.getColumnsQuantity());
        System.out.println(matrix2.getColumn(2));

        System.out.println("3");
        System.out.println(matrix2.getRow(3));

        System.out.println("determinant = " + matrix2.getDeterminant());
        System.out.println("Columns quantity = " + matrix2.getColumnsQuantity());
        System.out.println("Rows quantity = " + matrix2.getRowsQuantity());
        System.out.println();

        Matrix matrix3 = new Matrix(matrix2);
        System.out.println("Copy Constructor(academ.findyurov.matrixArray.Matrix 3):");
        System.out.println(matrix3);
        System.out.println();

        int i = 0;
        System.out.println("Row number " + (i + 1) + ":");
        System.out.println(matrix2.getRow(i));
        System.out.println();

        System.out.println("matrix2");
        System.out.println(matrix2);

        double[] value = {20, 2, 5, 6, 7};
        System.out.println("let's change it to" + Arrays.toString(value));
        matrix2.setRow(i, new Vector(value));
        System.out.println(matrix2.getRow(0));
        System.out.println();

        i = 1;
        System.out.println("Column number" + (i + 1) + ":");
        System.out.println(matrix2.getColumn(i));
        System.out.println();

        System.out.println("got the matrixArray");
        System.out.println(matrix2);
        System.out.println();

        System.out.println("Transform the matrixArray:");
        matrix2.transpose();
        System.out.println(matrix2);
        System.out.println();

        double alpha = 6;
        System.out.println("Multiply the matrixArray by a scalar:" + alpha);
        matrix2.multiplyByScalar(alpha);
        System.out.println(matrix2);
        System.out.println();

        System.out.println("Multiplying a matrixArray by a vector");
        Vector vector = new Vector(new double[]{0, 3, 0, 1, 4});
        System.out.println(vector + ":");
        Vector mulOnVec = matrix2.multiply(vector);
        System.out.println(mulOnVec);
        System.out.println();

        System.out.println("A matrixArray created from an array of vectors:");
        Vector[] vectors = {new Vector(new double[]{1, 4, 2}), new Vector(new double[]{3, 6, 7})};
        Matrix matrix4 = new Matrix(vectors);
        System.out.println(matrix4);

        System.out.println("Let's add the matrixArray 3 and 3 statically:");
        Matrix matrix5 = Matrix.getSum(matrix3, matrix3);
        System.out.println(matrix5);
        System.out.println();

        System.out.println("Subtract matrixArray 3 from matrixArray 3 (statically):");
        Matrix matrix6 = Matrix.getDifference(matrix3, matrix3);
        System.out.println(matrix6);
        System.out.println();

        System.out.println("Let's add matrixArray 3 to matrixArray 3:");
        matrix3.add(matrix3);
        System.out.println("Now matrixArray 3 has the form:");
        System.out.println(matrix3);
        System.out.println();

        System.out.println("Now we subtract matrixArray 3 from the new matrixArray 3:");
        matrix3.subtract(matrix3);
        System.out.println("Now matrixArray 3 has the form:");
        System.out.println(matrix3);
        System.out.println();

        System.out.println(matrix3);
        System.out.println("Multiply the matrixArray (unit) by the matrixArray 3:");
        matrix1 = new Matrix(new double[][]{
                {5, 0, 0, 6},
                {0, 1, 0, 0},
                {8, 0, 1, 0},
                {0, 2, 0, 1}
        });

        Matrix matrix8 = new Matrix(new double[][]{
                {5, 0, 4, 1},
                {0, 1, 0, 0},
                {5, 0, 4, 0},
                {0, 8, 0, 1}
        });

        Matrix matrix7 = Matrix.getProduct(matrix1, matrix8);
        System.out.println(matrix7);
    }
}