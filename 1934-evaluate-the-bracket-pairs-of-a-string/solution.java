class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        HashMap<String, String> map = new HashMap<>();

        // Storing the key -> value
        for(List<String> pair : knowledge)
            map.put(pair.get(0), pair.get(1));

        StringBuilder res = new StringBuilder();
        int i = 0;
        while(i < s.length()) {
            // Start of a bracket pair
            if(s.charAt(i) == '(') {
                int j = i + 1;
                
                // Finding the closing bracket
                while(s.charAt(j) != ')')
                    j++;

                // Extract key
                String key = s.substring(i + 1, j);

                // Get Value
                String val = map.getOrDefault(key, "?");

                // Adding value to the answer
                res.append(val);

                // Move past ')'
                i = j + 1;
            }

            // Normal Character
            else {
                res.append(s.charAt(i));
                i++;
            }
        }

        return res.toString();
    }
}
