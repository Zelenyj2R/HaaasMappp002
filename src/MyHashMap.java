public class MyHashMap<K, V> implements InMyMap<K,V>   { // убрал extends Result <K, V>
    private int size=0; // на всякий случай сразу проинициализировал 0
    private int capacity = 16;

    private Result<K, V>[] table;

    public class Result <K, V> {
        private K key;
        private V value;
        private Result<K, V> next;

        public Result(K key, V value, Result<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey(int i) {
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


    //@SuppressWarnings("unchecked")
    public MyHashMap(){
        table = new Result[capacity];
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
                if(currentNode.getKey(110).equals(key)){
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
            if(entry.getKey(100).equals(key)){
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
            if(entry.getKey(100).equals(key)) {
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
                    System.out.println(String.format("Key is %s and value is %s", currentNode.getKey(100),
                            currentNode.getValue()));
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
