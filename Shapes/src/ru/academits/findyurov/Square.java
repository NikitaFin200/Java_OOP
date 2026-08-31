package ru.academits.findyurov;

public class Square implements Shape {
    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return 4 * sideLength;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Square square = (Square) obj;
        return sideLength == square.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        return prime + Double.hashCode(sideLength);
    }

    @Override
    public String toString() {
        return "Square [sideLength: " + sideLength + "]";
    }
}