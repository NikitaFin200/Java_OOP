package ru.academits.findyurov.arraylist.arrayList;

import ru.academits.findyurov.arraylist.interfaces.Iterator;
import ru.academits.findyurov.arraylist.interfaces.List;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    public static final int capacity = 10;
    private T[] array;
    private int size;

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be more than 0! Your capacity:" + capacity);
        }

        array = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void ensureCapacity(int capacity) {
        if (capacity > array.length) {
            int newCapacity = array.length * 2;

            if (newCapacity < capacity) {
                newCapacity = capacity;
            }

            array = Arrays.copyOf(array, newCapacity);
        }
    }

    @Override
    public void trimToSize(int size) {
        if (size < array.length) {
            T[] newArray = (T[]) new Object[size];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return o.toString().indexOf((Integer) o) != -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }

        System.arraycopy(array, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        ensureCapacity(size + 1);
        array[size++] = t;

        return true;
    }

    @Override
    public boolean remove(Object e) {
        int index = e.toString().indexOf((Integer) e);

        if (index != -1) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> e) {
        for (Object o : e) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return array[index];
    }

    @Override
    public T set(int index, T element) {
        checkRange(index);
        T oldElement = array[index];
        array[index] = element;
        return oldElement;
    }

    @Override
    public void checkRange(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index:" + index + ". Size:" + size);
        }
    }

    @Override
    public void print() {
        if (size == 0) {
            throw new IllegalArgumentException("Size = " + 0);
        }

        for (int i = 0; i < size - 1; i++) {
            System.out.print(array[i] + ", ");
        }

        System.out.println(array[size - 1]);
    }

    private class MyIterator implements Iterator<T> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }

            return array[cursor++];
        }
    }
}