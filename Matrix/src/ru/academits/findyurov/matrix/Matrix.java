package ru.academits.findyurov.matrix;

import ru.academits.findyurov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsQuantity, int columnsQuantity) {
        if (rowsQuantity <= 0) {
            throw new IllegalArgumentException("Incorrect rows quantity." +
                    "The number must be greater than 0. Specified value = " + rowsQuantity);
        }

        if (columnsQuantity <= 0) {
            throw new IllegalArgumentException("Incorrect columns quantity." +
                    "The number must be greater than 0. Specified value = " + columnsQuantity);
        }

        rows = new Vector[rowsQuantity];

        for (int i = 0; i < rowsQuantity; ++i) {
            rows[i] = new Vector(columnsQuantity);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("It is not possible to create a matrix with a zero size.");
        }

        int columnsQuantity = 0;

        for (double[] row : array) {
            if (row.length > columnsQuantity) {
                columnsQuantity = row.length;
            }
        }

        if (columnsQuantity == 0) {
            throw new IllegalArgumentException("Quantity of columns is 0");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; ++i) {
            rows[i] = new Vector(columnsQuantity, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Number of columns is 0");
        }

        rows = new Vector[vectors.length];
        int columnsQuantity = 1;

        for (Vector vector : vectors) {
            if (vector.getSize() > columnsQuantity) {
                columnsQuantity = vector.getSize();
            }
        }

        for (int i = 0; i < vectors.length; ++i) {
            rows[i] = new Vector(columnsQuantity);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsQuantity() {
        return rows.length;
    }

    public int getColumnsQuantity() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds. Specified index = " + index
                    + ". Minimal index = 0. Maximal index = " + (rows.length - 1));
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds. Specified index = "
                    + index + ". Minimal index = 0. Maximal index = " + (rows.length - 1));
        }

        if (vector.getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException("Vector size = " + vector.getSize() + ", matrix row size = " + rows[0].getSize());
        }

        int size = vector.getSize();

        for (int i = 0; i < size; ++i) {
            rows[index].setCoordinate(i, vector.getCoordinate(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsQuantity()) {
            throw new IndexOutOfBoundsException("Illegal index of row. Specified index = " + index
                    + ". Minimal index = 0. Maximal index = " + (getColumnsQuantity() - 1));
        }

        int rowsQuantity = rows.length;
        Vector vector = new Vector(rowsQuantity);

        for (int i = 0; i < rowsQuantity; ++i) {
            vector.setCoordinate(i, rows[i].getCoordinate(index));
        }

        return vector;
    }

    public void transpose() {
        int columnsQuantity = getColumnsQuantity();
        Vector[] newRows = new Vector[columnsQuantity];

        for (int i = 0; i < columnsQuantity; i++) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        int rowsQuantity = rows.length;

        if (rowsQuantity == 2) {
            return rows[0].getCoordinate(0) * rows[1].getCoordinate(1)
                    - rows[0].getCoordinate(1) * rows[1].getCoordinate(0);
        }

        double determinant = 0;

        for (int i = 0; i < rowsQuantity; i++) {
            determinant += Math.pow(-1, i) * rows[0].getCoordinate(i) * getDeterminant(i);
        }

        return determinant;
    }

    private double getDeterminant(int columnIndex) {
        int minorSize = rows.length - 1;
        Matrix minorMatrix = new Matrix(minorSize, minorSize);

        int minorRow = 0;

        for (int i = 1; i < rows.length; i++) {
            int minorColumn = 0;

            for (int j = 0; j < rows.length; j++) {
                if (j != columnIndex) {
                    minorMatrix.rows[minorRow].setCoordinate(minorColumn, rows[i].getCoordinate(j));
                    minorColumn++;
                }
            }

            minorRow++;
        }

        return minorMatrix.getDeterminant();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        int maxIndex = rows.length - 1;

        for (int i = 0; i < maxIndex; i++) {
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[maxIndex]).append('}');
        return stringBuilder.toString();
    }

    public Vector multiply(Vector vector) {
        int rowsQuantity = rows.length;
        int columnsQuantity = getColumnsQuantity();

        if (vector.getSize() != columnsQuantity) {
            throw new IllegalArgumentException(
                    "Matrix columns quantity = " + columnsQuantity
                            + ", vector size = " + vector.getSize()
            );
        }

        Vector multiplicationResult = new Vector(rowsQuantity);

        for (int i = 0; i < rowsQuantity; ++i) {
            multiplicationResult.setCoordinate(
                    i,
                    Vector.getScalarProduct(rows[i], vector)
            );
        }

        return multiplicationResult;
    }

    private void checkEqualitySizes(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsQuantity() != matrix.getColumnsQuantity()) {
            throw new IllegalArgumentException(
                    "Matrix dimensions are not equal. "
                            + "Matrix 1: " + rows.length + " rows, "
                            + getColumnsQuantity() + " columns. "
                            + "Matrix 2: " + matrix.rows.length + " rows, "
                            + matrix.getColumnsQuantity() + " columns."
            );
        }
    }

    public void add(Matrix matrix) {
        checkEqualitySizes(matrix);

        for (int i = 0; i < rows.length; ++i) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkEqualitySizes(matrix);

        for (int i = 0; i < rows.length; ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.checkEqualitySizes(matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.checkEqualitySizes(matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int rowsQuantity1 = matrix1.rows.length;
        int columnsQuantity1 = matrix1.getColumnsQuantity();
        int rowsQuantity2 = matrix2.rows.length;
        int columnsQuantity2 = matrix2.getColumnsQuantity();

        if (columnsQuantity1 != rowsQuantity2) {
            throw new IllegalArgumentException("Matrix 1 columns quantity = " + columnsQuantity1 + ", matrix 2 rows quantity = " + rowsQuantity2
            );
        }

        Matrix product = new Matrix(rowsQuantity1, columnsQuantity2);

        for (int i = 0; i < rowsQuantity1; ++i) {
            for (int j = 0; j < columnsQuantity2; ++j) {
                product.rows[i].setCoordinate(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j))
                );
            }
        }

        return product;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Matrix)) {
            return false;
        }

        Matrix matrix = (Matrix) object;

        if (rows.length != matrix.rows.length
                || getColumnsQuantity() != matrix.getColumnsQuantity()) {
            return false;
        }

        for (int i = 0; i < rows.length; ++i) {
            if (!rows[i].equals(matrix.rows[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;

        for (Vector row : rows) {
            result = 31 * result + row.hashCode();
        }

        return result;
    }
}