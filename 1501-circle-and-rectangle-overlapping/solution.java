class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Finding the closest point in the rectangle to the circle's center
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        // Calculate the squared distance
        int dx = closestX - xCenter;
        int dy = closestY - yCenter;

        int distanceSquared = dx * dx + dy * dy;

        // Check if the closest point lies inside/on the circle
        return distanceSquared <= radius * radius;
    }
}
