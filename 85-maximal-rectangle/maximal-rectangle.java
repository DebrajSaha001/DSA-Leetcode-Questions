class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0)
            return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int height[] = new int[cols];
        int maxArea = 0;

        for(int r = 0; r < rows; r++) {
            // Building the histogram heights for this row
            for(int c = 0; c < cols; c++) {
                if(matrix[r][c] == '1')
                    height[c]++;

                else
                    height[c] = 0;
            }

            // Finding the largest rectangle in this histogram
            maxArea = Math.max(maxArea, largestRectangle(height));
        }

        return maxArea;
    }

    private int largestRectangle(int heights[]) {
        int n = heights.length;
        int maxArea = 0;

        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while(!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }
}