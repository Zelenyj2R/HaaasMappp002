public interface InMyMap<K,V> {

        void put(K key, V value);
        void remove(Object key);
        void clear();
        int size();
        V get(Object key);

}
