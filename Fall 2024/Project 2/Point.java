/***************************
Purpose: Project2 (Point class) represents points as x and y coordinates and provides comparison methods for identifying maximal points.
***************************/

class Point implements Comparable<Point> {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isBelowAndLeft(Point other) {
        return this.x < other.x && this.y < other.y;
    }

    @Override
    public int compareTo(Point other) {
        return Double.compare(this.x, other.x);
    }
}