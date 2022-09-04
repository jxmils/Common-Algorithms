import java.util.*; 

/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
 */
public class NumberOfIslands {

    /* Function to print a grid */
    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");   
                if (j == grid.length - 1) System.out.println();
            }
        }
    }

    public static int numIslands(int[][] matrix) {
        int size = 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (visited[row][col]) continue;
                if (numIslandsDFS(matrix, visited, row, col)) size++;
            }
        }
        return size;
    }
    public static boolean numIslandsDFS(int[][] matrix, boolean[][] visited, int row, int col) {
        if (row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length && !visited[row][col] && matrix[row][col] == 1) {
            visited[row][col] = true;
            numIslandsDFS(matrix, visited, row - 1, col);
            numIslandsDFS(matrix, visited, row + 1, col);
            numIslandsDFS(matrix, visited, row, col - 1);
            numIslandsDFS(matrix, visited, row, col + 1);
            return true;
        }
        return false;
    }

    public static void main (String[] args) {
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
        System.out.println("Here is the number of islands: " + numIslands(grid));
    }
}