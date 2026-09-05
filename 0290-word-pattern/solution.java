class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        // Number of characters must equal number of words
        if (pattern.length() != words.length)
            return false;

        HashMap<Character, String> charToWord = new HashMap<>();
        HashMap<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = words[i];

            // Character already has a mapping
            if (charToWord.containsKey(ch)) {
                if (!charToWord.get(ch).equals(word))
                    return false;
            }

            // Word already has a mapping
            if (wordToChar.containsKey(word)) {
                if (wordToChar.get(word) != ch)
                    return false;
            }

            // Create mappings
            charToWord.put(ch, word);
            wordToChar.put(word, ch);
        }

        return true;
    }
}

