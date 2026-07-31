class Solution {
    public boolean rotateString(String s1, String goal) {
        if(s1.length() != goal.length())
            return false;

        String concat = s1 + s1;

        return concat.contains(goal);
    }
}
