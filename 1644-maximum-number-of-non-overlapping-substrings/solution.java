class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int first[] = new int[26];
        int last[] = new int[26];

        Arrays.fill(first, -1);

        // Finding the first and the last occurrence
        for(int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if(first[c] == -1)
                first[c] = i;

            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Trying to create a valid interval for every character
        for(int c = 0; c < 26; c++) {
            if(first[c] == -1)
                continue;

            int start = first[c];
            int end = last[c];
            boolean valid = true;

            for(int i = start; i <= end; i++) {
                int current = s.charAt(i) - 'a';

                // This character appeared before start, so we cannot include all its occurences
                if(first[current] < start) {
                    valid = false;
                    break;
                }

                // Including all the occurences of the character
                end = Math.max(end, last[current]);
            }

            if(valid)
                intervals.add(new int[]{start, end});
        }

        // Sorting it by the ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));
        List<String> res = new ArrayList<>();
        int prevEnd = -1;

        // Greedily selecting non-overlapping intervals
        for(int interval[] : intervals) {
            int start = interval[0];
            int end = interval[1];

            if(start > prevEnd) {
                res.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return res;
    }
}
