package ru.academits.findyurov.main;

import ru.academits.findyurov.Shape;
import ru.academits.findyurov.Circle;
import ru.academits.findyurov.Rectangle;
import ru.academits.findyurov.Square;
import ru.academits.findyurov.Triangle;
import ru.academits.findyurov.comparators.ShapeAreaComparator;
import ru.academits.findyurov.comparators.ShapePerimeterComparator;

import java.util.Arrays;

public class Main {
    public static Shape getShapeWithMaxArea(Shape... shapes) {
        if (shapes == null) {
            throw new NullPointerException("The array is null.");
        }

        if (shapes.length == 0) {
            throw new IllegalArgumentException("Array size is 0. The array must be >= 1.");
        }

        Arrays.sort(shapes, new ShapeAreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondPerimeter(Shape... shapes) {
        if (shapes == null) {
            throw new NullPointerException("The array is null.");
        }

        if (shapes.length < 2) {
            throw new IllegalArgumentException("Array size is incorrect and is equal to = " + shapes.length
                    + "The array must be >= 2.");
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape square = new Square(2);
        Shape triangle = new Triangle(0, 0, 3, 0, 0, 4);
        Shape rectangle = new Rectangle(1, 2);
        Shape circle = new Circle(5);

        Shape[] shapesArray1 = {
                square,
                triangle,
                rectangle,
                circle
        };

        for (Shape shape : shapesArray1) {
            System.out.println(shape);
            System.out.println("area = " + shape.getArea());
            System.out.println("width = " + shape.getWidth());
            System.out.println("height = " + shape.getHeight());
            System.out.println("perimeter = " + shape.getPerimeter());
            System.out.println("hash = " + shape.hashCode());
            System.out.println();
        }

        Shape[] shapesArray2 = {
                new Square(9),
                new Triangle(0, 1, 4, 2, 1, 6),
                new Rectangle(5, 3),
                new Circle(2),
                new Square(7),
                new Triangle(7, 7, 2, 4, 2, 5),
                new Rectangle(7, 3),
                new Circle(4),
                new Square(2),
                new Triangle(0, 0, 3, 0, 0, 4),
                new Rectangle(1, 2),
                new Circle(5)
        };

        Shape maxAreaShape = getShapeWithMaxArea(shapesArray2);
        System.out.println(maxAreaShape);
        System.out.println("area = " + maxAreaShape.getArea());
        System.out.println("width = " + maxAreaShape.getWidth());
        System.out.println("height = " + maxAreaShape.getHeight());

        System.out.println();

        Shape secondPerimeterShape = getShapeWithSecondPerimeter(shapesArray2);
        System.out.println(secondPerimeterShape);
        System.out.println("perimeter = " + secondPerimeterShape.getPerimeter());
        System.out.println("width = " + secondPerimeterShape.getWidth());
        System.out.println("height = " + secondPerimeterShape.getHeight());
    }
}