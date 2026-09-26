class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1 || numRows >= s.length())
            return s;

        StringBuilder rows[] = new StringBuilder[numRows];

        for(int i = 0; i < numRows; i++)
            rows[i] = new StringBuilder();

        int row = 0;
        int direction = 1;

        for(char ch : s.toCharArray()) {
            // Put character in current row
            rows[row].append(ch);

            // Change the direction at top or bottom
            if(row == 0)
                direction = 1;
            else if(row == numRows - 1)
                direction = -1;

            // Moving to the next row
            row += direction;
        }

        // Combining all the rows
        StringBuilder res = new StringBuilder();

        for(StringBuilder currentRow : rows)
            res.append(currentRow);

        return res.toString();
    }
}
