class Solution {
    static class FastMap {
        int[] keys;
        int[] values;
        boolean[] exists;
        int size;

        FastMap(int n) {
            size = 1;
            while (size < n * 4) {
                size *= 2;
            }

            keys = new int[size];
            values = new int[size];
            exists = new boolean[size];
        }

        int getIndex(int key) {
            int index = key & (size - 1);
            while (exists[index] && keys[index] != key) {
                index = (index + 1) & (size - 1);
            }
            return index;
        }

        void add(int key, int value) {
            int index = getIndex(key);
            if (!exists[index]) {
                exists[index] = true;
                keys[index] = key;
            }
            values[index] += value;
        }

        int get(int key) {
            int index = getIndex(key);
            if (exists[index]) {
                return values[index];
            }
            return 0;
        }
    }

    public int maxSubarrayLength(int[] nums, int k) {
        FastMap frequency = new FastMap(nums.length);
        int left = 0;
        int answer = 0;

        for (int right = 0; right < nums.length; right++) {
            int current = nums[right];
            // Add current number
            frequency.add(current, 1);
            // Remove elements until window becomes valid
            while (frequency.get(current) > k) {
                frequency.add(nums[left], -1);
                left++;
            }
            // Update maximum window size
            answer = Math.max(answer, right - left + 1);
        }
        return answer;
    }
}
