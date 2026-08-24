class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;

        int left = 0;
        int right = matrix[0].length - 1;

        while(top <= bottom && left <= right) {
            // From left to right
            for(int j = left; j <= right; j++)
                res.add(matrix[top][j]);
            top++;

            // From top to bottom
            for(int i = top; i <= bottom; i++)
                res.add(matrix[i][right]);
            right--;

            // From Right to left
            if(top <= bottom) {
                for(int j = right; j >= left; j--)
                    res.add(matrix[bottom][j]);
                bottom--;
            }

            // From bottom to top
            if(left <= right) {
                for(int i = bottom; i >= top; i--)
                    res.add(matrix[i][left]);
                left++;
            }
        }
        
        return res;
    }
}
