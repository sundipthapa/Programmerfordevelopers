package Question8;

import java.util.Stack;

/*
a)
Given 2D matrix of 1 and 0s. Using stack, find maximum area of square made by 0s.
INPUT: 1 0 1 0 0
0 1 1 1 1
0 0 0 0 1
0 0 0 1 1
0 1 0 1 1
OUTPUT: 4

 */
public class MaximumAreaofSquare {
    // Function to find the maximum square made by 0s in the 2D matrix
    public static int findMaximumSquare(int[][] matrix) {
        // Get the number of rows and columns in the matrix
        int m = matrix.length;
        int n = matrix[0].length;

        // Initialize a variable to store the maximum area of the square
        int maxArea = 0;

        // Create an array to store the heights of the histogram
        int[] heights = new int[n];

        // For each row in the matrix, calculate the height of the histogram and update maxArea
        for (int i = 0; i < m; i++) {
            // Calculate the height of the histogram for the current row
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    heights[j] = 0;
                } else {
                    heights[j]++;
                }
            }
            // Update maxArea with the maximum of the current area and maxArea
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    // Function to find the largest rectangle area in the histogram
    private static int largestRectangleArea(int[] heights) {
        // Create a stack to store the indices of the histogram
        Stack<Integer> stack = new Stack<>();

        // Initialize a variable to store the maximum area of the rectangle
        int maxArea = 0;

        // Iterate through the heights of the histogram
        for (int i = 0; i <= heights.length; i++) {
            // Get the current height of the histogram
            int h = (i == heights.length ? 0 : heights[i]);

            // If the stack is empty or the current height is greater than or equal to the height at the top of the stack
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                // Push the current index onto the stack
                stack.push(i);
            } else {
                // Pop the element from the stack
                int tp = stack.pop();

                // Calculate the area of the rectangle by multiplying the current height with the difference between the current index and the index of the element at the top of the stack minus one
                maxArea = Math.max(maxArea, heights[tp] * (stack.isEmpty() ? i : i - 1 - stack.peek()));

                // Decrement i to continue the current iteration
                i--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        // Given matrix
        int[][] matrix = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};
        System.out.println("The find maximum area of square made by 0s" + findMaximumSquare(matrix)); // Output: 4
    }
}