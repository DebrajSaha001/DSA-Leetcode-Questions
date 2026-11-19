class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Case 1: Buy type 1 and type 2 separately
        long case1 = (long) cost1 * need1 + (long) cost2 * need2;

        // Case 2: Buy type 3 for the common requirement
        long common = Math.min(need1, need2);

        long case2 = common * costBoth + (long) (need1 - common) * cost1 + (long) (need2 - common) * cost2;

        // Case 3: Buy only type 3 items
        long case3 = (long) Math.max(need1, need2) * costBoth;

        return Math.min(case1, Math.min(case2, case3));
    }
}