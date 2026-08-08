class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> set = new HashMap<>();

        for(int num : nums1)
            set.put(num, set.getOrDefault(num, 0) + 1);

        ArrayList<Integer> lst = new ArrayList<>();
        for(int num : nums2) {
            if(set.containsKey(num) && set.get(num) > 0) {
                lst.add(num);
                set.put(num, set.get(num) - 1);
            }
        }

        int ans[] = new int[lst.size()];
        int idx = 0;

        for(int i = 0; i < lst.size(); i++)
            ans[i] = lst.get(i);

        return ans;
    }
}
