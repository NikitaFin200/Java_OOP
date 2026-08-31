package ru.academits.findyurov;

public class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Circle circle = (Circle) obj;
        return radius == circle.radius;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        return prime + Double.hashCode(radius);
    }

    @Override
    public String toString() {
        return "Circle [radius: " + radius + "]";
    }
}