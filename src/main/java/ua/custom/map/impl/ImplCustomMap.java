package ua.custom.map.impl;

import ua.custom.map.CustomMap;

import java.util.*;

@SuppressWarnings("all")
public class ImplCustomMap<K, V> implements CustomMap<K, V> {
    private final float DEFAULT_LOAD_FACTOR = 0.75F;
    private final int TREEIFY_THRESHOLD = 8;
    private final int DEFAULT_MAP_SIZE = 16;
    private int capacity;
    private Node<K, V>[] table;
    private final Set<Node<K, V>> entrySet;
    private final Set<K> keySet;
    private final Collection<V> values;

    public ImplCustomMap() {
        table = new Node[DEFAULT_MAP_SIZE];
        entrySet = new HashSet<>();
        keySet = new HashSet<>();
        values = new ArrayList<>();
        capacity = 0;
    }

    private int getIndex(K key) {
        int n = table.length;
        return key.hashCode() & (n - 1);
    }

    @Override
    public V put(K key, V value) {
        if (capacity == DEFAULT_LOAD_FACTOR * table.length) {
            table = Arrays.copyOf(table, table.length * 2);
        }

        int index = getIndex(key);

        if (index < table.length) {
            if (table[index] == null) {
                table[index] = new Node(key.hashCode(), key, value, null);
                entrySet.add(table[index]);
                keySet.add(key);
                values.add(value);
                ++capacity;
            }
            return value;
        }

        return null;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        if (index < table.length) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    public Set<Node<K, V>> entrySet() {
        return entrySet;
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return Arrays.stream(table).allMatch(node -> node == null);
    }

    @Override
    public boolean containsKey(K key) {
        return Arrays.stream(table)
                .filter(node -> node != null)
                .anyMatch(node -> node.key.equals(key));
    }

    @Override
    public boolean containsValue(V value) {
        return Arrays.stream(table)
                .filter(node -> node != null)
                .anyMatch(node -> node.value.equals(value));
    }

    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        ImplCustomMap.Node<K, V> next;

        Node(int hash, K key, V value, ImplCustomMap.Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash && Objects.equals(key, node.key) && Objects.equals(value, node.value) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash, key, value, next);
        }

        @Override
        public String toString() {
            return this.key + " = " + this.value;
        }
    }
}
