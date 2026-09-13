class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        // Count available digits
        for (int digit : digits)
            freq[digit]++;

        int answer = 0;

        // Hundreds digit
        for (int first = 1; first <= 9; first++) {
            if (freq[first] == 0)
                continue;

            // Tens digit
            for (int second = 0; second <= 9; second++) {
                if (freq[second] == 0)
                    continue;

                // Even last digit
                for (int third = 0; third <= 8; third += 2) {
                    if (freq[third] == 0)
                        continue;

                    // Check if we have enough copies
                    if (first == second && second == third) {
                        if (freq[first] < 3)
                            continue;
                    }

                    else if (first == second) {
                        if (freq[first] < 2)
                            continue;
                    }

                    else if (first == third) {
                        if (freq[first] < 2)
                            continue;
                    }

                    else if (second == third) {
                        if (freq[second] < 2)
                            continue;
                    }
                    answer++;
                }
            }
        }
        return answer;
    }
}
