package Questioncollections.Question2;

/*
You are given a 2D array containing hierarchical information about certain species, with edge[i]=[xi,yi], where node xi is connected to xj. You are also provided an array of values associated with each species, such that value[i] reflects the ith nodes value. If the greatest common divisor of two values is 1, they are "relatively prime." Any other node on the shortest path from that node to the absolute parent node is an ancestor of certain species i. Return a list of nearest ancestors, where result[i] is the node i's nearest ancestor such that values[i] and value[result[i]] are both relative primes otherwise -1.

Input: values [3,2,6,6,4,7,12], edges= {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}}
Output: {-1,0, -1, -1,0,2, -1}

 */
import java.util.*;

public class Solutiona {

    // Function to find nearest ancestor
    public int[] nearestAncestor(int[] values, int[][] edges) {
        int n = values.length;
        int[] nearest = new int[n];
        Arrays.fill(nearest, -1);

        // Creating adjacency list from given edges
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // BFS to find nearest ancestor
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : adj.get(node)) {
                if (visited[next]) {
                    continue;
                }
                int gcd = gcd(values[node], values[next]);
                if (gcd == 1) {
                    nearest[next] = node;
                } else {
                    nearest[next] = nearest[node];
                }
                visited[next] = true;
                queue.offer(next);
            }
        }
        return nearest;
    }

    // Function to calculate greatest common divisor of two numbers
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Solutiona obj = new Solutiona();
        int[] values = new int[] {3,2,6,6,4,7,12};
        int[][] edges = new int[][] {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}};
        int[] result = obj.nearestAncestor(values, edges);
        System.out.println(Arrays.toString(result));
    }
}


/*

Explanation:

The function nearestAncestor takes two inputs - values which is an array of species values and edges which is a 2D array representing the hierarchical information of species.
The function returns an array nearest where nearest[i] is the nearest ancestor of node i such that values[i] and values[nearest[i]] are both relative primes.
The first step is to create an adjacency list from the given edges.
Then, we use BFS to traverse the tree and calculate the nearest ancestor for each node. For each node, we calculate the GCD of its value and the value of its neighbor. If the GCD is 1, it means the values are relatively prime
 */





