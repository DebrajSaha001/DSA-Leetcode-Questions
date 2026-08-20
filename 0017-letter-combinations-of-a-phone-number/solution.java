class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        if(digits.length() == 0)
            return res;

        String map[] = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
        };

        backtrack(0, digits, "", map, res);
        return res;
    }

    private void backtrack(int idx, String digits, String current, String map[], List<String> res) {
        // selecting one letter for every digit
        if(idx == digits.length()) {
            res.add(current);
            return;
        }
        // getting the letters corresponding to the digit
        String letters = map[digits.charAt(idx) - '0'];
        // trying every possible letter
        for(char ch : letters.toCharArray()) {
            // choosing the letter
            backtrack(idx + 1, digits, current + ch, map, res);
        }
    }
}

