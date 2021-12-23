package com.maximKachan.collections.map;

import com.maximKachan.collections.set.MySet;

import java.util.*;

public class MyMap<K,V> extends AbstractMap<K,V> {
    private int size = 0;
    private Object[] elements;
    private static final int INITIAL_CAPACITY = 20;
    private static final double MAX_POPULATE_LEVEL = 0.8;
    private int population = 0;

    public MyMap(){
        elements = new Object[INITIAL_CAPACITY];
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new MySet<>();
        for (Object o : elements){
            Entry<K, V> entry = (Entry<K, V>) o;
            entries.add(entry);
        }
        return entries;
    }

    private void initSet(){
        population = 0;
        size = 0;
        Object[] tempElem = elements;
        elements = new Object[elements.length*2];
        for (Object o : tempElem){
            if (o != null){
                List<Container<K, V>> list = (List<Container<K,V>>) o;
                for(Container<K, V> elem : list){
                    put(elem.getKey(), elem.getValue());
                }
            }
        }
        checkPopulation();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V put(K key, V value) {
        Container<K, V> container = new Container<>(key, value);
        int index = container.getKey().hashCode() % elements.length;
        List<Container<K, V>> bucket;
        if (elements[index] == null){
            bucket = new ArrayList<>();
            bucket.add(container);
            elements[index] = bucket;
            size++;
            population++;
        } else {
            bucket = (List<Container<K, V>>) elements[index];
            if (bucket.contains(container)){
                bucket.set(bucket.indexOf(container), container);
            } else {
                bucket.add(container);
                size++;
            }
        }
        checkPopulation();
        return value;
    }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % elements.length;
        if (elements[index] != null){
            List<Entry<K, V>> list = (List<Entry<K,V>>) elements[index];
            for (Entry<K, V> entry : list){
                if (entry.getKey().equals(key)) return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        int index = key.hashCode() % elements.length;
        if (elements[index] != null){
            List<Entry<K, V>> list = (List<Entry<K,V>>) elements[index];
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).getKey().equals(key)) {
                    V value = list.get(i).getValue();
                    list.remove(i);
                    return value;
                }
            }
        }
        return null;
    }

    private void checkPopulation(){
        if (1.0*population/elements.length > MAX_POPULATE_LEVEL){
            initSet();
        }
    }

    private static class Container<K, V> implements Map.Entry<K,V>{
        private final K key;
        private V value;

        public Container(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Container<?, ?> container = (Container<?, ?>) o;
            return Objects.equals(key, container.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MyMap \n{ \n");
        for (Object o : elements){
            if (o != null){
                List<Entry<K, V>> entries = (List<Entry<K,V>>) o;
                for (Entry<K, V> entry : entries){
                    sb.append("key=").append(entry.getKey()).append(" value=").append(entry.getValue()).append("\n");
                }
            }
        }
        sb.append("}");
        return  sb.toString();
    }
}
