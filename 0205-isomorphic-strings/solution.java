class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] mapST = new int[256];
        int[] mapTS = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);

            // If a mapping already exists, it must match
            if (mapST[a] != 0 && mapST[a] != b)
                return false;

            // Reverse mapping must also match
            if (mapTS[b] != 0 && mapTS[b] != a)
                return false;

            // Storing both the mappings
            mapST[a] = b;
            mapTS[b] = a;
        }

        return true;
    }
}

