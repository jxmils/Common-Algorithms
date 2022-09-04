import java.util.*;

/*
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
You may assume all four edges of the grid are surrounded by water.
The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.

Input: grid = [
[0,0,1,0,0,0,0,1,0,0,0,0,0],
[0,0,0,0,0,0,0,1,1,1,0,0,0],
[0,1,1,0,1,0,0,0,0,0,0,0,0],
[0,1,0,0,1,1,0,0,1,0,1,0,0],
[0,1,0,0,1,1,0,0,1,1,1,0,0],
[0,0,0,0,0,0,0,0,0,0,1,0,0],
[0,0,0,0,0,0,0,1,1,1,0,0,0],
[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
 */
public class MaxAreaOfIsland {
    /* Function to print a grid */
    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");   
                if (j == grid.length - 1) System.out.println();
            }
        }
    }

    public static int maxAreaIsland(int[][] grid) {
        int size = 0; 
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                size = Math.max(size, maxAreaIslandDFS(grid, visited, row, col));
            }
        }
        return size;
    }
    public static int maxAreaIslandDFS(int[][] grid, boolean[][] visited, int row, int col) {
        if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && !visited[row][col] && grid[row][col] == 1) {
            visited[row][col] = true;
            return 1 + maxAreaIslandDFS(grid, visited, row + 1, col) + maxAreaIslandDFS(grid, visited, row - 1, col) + maxAreaIslandDFS(grid, visited, row, col + 1) + maxAreaIslandDFS(grid, visited, row, col - 1);
        }
        return 0;
    }

    public static void main (String args[]) {
        /* Fill the grid with values between 0 and 1, then print to screen */
        Random rand = new Random();
        int upperBound = 2;
        int[][] grid = new int[5][5]; 
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = rand.nextInt(upperBound);
            }
        }
        printGrid(grid);

        /* Find the maximum area of the islands */
        System.out.println("Here is the maximum area of the islands: " + maxAreaIsland(grid));
    }
}
