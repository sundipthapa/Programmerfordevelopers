package Questioncollections.Question4;
/*

b)	Given a linked list containing an integer value, return the number of steps required to sort an array in ascending order by eliminating elements at each step
Note: at each step remove element a[i] where a[i-1]> a[i]

 */
class Solutionb {
    public int sortArray(int[] nums) {
        // Get the length of the array
        int n = nums.length;
        // Initialize the number of steps
        int steps = 0;
        // Start the iteration from the second element
        int i = 1;
        // Keep looping until the end of the array
        while (i < n) {
            // Check if the current element is less than the previous element
            if (nums[i - 1] > nums[i]) {
                // Increase the number of steps
                steps++;
                // Find the end of the sequence of elements to be removed
                int j = i;
                while (j < n - 1 && nums[j] > nums[j + 1]) {
                    j++;
                }
                // Get the length of the sequence of elements to be removed
                int length = j - i + 1;
                // Shift the elements after the sequence to the left
                for (int k = i; k < n - length; k++) {
                    nums[k] = nums[k + length];
                }
                // Update the length of the array
                n -= length;
                // Update the index to start the next iteration
                i = Math.max(1, i - length + 1);
            } else {
                // Move to the next element if the current element is not less than the previous element
                i++;
            }
        }
        // Return the number of steps
        return steps;
    }
    public static void main(String[] args) {
        Solutionb solution = new Solutionb();
        int[] nums = {4, 5, 2, 25, 7, 32, 11, 15};
        int steps = solution.sortArray(nums);
        System.out.println("Steps required to sort the array: " + steps);
    }

}





