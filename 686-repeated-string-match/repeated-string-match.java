class Solution {
    public int repeatedStringMatch(String a, String b) {
        StringBuilder repeated = new StringBuilder();
        int count = 0;

        // Repeat until the length is at least b.length()
        while(repeated.length() < b.length()) {
            repeated.append(a);
            count++;
        }

        // Check whether b is present
        if(repeated.toString().contains(b))
            return count;

        // Try one additional repetition
        repeated.append(a);
        count++;

        if(repeated.toString().contains(b))
            return count;

        return -1;
    }
}
