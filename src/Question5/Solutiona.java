package Question5;

/*

Question 5
a)
You are given a 2D array containing coordinates and height of rectangle such that height[i]=[xi,yi,hi], where xi the x coordinate of left edge, yi represents x coordinate of right edge of rectangle and hi represents the height of the peaks of each rectangle. If you want to construct a border line over the peaks of rectangle represented in bar chart, return the key coordinates required to build a border line that contacts the peaks of the given chart.
Note: key points are the left coordinates of shape representing peaks where you need to draw boarder line.


Input: height={{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}}
Output: {{1,10},{2,15},{5,12},{8,0},{9,1},{11,15},{13,0}}

 */
import java.util.*;

public class Solutiona {
    public static int[][] getBorder(int[][] height) {
        int n = height.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int x1 = height[i][0], x2 = height[i][1], h = height[i][2];
            map.put(x1, Math.max(map.getOrDefault(x1, 0), h));
            map.put(x2, Math.max(map.getOrDefault(x2, 0), 0));
        }
        List<int[]> res = new ArrayList<>();
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int x = entry.getKey(), h = entry.getValue();
            if (max != h) {
                res.add(new int[]{x, h});
                max = h;
            }
        }
        int[][] result = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[][] = {{1, 4, 10}, {2, 5, 15}, {5, 8, 12}, {9, 11, 1}, {11, 13, 15}};
        System.out.println(Arrays.deepToString(getBorder(arr)));
    }
}

