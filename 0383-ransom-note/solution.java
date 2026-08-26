class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];

        // Counting the characters in magazine
        for(char ch : magazine.toCharArray())
            count[ch - 'a']++;

        // Using the characters from ransomNote
        for(char ch : ransomNote.toCharArray()) {
            count[ch - 'a']--;

            // Not enough of this character
            if(count[ch - 'a'] < 0)
                return false;
        }

        return true;
    }
}
