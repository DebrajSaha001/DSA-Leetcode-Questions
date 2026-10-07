class Solution {
    Set<String> res = new HashSet<>();
    public List<String> removeInvalidParentheses(String s) {
        int leftRemove = 0;
        int rightRemove = 0;

        // Finding the minimum number of '(' and ')' to remove
        for(char ch : s.toCharArray()) {
            if(ch == '(')
                leftRemove++;

            else if(ch == ')') {
                if(leftRemove > 0)
                    leftRemove--;

                else
                    rightRemove++;
            }
        }

        backtrack(s, 0, 0, leftRemove, rightRemove, new StringBuilder());
        return new ArrayList<>(res);
    }

    private void backtrack(String s, int index, int bal, int leftRemove, int rightRemove, StringBuilder path) {
        // Reaching the end
        if(index == s.length()){
            if(bal == 0 && leftRemove == 0 && rightRemove == 0)
                res.add(path.toString());

            return;
        }

        char ch = s.charAt(index);

        // Case 1: Letter
        if(ch != '(' && ch != ')') {
            path.append(ch);
            backtrack(s, index + 1, bal, leftRemove, rightRemove, path);
            path.deleteCharAt(path.length() - 1);
        }

        // Case 2: '('
        else if(ch == '(') {
            // Option 1: Remove '('
            if(leftRemove > 0)
                backtrack(s, index + 1, bal, leftRemove - 1, rightRemove, path);

            // Option 2: Keep ')'
            path.append('(');
            backtrack(s, index + 1, bal + 1, leftRemove, rightRemove, path);
            path.deleteCharAt(path.length() - 1);
        }

        // Case 3: ')'
        else {
            // Option 1: Remove '('
            if(rightRemove > 0)
                backtrack(s, index + 1, bal, leftRemove, rightRemove - 1, path);

            // Option 2: Keep ')'
            if(bal > 0) {
                path.append(')');
                backtrack(s, index + 1, bal - 1, leftRemove, rightRemove, path);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }
}