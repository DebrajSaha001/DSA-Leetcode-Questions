class RandomizedSet {
    private ArrayList<Integer> list;
    private HashMap<Integer, Integer> map;
    private Random random;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        // Already present
        if (map.containsKey(val))
            return false;

        // Storing its index
        map.put(val, list.size());
        // Adding it to the list
        list.add(val);

        return true;
    }

    public boolean remove(int val) {
        // Not present
        if (!map.containsKey(val))
            return false;

        // Index of the element to remove
        int index = map.get(val);

        // Last element
        int lastValue = list.get(list.size() - 1);

        // Move the last element into the removed element's position
        list.set(index, lastValue);

        // Update the last element's new index
        map.put(lastValue, index);

        // Removing the last element
        list.remove(list.size() - 1);

        // Removing the value from the map
        map.remove(val);

        return true;
    }

    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
