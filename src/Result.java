public class Result <K, V> {
    private K key;
    private V value;
    private Result<K, V> next;

    public Result(K key, V value, Result<K, V> next){
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Result getNext() {
        return next;
    }

    public void setNext(Result<K, V> next) {
        this.next = next;
    }

}

