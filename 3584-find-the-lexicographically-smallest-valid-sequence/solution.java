class Solution {
    public int[] validSequence(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int suff[] = new int[n + 1];
        Arrays.fill(suff, -1);

        suff[n] = m;
        int j = m - 1;

        for(int i = n - 1; i >= 0; i--) {
            while(j >= 0 && word1.charAt(j) != word2.charAt(i)) {
                j--;
            }
            if(j >= 0) {
                suff[i] = j;
                j--;
            }
        }

        int ans[] = new int[n];
        int idx = 0;
        boolean change = false;

        for(int i = 0; i < n; i++) {
            boolean found = false;

            while(idx < m) {
                if(word1.charAt(idx) == word2.charAt(i)) {
                    ans[i] = idx;
                    idx++;
                    found = true;
                    break;
                }

                else if(!change) {
                    if(i + 1 == m || suff[i + 1] > idx) {
                        ans[i] = idx;
                        idx++;
                        change = true;
                        found = true;
                        break;   
                    }
                }
                idx++;
            }
            
            if(!found) {
                return new int[0];
            }
        }

        return ans;
    }
}
