package ru.academits.findyurov;

import ru.academits.findyurov.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsQuantity, int columnsQuantity) {
        if (rowsQuantity <= 0) {
            throw new IllegalArgumentException("Incorrect rows quantity." +
                    "The number must be greater than 0. Your value = " + rowsQuantity);
        }

        if (columnsQuantity <= 0) {
            throw new IllegalArgumentException("Incorrect columns quantity." +
                    "The number must be greater than 0. Your value = " + rowsQuantity);
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

        rows = new Vector[array.length];

        int columnsQuantity = 0;

        for (double[] row : array) {
            if (row.length > columnsQuantity) {
                columnsQuantity = row.length;
            }
        }

        if (columnsQuantity == 0) {
            throw new IllegalArgumentException("Quantity of columns is 0");
        }

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
        //return rows[getRowsQuantity() - 1].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("Index went abroad. Your index = " + index
                    + ". Minimal index = 0. Maximal index = " + (rows.length - 1));
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("Index went abroad. Your index = "
                    + index + ". Minimal index = 0. Maximal index = " + (rows.length - 1));
        }

        if (vector.getSize() != rows.length + 1) {
            throw new IllegalArgumentException("The sizes are not equal");
        }

        int size = vector.getSize();

        for (int i = 0; i < size; ++i) {
            rows[index].setCoordinate(i, vector.getCoordinate(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsQuantity()) {
            throw new ArrayIndexOutOfBoundsException("Illegal index of row. Your index = " + index
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

        Vector[] vectors = new Vector[columnsQuantity];

        for (int i = 0; i < getColumnsQuantity(); i++) {
            vectors[i] = getColumn(i);
        }

        rows = new Vector[vectors.length];//меняем размер основной матрицы


        for (int i = 0; i < vectors.length; ++i) {
            rows[i] = new Vector(vectors[0].getSize()); //меняем размеры векторов
        }

        //меняем основной массив векторов
        if (getColumnsQuantity() + 1 >= 0) {
            System.arraycopy(vectors, 0, rows, 0, getColumnsQuantity() + 1);
        }
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

        int decompositionIndex = 0;
        double determinant = 0;

        for (int i = 0; i < rowsQuantity; i++) {
            determinant += Math.pow(-1, i) * rows[i].getCoordinate(decompositionIndex) *
                    getDeterminant(this, i, decompositionIndex);
        }

        return determinant;
    }

    private static double getDeterminant(Matrix matrix, int rowIndex, int columnIndex) {
        int minorSize = matrix.rows.length - 1;
        Matrix minor = new Matrix(minorSize, minorSize);

        for (int i = 0, columnIndexMinor = 0; i < matrix.rows.length; i++) {
            if (i != rowIndex) {
                for (int j = 0, rowIndexMinor = 0; j < matrix.rows.length; j++) {
                    if (j != columnIndex) {
                        minor.rows[columnIndexMinor].setCoordinate(rowIndexMinor, matrix.rows[i].getCoordinate(j));
                        rowIndexMinor++;

                        if (rowIndexMinor == minorSize) {
                            rowIndexMinor = 0;
                            columnIndexMinor++;
                        }
                    }
                }
            }
        }

        return minor.getDeterminant();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        int max = rows.length - 1;

        for (int i = 0; i < max; i++) {
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[max]).append("}");
        return stringBuilder.toString();
    }

    public Vector multiply(Vector vector) {
        int rowsQuantity = rows.length;
        int columnsQuantity = getColumnsQuantity();

        if (vector.getSize() != rows.length) {
            throw new IllegalArgumentException("The rows quantity does not match the size. rowsQuantity = "
                    + rowsQuantity + " columnsQuantity = " + columnsQuantity +
                    " vector size = " + vector.getSize());
        }

        Vector multiplyResult = new Vector(rowsQuantity);

        for (int i = 0; i < rowsQuantity; ++i) {
            double sum = 0;

            for (int j = 0; j < columnsQuantity; ++j) {
                sum += Vector.getScalarProduct(rows[i], vector);
            }

            multiplyResult.setCoordinate(i, sum);
        }

        return multiplyResult;
    }

    public void checkEqualitySizes(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsQuantity() != matrix.getColumnsQuantity()) {
            throw new IllegalArgumentException("Now the dimensions of the matrix are not equal. Quantity rows matrix1 = "
                    + rows.length + ", Quantity columns matrix1 = " + getColumnsQuantity()
                    + ". Quantity rows matrix2 = " + matrix.rows.length
                    + ", Quantity columns matrix2 = " + matrix.getColumnsQuantity());
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

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.checkEqualitySizes(matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }


    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.checkEqualitySizes(matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int rowsQuantity1 = matrix1.rows.length;
        int rowsQuantity2 = matrix2.rows.length;
        int columnsQuantity1 = matrix1.getColumnsQuantity();
        int columnsQuantity2 = matrix2.getColumnsQuantity();

        if (rowsQuantity1 != columnsQuantity2 && rowsQuantity2 != columnsQuantity1) {
            throw new IllegalArgumentException("The rows quantity does not match the size. rowsQuantity1 = "
                    + rowsQuantity1 + ", columnsQuantity1 = " + columnsQuantity1 +
                    ", rowsQuantity2" + rowsQuantity2 + ", columnsQuantity2" + columnsQuantity2);
        }

        int productRowsQuantity = Math.max(rowsQuantity1, rowsQuantity2);
        int productColumnsQuantity = Math.max(columnsQuantity1, columnsQuantity2);

        Matrix product = new Matrix(productRowsQuantity, productColumnsQuantity);

        for (int i = 0; i < rowsQuantity1; ++i) {
            for (int j = 0; j < rowsQuantity2; ++j) {
                product.rows[i].setCoordinate(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return product;
    }
}