class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();

        // First part
        for(int i = 1; i <= 3 && i < n; i++) {
            String part1 = s.substring(0, i);

            if(!isValid(part1))
                continue;

            // Second part
            for(int j = i + 1; j <= i + 3 && j < n; j++) {
                String part2 = s.substring(i, j);

                if(!isValid(part2))
                    continue;

                // Third part
                for(int k = j + 1; k <= j + 3 && k < n; k++) {
                    String part3 = s.substring(j, k);
                    String part4 = s.substring(k);

                    if(isValid(part3) && isValid(part4))
                        res.add(part1 + "." + part2 + "." + part3 + "." + part4);
                }
            }
        }

        return res;
    }

    private boolean isValid(String part) {
        // More than 3 digits
        if(part.length() > 3)
            return false;

        // Leading zero
        if(part.length() > 1 && part.charAt(0) == '0')
            return false;

        int value = Integer.parseInt(part);
        return value <= 255;
    }
}
