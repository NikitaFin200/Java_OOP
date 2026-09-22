package ru.academits.findyurov.hashTable.hashTable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private int size; // размер массива хэш-таблицы
    private ArrayList<LinkedList<T>> table; // сама хэш-таблица

    // конструктор с заданием размера массива
    public HashTable(int size) {
        this.size = size;
        this.table = new ArrayList<LinkedList<T>>(size);

        for(int i = 0; i < size; i++) {
            table.add(new LinkedList<T>());
        }
    }

    // методы из интерфейса Collection<T>
    public boolean add(T item) {
        int index = getIndex(item);

        LinkedList<T> list = table.get(index);

        if(!list.contains(item)) {
            return list.add(item);
        }

        return false;
    }

    public boolean remove(Object o) {
        int index = getIndex((T) o);
        LinkedList<T> list = table.get(index);
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    public boolean contains(Object o) {
        int index = getIndex((T) o);
        LinkedList<T> list = table.get(index);
        return list.contains(o);
    }

    public int size() {
        int count = 0;

        for(LinkedList<T> list : table) {
            count += list.size();
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public Iterator<T> iterator() {
        ArrayList<T> items = new ArrayList<T>(size());

        for(LinkedList<T> list : table) {
            for(T item : list) {
                items.add(item);
            }
        }

        return items.iterator();
    }

    public Object[] toArray() {
        Object[] result = new Object[size()];
        int i = 0;

        for(T item : this) {
            result[i++] = item;
        }

        return result;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    // вспомогательный метод для определения индекса элемента в массиве хэш-таблицы
    private int getIndex(T item) {
        int hashCode = item.hashCode();
        return Math.abs(hashCode % size);
    }
}