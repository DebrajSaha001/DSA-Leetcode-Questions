class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Counting the frequencies
        HashMap<Integer, Integer> freq = new HashMap<>();

        for(int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);

        // Minimum Heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> freq.get(a) - freq.get(b)
        );

        // Keep only k most frequencies elements
        for(int num : freq.keySet()) {
            minHeap.offer(num);

            if(minHeap.size() > k)
                minHeap.poll();
        }

        // Building the answer
        int res[] = new int[k];
        for(int i = 0; i < k; i++)
            res[i] = minHeap.poll();

        return res;
    }
}

