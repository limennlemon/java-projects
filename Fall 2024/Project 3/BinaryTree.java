
/***************************
Purpose: Project 3 (BinaryTree class) is an immutable class that defines the binary tree and associated constructors (to accept the string, construct the binary tree, accept the array of integers, and construct a balanced binary search tree) and methods (to output the binary tree in indented form, return whether the tree is a binary search tree, return whether the tree is balanced, return the height of the tree, and return the array list of values of the tree).  
***************************/

import java.util.ArrayList;
import java.util.Collections;

public class BinaryTree {
	private final Node root;

	private static class Node {
		int value;
		Node left;
		Node right;

		Node(int value) {
			this.value = value;
		}
	}

	// Private StringParser inner class
	private static class StringParser {
		private final String input;
		private int position;

		public StringParser(String input) {
			this.input = input;
			this.position = 0;
		}

		public boolean hasMore() {
			return position < input.length();
		}

		public char peek() {
			return input.charAt(position);
		}

		public char consume() {
			return input.charAt(position++);
		}

		public void skipWhitespace() {
			while (hasMore() && Character.isWhitespace(peek())) {
				consume();
			}
		}
	}

	// Constructor for preorder string representation
	public BinaryTree(String preorder) throws TreeSyntaxException {
		StringParser parser = new StringParser(preorder);
		root = parsePreorder(parser);
		if (parser.hasMore()) {
			throw new TreeSyntaxException("Extra Characters at the End");
		}
	}

	// Constructor for balanced BST from ArrayList
	public BinaryTree(ArrayList<Integer> values) {
		Collections.sort(values);
		root = buildBalancedBST(values, 0, values.size() - 1);
	}

	private Node buildBalancedBST(ArrayList<Integer> values, int start, int end) {
		if (start > end)
			return null;

		int mid = (start + end) / 2;
		Node node = new Node(values.get(mid));

		node.left = buildBalancedBST(values, start, mid - 1);
		node.right = buildBalancedBST(values, mid + 1, end);

		return node;
	}

	private Node parsePreorder(StringParser parser) throws TreeSyntaxException {
		parser.skipWhitespace();

		if (!parser.hasMore()) {
			throw new TreeSyntaxException("Incomplete Tree");
		}

		char next = parser.peek();
		if (next == '*') {
			parser.consume();
			return null;
		}

		if (next != '(') {
			throw new TreeSyntaxException("Missing Left Parenthesis");
		}

		parser.consume(); // consume '('
		parser.skipWhitespace();

		// Parse value
		StringBuilder valueStr = new StringBuilder();
		while (parser.hasMore() && Character.isDigit(parser.peek())) {
			valueStr.append(parser.consume());
		}

		if (valueStr.length() == 0) {
			throw new TreeSyntaxException("Data is Not an Integer");
		}

		Node node = new Node(Integer.parseInt(valueStr.toString()));

		// Parse left child
		parser.skipWhitespace();
		node.left = parsePreorder(parser);

		// Parse right child
		parser.skipWhitespace();
		node.right = parsePreorder(parser);

		parser.skipWhitespace();
		if (!parser.hasMore() || parser.consume() != ')') {
			throw new TreeSyntaxException("Missing Right Parentheses");
		}

		return node;
	}

	public void printIndented() {
		printIndented(root, 0);
	}

	private void printIndented(Node node, int level) {
		if (node == null)
			return;

		for (int i = 0; i < level; i++) {
			System.out.print("    ");
		}
		System.out.println(node.value);

		printIndented(node.left, level + 1);
		printIndented(node.right, level + 1);
	}

	public boolean isBST() {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST(Node node, int min, int max) {
		if (node == null)
			return true;

		if (node.value <= min || node.value >= max)
			return false;

		return isBST(node.left, min, node.value) && isBST(node.right, node.value, max);
	}

	public boolean isBalanced() {
		return getBalancedHeight(root) != -1;
	}

	private int getBalancedHeight(Node node) {
		if (node == null)
			return 0;

		int leftHeight = getBalancedHeight(node.left);
		if (leftHeight == -1) {
			return -1;
		}

		int rightHeight = getBalancedHeight(node.right);
		if (rightHeight == -1) {
			return -1;
		}

		if (Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}

		int height = Math.max(leftHeight, rightHeight) + 1;
		return height;
	}

	public int getHeight() {
		return getHeight(root) - 1; // Subtract 1 to get edge count instead of node count
	}

	private int getHeight(Node node) {
		if (node == null) {
			return 0; // Base case: empty tree has height 0
		}
		return 1 + Math.max(getHeight(node.left), getHeight(node.right));
	}

	public ArrayList<Integer> getValues() {
		ArrayList<Integer> values = new ArrayList<>();
		collectValues(root, values);
		return values;
	}

	private void collectValues(Node node, ArrayList<Integer> values) {
		if (node == null)
			return;
		values.add(node.value);
		collectValues(node.left, values);
		collectValues(node.right, values);
	}
}