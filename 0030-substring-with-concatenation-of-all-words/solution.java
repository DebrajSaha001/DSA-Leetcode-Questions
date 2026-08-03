class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();

        int wordLen = words[0].length();
        int totWords = words.length;

        Map<String, Integer> target = new HashMap<>();

        for(String word : words) {
            target.put(word, target.getOrDefault(word, 0) + 1);
        }

        for(int offset = 0; offset < wordLen; offset++) {
            int left = offset;
            int right = offset;
            int count = 0;

            Map<String, Integer> window = new HashMap<>();

            while(right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if(target.containsKey(word)) {
                    window.put(word, window.getOrDefault(word,0) + 1);
                    count++;

                    while(window.get(word) > target.get(word)) {
                        String remove = s.substring(left,left+wordLen);
                        window.put(remove, window.get(remove) - 1);
                        left += wordLen;
                        count--;
                    }

                    if(count == totWords) {
                        ans.add(left);
                        String remove = s.substring(left,left+wordLen);
                        window.put(remove, window.get(remove) - 1);
                        left += wordLen;
                        count--;
                    }
                }

                else {
                    window.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return ans;
    }
}
