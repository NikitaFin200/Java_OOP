package ru.academits.findyurov.arraylist.interfaces;

import java.util.Collection;

public interface List<T> {
    public static final int capacity = 0;

    void ensureCapacity(int size);

    public void trimToSize(int size);

    public int size();

    public boolean isEmpty();

    public boolean contains(Object o);

    public Object[] toArray();

    public <T> T[] toArray(T[] a);

    public boolean add(T t);

    public boolean remove(Object e);

    public boolean containsAll(Collection<?> e);

    public T get(int index);

    public T set(int index, T element);

    public void checkRange(int index);

    public void print();
}