class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String parts[] = path.split("/");

        for(String part : parts) {
            // Ignoring the empty strings and "."
            if(part.equals("") || part.equals("."))
                continue;
            // Towards parent directory
            if(part.equals("..")) {
                if(!stack.isEmpty())
                    stack.pop();
            } else {
                // normal directory/file name
                stack.push(part);
            }
        }
        // Building the canonical path
        StringBuilder res = new StringBuilder();
        
        for(String directory : stack) 
            res.append("/").append(directory);
        // If the stack is empty, then we are at the root
        if(res.length() == 0)
            return "/";

        return res.toString();
    }
}
