import java.util.*;

public class Q4A {
    private Map<Integer,Integer> valueMap = new HashMap<>();
    private Map<Integer,Integer> countMap = new HashMap<>();
    private TreeMap<Integer, List<Integer>> frequencyMap = new TreeMap<>();
    private final int size;

    public Q4A(int capacity){
        size = capacity;
    }

    public int get(int key){
        if (valueMap.containsKey(key) == false || size == 0)
            return -1;

        int frequency = countMap.get(key);
        frequencyMap.get(frequency).remove(new Integer(key));

        if (frequencyMap.get(frequency).size()==0)
            frequencyMap.remove(frequency);

        frequencyMap.computeIfAbsent(frequency + 1, k -> new LinkedList<>()).add(key);
        countMap.put(key, frequency + 1);
        return valueMap.get(key);
    }

    public void put(int key, int value){
        if (valueMap.containsKey(key) == false && size > 0) {
            if (valueMap.size() == size) {
                int lowestCount = frequencyMap.firstKey();
                int keyToDelete = frequencyMap.get(lowestCount).remove(0);

                if (frequencyMap.get(lowestCount).size() == 0)
                    frequencyMap.remove(lowestCount);

                valueMap.remove(keyToDelete);
                countMap.remove(keyToDelete);

            }
            valueMap.put(key, value);
            countMap.put(key, 1);
            frequencyMap.computeIfAbsent(1, k -> new LinkedList<>()).add(key);
        }
        else if (size > 0){
            valueMap.put(key, value);
            int frequency = countMap.get(key);
            frequencyMap.get(frequency).remove(new Integer(key));

            if (frequencyMap.get(frequency).size()==0)
                frequencyMap.remove(frequency);

            frequencyMap.computeIfAbsent(frequency + 1, k -> new LinkedList<>()).add(key);
            countMap.put(key, frequency + 1);
        }
    }

}
