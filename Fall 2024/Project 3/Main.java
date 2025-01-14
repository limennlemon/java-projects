
/***************************
Purpose: Project 3 (Main class) prompts user for input and displays results to the console.   
***************************/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Enter a binary tree:  ");
			String input = scanner.nextLine();
			input = input.trim();

			try {
				BinaryTree tree = new BinaryTree(input);

				System.out.println("\nOriginal tree:\n");
				tree.printIndented();

				boolean isBST = tree.isBST();
				boolean isBalanced = tree.isBalanced();

				if (!isBST) {
					System.out.println("\nIt is not a binary search tree");
				} else if (isBalanced) {
					System.out.println("\nIt is a balanced binary search tree");
				} else {
					System.out.println("\nIt is a binary search tree but it is not balanced");
				}

				if (!isBalanced || !isBST) {
					// Construct a balanced BST
					ArrayList<Integer> values = tree.getValues();
					Collections.sort(values); // Ensure the values are sorted
					BinaryTree balancedTree = new BinaryTree(values);

					System.out.println("\nBalanced binary search tree with same values:\n");
					balancedTree.printIndented();
					// Get the original tree height
					System.out.println("\nOriginal tree height:  " + tree.getHeight());
					// Get the balanced tree height
					System.out.println("Balanced tree height:  " + balancedTree.getHeight());
				}

			} catch (TreeSyntaxException e) {
				System.out.println("\nError:  " + e.getMessage());
			}

			System.out.print("\nMore trees? Y or N:  ");
			String response = scanner.nextLine().trim().toUpperCase();
			if (!response.equals("Y")) {
				break;
			}
			System.out.println();
		}

		scanner.close();
	}
}
