
/***************************
Purpose: Project 4 (Graph class) defines the graph and provides methods to add vertices/edges, check cycles/connectivity, and return the list of vertices from depth-first/breadth-first searches.
***************************/

import java.util.*;

public class Graph {
	private final Map<String, Vertex> vertices = new HashMap<>();
	private final Map<String, List<String>> adjacencyList = new HashMap<>();

	public void addVertex(Vertex vertex) {
		vertices.put(vertex.getName(), vertex);
		adjacencyList.put(vertex.getName(), new ArrayList<>());
	}

	public void addEdge(String vertex1, String vertex2) {
		if (!vertices.containsKey(vertex1) || !vertices.containsKey(vertex2)) {
			throw new IllegalArgumentException("One or both vertices do not exist.");
		}
		adjacencyList.get(vertex1).add(vertex2);
		adjacencyList.get(vertex2).add(vertex1);
	}

	public boolean isConnected() {
		if (vertices.isEmpty())
			return true;
		Set<String> visited = new HashSet<>();
		dfs(vertices.keySet().iterator().next(), visited);
		return visited.size() == vertices.size();
	}

	private void dfs(String vertex, Set<String> visited) {
		visited.add(vertex);
		for (String neighbor : adjacencyList.get(vertex)) {
			if (!visited.contains(neighbor)) {
				dfs(neighbor, visited);
			}
		}
	}

	public boolean hasCycles() {
		Set<String> visited = new HashSet<>();
		for (String vertex : vertices.keySet()) {
			if (!visited.contains(vertex)) {
				if (hasCycles(vertex, null, visited)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasCycles(String current, String parent, Set<String> visited) {
		visited.add(current);
		for (String neighbor : adjacencyList.get(current)) {
			if (!neighbor.equals(parent)) {
				if (visited.contains(neighbor) || hasCycles(neighbor, current, visited)) {
					return true;
				}
			}
		}
		return false;
	}

	public List<String> depthFirstSearch() {
		List<String> result = new ArrayList<>();
		if (vertices.isEmpty())
			return result;
		Set<String> visited = new HashSet<>();
		dfsList(vertices.keySet().iterator().next(), visited, result);
		return result;
	}

	private void dfsList(String vertex, Set<String> visited, List<String> result) {
		visited.add(vertex);
		result.add(vertex);
		for (String neighbor : adjacencyList.get(vertex)) {
			if (!visited.contains(neighbor)) {
				dfsList(neighbor, visited, result);
			}
		}
	}

	public List<String> breadthFirstSearch() {
		List<String> result = new ArrayList<>();
		if (vertices.isEmpty())
			return result;
		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		String startVertex = vertices.keySet().iterator().next();

		queue.add(startVertex);
		visited.add(startVertex);

		while (!queue.isEmpty()) {
			String vertex = queue.poll();
			result.add(vertex);
			for (String neighbor : adjacencyList.get(vertex)) {
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.add(neighbor);
				}
			}
		}
		return result;
	}

	public Map<String, Vertex> getVertices() {
		return vertices;
	}
}