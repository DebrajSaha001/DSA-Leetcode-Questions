class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Encode: value in higher bits, index in lower bits
        long[] arr = new long[n];

        for (int i = 0; i < n; i++)
            arr[i] = ((long) nums[i] << 32) | (i & 0xffffffffL);

        // Primitive array sort: much faster than sorting objects
        Arrays.sort(arr);
        int[] result = new int[n];
        int[] indices = new int[n];
        int start = 0;

        while (start < n) {
            int end = start;
            // Find the connected group
            while (end + 1 < n) {
                int currentValue = (int) (arr[end] >> 32);
                int nextValue = (int) (arr[end + 1] >> 32);
                if ((long) nextValue - currentValue > limit)
                    break;

                end++;
            }

            int size = end - start + 1;
            // Collect original indices
            for (int i = 0; i < size; i++)
                indices[i] = (int) arr[start + i];

            // Sort only the indices of this group
            Arrays.sort(indices, 0, size);

            // Assign sorted values to sorted indices
            for (int i = 0; i < size; i++)
                result[indices[i]] = (int) (arr[start + i] >> 32);

            start = end + 1;
        }

        return result;
    }
}
