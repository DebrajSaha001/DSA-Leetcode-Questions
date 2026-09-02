class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        int write = 0;

        for (int read = 1; read < intervals.length; read++) {
            if (intervals[read][0] <= intervals[write][1]) {
                if (intervals[read][1] > intervals[write][1])
                    intervals[write][1] = intervals[read][1];
            }

            else
                intervals[++write] = intervals[read];
        }

        return Arrays.copyOf(intervals, write + 1);
    }
}

