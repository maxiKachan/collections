package com.maximKachan.collections.list;

import java.util.*;
import java.util.function.Consumer;

public class MyOwnArrayList<E> implements List<E> {
    private Object[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 20;

    public MyOwnArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    public MyOwnArrayList(int size) {
        this.size = size;
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
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyOwnIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }
        System.arraycopy(elements, 0, a, 0, size);
        return a;
    }

    @Override
    public boolean add(E e) {
        checkCapacity();
        elements[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                moveElemDelete(i);
                size--;
                return true;
            }
        }
        return false;
    }

    private void moveElemDelete(int deletedElem) {
        System.arraycopy(elements, deletedElem + 1, elements, deletedElem, size - 1 - deletedElem);
    }

    private void moveElemInsert(int insertIndex) {
        System.arraycopy(elements, insertIndex, elements, insertIndex + 1, size - insertIndex);
    }

    private void checkCapacity(){
        if (size >= elements.length) {
            expandCapacity();
        }
    }

    private void expandCapacity() {
        Object[] tempElem = elements;
        elements = new Object[elements.length * 2];
        System.arraycopy(tempElem, 0, elements, 0, tempElem.length);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c){
            this.add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        for (E e : c){
            this.add(index++, e);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c){
            if (contains(o)) {
                remove(o);
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object o : c){
            if (!contains(o)){
                remove(o);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        elements[index] = element;
        return element;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        checkCapacity();
        moveElemInsert(index);
        elements[index] = element;
        size++;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E e = (E) elements[index];
        moveElemDelete(index);
        size--;
        return e;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elements[i])) return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyOwnListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyOwnListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        MyOwnArrayList<E> subList = new MyOwnArrayList<>();
        System.arraycopy(this.elements, fromIndex, subList.elements, 0, toIndex-fromIndex);
        return subList;
    }

    private class MyOwnIterator implements Iterator<E> {
        int cursor = 0;
        int previous = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            previous = cursor;
            return (E) elements[cursor++];
        }

        @Override
        public void remove() {
            MyOwnArrayList.this.remove(cursor);
            cursor--;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    private class MyOwnListIterator extends MyOwnIterator implements ListIterator<E> {

        public MyOwnListIterator(int starIndex) {
            cursor = starIndex;
        }

        @Override
        public boolean hasPrevious() {
            return previous > -1;
        }

        @Override
        public E previous() {

            if (previous == -1) {
                throw new NoSuchElementException();
            }
            cursor--;
            return (E) elements[previous--];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return previous;
        }

        @Override
        public void set(E e) {
            MyOwnArrayList.this.set(cursor, e);
        }

        @Override
        public void add(E e) {
            MyOwnArrayList.this.add(e);
        }
    }

    @Override
    public String toString() {
        Object[] printArray = new Object[size];
        System.arraycopy(elements, 0, printArray, 0, size);
        return  Arrays.toString(printArray);
    }
}
