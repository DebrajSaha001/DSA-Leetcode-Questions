class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates, target, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(int candidates[], int rem, int start, List<Integer> current) {
        // We found a valid combination
        if(rem == 0) {
            res.add(new ArrayList<>(current));
            return;
        }

        // Sum has exceeded target
        if(rem < 0)
            return;

        for(int i = start; i < candidates.length; i++) {
            // Don't choose a number bigger than remaining
            if(candidates[i] > rem)
                continue;

            // Choose
            current.add(candidates[i]);

            // i and not i + 1 because we can reuse the same number
            backtrack(candidates, rem - candidates[i], i, current);
            
            // Undo the choice
            current.remove(current.size() - 1);
        }
    }
}
