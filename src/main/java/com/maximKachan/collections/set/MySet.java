package com.maximKachan.collections.set;

import java.util.*;

public class MySet<E> extends AbstractSet<E> {
    private int size = 0;
    private Object[] elements;
    private static final int INITIAL_CAPACITY = 20;
    private static final double MAX_POPULATE_LEVEL = 0.8;
    private int population = 0;

    public MySet(){
        elements = new Object[INITIAL_CAPACITY];
    }

    private void initSet(){
        population = 0;
        size = 0;
        Object[] tempElem = elements;
        elements = new Object[elements.length*2];
        for (Object o : tempElem){
            if (o != null){
                List<E> list = (List<E>) o;
                for(E elem : list){
                    add(elem);
                }
            }
        }
        checkPopulation();
    }

    @Override
    public Iterator<E> iterator() {
        return new MySetIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        int index = e.hashCode() % elements.length;
        if (elements[index] == null){
            List<E> list = new ArrayList<>();
            list.add(e);
            elements[index] = list;
            population++;
            size++;
        } else {
            List<E> list = (ArrayList<E>) elements[index];
            if (!list.contains(e)) {
                list.add(e);
                size++;
            } else {
                return false;
            }
        }
        checkPopulation();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = o.hashCode() % elements.length;
        if (elements[index] != null) {
            List<E> list = (List<E>) elements[index];
            list.remove(o);
            return true;
        } else {
            return false;
        }
    }

    private void checkPopulation(){
        if (1.0*population/elements.length > MAX_POPULATE_LEVEL){
            initSet();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E e : (E[]) elements){
            if (e != null){
                sb.append(e);
            }
        }
        return sb.toString();
    }

    private class MySetIterator implements Iterator<E>{
        private int cursor;
        private final List<E> unitedList = new ArrayList<>();

        public MySetIterator(){
            for (Object o : elements){
                if (o != null){
                    List<E> list = (List<E>) o;
                    unitedList.addAll(list);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return cursor < unitedList.size();
        }

        @Override
        public E next() {
            return unitedList.get(cursor++);
        }
    }
}
