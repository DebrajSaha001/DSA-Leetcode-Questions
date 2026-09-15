class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k, new ArrayList<>());
        return res;
    }

    private void backtrack(int start, int n, int k, List<Integer> curr) {
        // After selecting k numbers
        if(curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }

        // Trying every possible next number
        for(int i = start; i <= n; i++) {
            // Choose
            curr.add(i);
            // Explore
            backtrack(i + 1, n, k, curr);
            // Undo
            curr.remove(curr.size() - 1);
        }
    }
}
