package ua.custom.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public interface CustomMap<K, V> {
    V put(K key, V value);
    V get(K key);
    void remove(K key);
    Set<K> keySet();
    Collection<V> values();
    int size();
    boolean isEmpty();
    boolean containsKey(K key);
    boolean containsValue(V value);
    boolean equals(Object obj);
    int hashCode();
}
