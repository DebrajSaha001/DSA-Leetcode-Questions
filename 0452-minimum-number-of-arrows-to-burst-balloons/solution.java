class Solution {
    public int findMinArrowShots(int[][] points) {
        // Sorting the balloons by their ending coordinate
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        int arrows = 1;

        // Shoot the first arrow at the end of the first balloon
        int arrow = points[0][1];

        for(int i = 1; i < points.length; i++) {
            // Current balloon starts after our arrow
            if(points[i][0] > arrow) {
                // Need another arrow
                arrows++;

                // Shoot it at the end of the current balloon
                arrow = points[i][1];
            }
        }

        return arrows;
    }
}

