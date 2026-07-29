class Solution {
    public String smallestPalindrome(String s, long k) {
        int freq[] = new int[26];

        for(char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        char mid = 0;

        for(int i = 0; i < 26; i++) {
            if(freq[i] % 2 == 1)
                mid = (char)('a' + i);

            for(int j = 0; j < freq[i] / 2; j++) {
                left.append((char)('a' + i));
            }
        }

        int halfFreq[] = new int[26];

        for(char ch : left.toString().toCharArray()) {
            halfFreq[ch - 'a']++;
        }

        long total = countFromFrequency(halfFreq);
        if(k > total)
            return "";

        String half = findKth(left.toString(), k);
        StringBuilder ans = new StringBuilder();
        ans.append(half);
        if(mid != 0)
            ans.append(mid);

        ans.append(new StringBuilder(half).reverse());
        return ans.toString();
    }

    private String findKth(String str, long k) {
        int freq[] = new int[26];

        for(char ch : str.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder res = new StringBuilder();

        for(int pos = 0; pos < str.length(); pos++) {
            for(int ch = 0; ch < 26; ch++) {
                if(freq[ch] == 0)
                    continue;

                freq[ch]--;
                long count = countFromFrequency(freq);
                if(k > count) {
                    k -= count;
                    freq[ch]++;
                }

                else {
                    res.append((char)('a' + ch));
                    break;
                }
            }
        }

        return res.toString();
    }

    private long countFromFrequency(int freq[]) {
        long result = 1;
        int remaining = 0;
    
        for(int x : freq) {
            remaining += x;
        }

        for(int i = 0; i < 26; i++) {
            int take = freq[i];
            if(take == 0)
                continue;

            for(int j = 1; j <= take; j++) {
                long numerator = remaining - take + j;
                long gcd = gcd(result, j);
                result /= gcd;
                long divisor = j / gcd;
    
                if(result > Long.MAX_VALUE / numerator)
                    return 1000000000000000000L;

                result *= numerator;
                result /= divisor;


                if(result > 1000000000000000000L)
                    return 1000000000000000000L;
            }
            
            remaining -= take;
        }

        return result;
    }

    private long gcd(long a, long b) {
        while(b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}

