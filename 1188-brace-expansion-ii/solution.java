class Solution {
    int index = 0;
    public List<String> braceExpansionII(String expression) {
        Set<String> res = parseExpression(expression);

        List<String> list = new ArrayList<>(res);
        Collections.sort(list);
        return list;
    }

    // expression = term (',' term)*
    private Set<String> parseExpression(String s) {
        Set<String> result = parseTerm(s);

        while (index < s.length() && s.charAt(index) == ',') {
            index++; // skip ','
            Set<String> next = parseTerm(s);
            // Union
            result.addAll(next);
        }
        return result;
    }

    // term = factor factor factor...
    private Set<String> parseTerm(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length() && s.charAt(index) != ',' && s.charAt(index) != '}') {
            Set<String> current = parseFactor(s);

            // Concatenation
            Set<String> temp = new HashSet<>();
            for (String a : result) {
                for (String b : current)
                    temp.add(a + b);
            }

            result = temp;
        }

        return result;
    }

    // factor = letter OR {expression}
    private Set<String> parseFactor(String s) {
        // Letter
        if (s.charAt(index) != '{') {
            Set<String> result = new HashSet<>();
            result.add(String.valueOf(s.charAt(index)));
            index++;
            return result;
        }

        // '{'
        index++;
        Set<String> result = parseExpression(s);
        // '}'
        index++;
        return result;
    }
}
