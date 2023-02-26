package Question4;
import java.util.*;

/*
Design and Implement LFU caching

 */
public class LFUCache<K, V> {

    private final int capacity;
    private final Map<K, V> cache;  // stores key-value pairs
    private final Map<K, Integer> freq;  // stores frequency of each key
    private final Map<Integer, LinkedHashSet<K>> freqToKeys;  // stores keys with the same frequency
    private int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        freq = new HashMap<>();
        freqToKeys = new HashMap<>();
        minFreq = 0;
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }

        // increment frequency of key
        int frequency = freq.get(key);
        freq.put(key, frequency + 1);

        // move key to a new frequency list if necessary
        freqToKeys.get(frequency).remove(key);
        if (freqToKeys.get(frequency).isEmpty() && frequency == minFreq) {
            minFreq++;
        }
        freqToKeys.computeIfAbsent(frequency + 1, k -> new LinkedHashSet<>()).add(key);

        return cache.get(key);
    }

    public void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }

        // check if key is already in cache
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);  // increment frequency of key
            return;
        }

        // remove least frequently used key if cache is full
        if (cache.size() >= capacity) {
            K leastFreqKey = freqToKeys.get(minFreq).iterator().next();
            freqToKeys.get(minFreq).remove(leastFreqKey);
            cache.remove(leastFreqKey);
            freq.remove(leastFreqKey);
        }

        // add new key to cache
        cache.put(key, value);
        freq.put(key, 1);
        minFreq = 1;
        freqToKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
    }
    public static void main(String[] args) {
        LFUCache<Integer, String> cache = new LFUCache<>(3);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        System.out.println(cache.get(1));  // prints "one"
        System.out.println(cache.get(2));  // prints "two"
        System.out.println(cache.get(3));  // prints "three"
        cache.put(4, "four");
        System.out.println(cache.get(1));  // prints "one"
        System.out.println(cache.get(2));  // prints "two"
        System.out.println(cache.get(3));  // prints "null"
        System.out.println(cache.get(4));  // prints "four"
    }

}
