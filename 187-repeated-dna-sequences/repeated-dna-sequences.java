class Solution {
    private final byte[] SEEN = new byte[1 << 20]; 
    private final int MASK = (1 << 20) - 1; 
    private final byte[] VALUE_MAP = new byte['Z' - 'A' + 1];
    {
        VALUE_MAP['A' - 'A'] = 0;
        VALUE_MAP['C' - 'A'] = 1;
        VALUE_MAP['G' - 'A'] = 2;
        VALUE_MAP['T' - 'A'] = 3;
    }

    public List<String> findRepeatedDnaSequences(String str) {
        List<String> results = new ArrayList<>();
        char[] characters = str.toCharArray();
        int dna = 0;

        for(int i = 0; i < characters.length; i++) {
            char current = characters[i];
            int value = VALUE_MAP[current - 'A'];

            dna = ((dna << 2) | value) & MASK;
            
            if(i >= 9) {
                if(SEEN[dna] == 0)
                    SEEN[dna] = 1;
                
                else if((SEEN[dna] == 1)) {
                    results.add(new String(characters, i - 9, 10));
                    SEEN[dna] = 2;
                }
            }
        }

        return results;

        // Set<String> seen = new HashSet<>();
        // Set<String> repeat = new HashSet<>();

        // for(int i = 0; i <= s.length() - 10; i++) {
        //     String seq = s.substring(i , i + 10);

        //     if(seen.contains(seq))
        //         repeat.add(seq);

        //     else
        //         seen.add(seq);
        // }

        // return new ArrayList<>(repeat);
    }
}
