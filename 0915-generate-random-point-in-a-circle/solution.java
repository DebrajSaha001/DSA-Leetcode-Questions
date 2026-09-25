class Solution {
    double radius;
    double xCenter;
    double yCenter;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.xCenter = x_center;
        this.yCenter = y_center;
    }

    public double[] randPoint() {
        double angle = 2 * Math.PI * Math.random();
        double r = radius * Math.sqrt(Math.random());

        double x = xCenter + r * Math.cos(angle);
        double y = yCenter + r * Math.sin(angle);

        return new double[]{x, y};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */
