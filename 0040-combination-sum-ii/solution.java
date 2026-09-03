class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // Sort so duplicates are adjacent
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(int candidates[], int rem, int start, List<Integer> current) {
        // Found a valid combination
        if(rem == 0) {
            res.add(new ArrayList<>(current));
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            // Skip duplicates at the same level
            if(i > start && candidates[i] == candidates[i - 1])
                continue;

            // Since array is sorted, no need to continue
            if(candidates[i] > rem)
                break;

            // Choose
            current.add(candidates[i]);

            // Use i + 1 because each element can be used only once.
            backtrack(candidates, rem - candidates[i], i + 1, current);
            
            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}
