/***************************
 * Purpose: Project 4 (Vertex class) is an immutable class that
 * defines the vertex of the graph and contains the x and y coordinates along
 * with its name.
 ***************************/

public class Vertex {
	private final double x;
	private final double y;
	private final String name;

	public Vertex(double x, double y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getName() {
		return name;
	}
}