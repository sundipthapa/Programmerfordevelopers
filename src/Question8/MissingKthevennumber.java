package Question8;
/*

b)
Given an array of even numbers sorted in ascending order and an integer k,
Find the k^th missing even number from provided array
Input a[] ={0, 2, 6, 18, 22} k=6
Output: 16 examples:
Explanation: Missing even numbers on the list are 4, 8, 10, 12, 14, 16, 20 and so on and kth missing number is on 6th place of the list i.e. 16

 */

public class MissingKthevennumber {
    // Function to find the kth missing even number
    public static int findKthMissingEven(int[] a, int k) {
        // Initialize variables to store the lower and upper bounds
        int low = 0, high = a.length - 1;

        // Find the index of the first missing even number
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = a[mid] / 2 - a[0] / 2 + 1 - mid;
            if (count >= k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // Return the kth missing even number
        return a[0] + 2 * (k + low - 1);
    }

    public static void main(String[] args) {
        int[] a = {0, 2, 6, 18, 22};
        int k = 6;
        System.out.println("kth missing number is on 6th place of the list i.e. " + findKthMissingEven(a, k)); // Output: 16
    }
}
