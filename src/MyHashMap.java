public class MyHashMap<K, V> extends Result <K, V> implements InMyMap<K,V>   {
    private int size;
    private int capacity = 16;

    private Result<K, V>[] table = new Result[capacity];

    

    @SuppressWarnings("unchecked")
    public void MyHashMap(){
        table = new Result[capacity];

    }

    public MyHashMap(K key, V value, Result<K, V> next) {
        super(key, value, next);
    }


    @Override
    public void put(K key, V value) {
        int index = index(key);
        Result<K, V> newEntry = new Result<>(key, value, null);
        if(table[index] == null){
            table[index] = newEntry;
        }else {
            Result<K, V> previousNode = null;
            Result<K, V> currentNode = table[index];
            while(currentNode != null){
                if(currentNode.getKey().equals(key)){
                    currentNode.setValue(value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if(previousNode != null)
                previousNode.setNext(newEntry);
        }

    }
    @Override
    public void remove(Object key) {
        int index = index((K) key);
        Result previous = null;
        Result entry = table[index];
        while (entry != null){
            if(entry.getKey().equals(key)){
                if(previous == null){
                    entry = entry.getNext();
                    table[index] = entry;
                }else {
                    previous.setNext(entry.getNext());
                }
                return;
            }
            previous = entry;
            entry = entry.getNext();
        }

    }
    @Override
    public void clear() {
        table = new Result[capacity];
        size = 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public V get(Object key) {
        V value = null;
        int index = index((K) key);
        Result<K, V> entry = table[index];
        while (entry != null){
            if(entry.getKey().equals(key)) {
                value = entry.getValue();
                break;
            }
            entry = entry.getNext();
        }
        return value;
    }
    public void display(){
        for(int i = 0; i < capacity; i++){
            if(table[i] != null){
                Result<K, V> currentNode = table[i];
                while (currentNode != null){
                    System.out.println(String.format("Key is %s and value is %s", currentNode.getKey(), currentNode.getValue()));
                    currentNode = currentNode.getNext();
                }
            }
        }
    }
    private int index(K key){
        if(key == null){
            return 0;
        }
        return Math.abs(key.hashCode() % capacity);
    }
}
