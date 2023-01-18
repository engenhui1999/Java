import java.io.*;
import java.util.*;

public class millionairemadness {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    private static int rows;
    private static int cols;
    private static int[][] adjMatrix;
    private static int[][] visited;
    private static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException{
        String[] input = br.readLine().split(" ");
        rows = Integer.parseInt(input[0]);
        cols = Integer.parseInt(input[1]);
        adjMatrix = new int[rows][cols];
        visited = new int[rows][cols];
        int highest = 0;

        for (int i = 0; i < rows; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                adjMatrix[i][j] = Integer.parseInt(input[j]);
            }
        }

        pq = new PriorityQueue<>((x, y) -> Integer.compare(x[4], y[4]));
        if (rows > 1) { 
            int[] temp = {0, 0, 1, 0, adjMatrix[1][0] - adjMatrix[0][0]};
            pq.add(temp); 
        }
        if (cols > 1) {
            int[] temp = {0, 0, 0, 1, adjMatrix[0][1] - adjMatrix[0][0]};
            pq.add(temp); 
        }

        while (!pq.isEmpty()) {
            int[] temp = pq.remove();
            if (temp[2] == (rows - 1) && temp[3] == (cols - 1)) { break; }
            addNeighbours(temp);
            visited[temp[2]][temp[3]] = 1;
            highest = Math.max(highest, temp[4]);
        }
        br.close();
        pw.println(highest);
        pw.close();
    }

    private static void addNeighbours(int[] curr) {
        int i = curr[2];
        int j = curr[3];

        if (i > 0 && visited[i-1][j] == 0) { // go up
            int[] temp = {i, j, i-1, j, adjMatrix[i-1][j] - adjMatrix[i][j]};
            pq.add(temp);            
        }
        if ((i+1) < rows && visited[i+1][j] == 0) { // go down
            int[] temp = {i, j, i+1, j, adjMatrix[i+1][j] - adjMatrix[i][j]};
            pq.add(temp); 
        }
        if (j > 0 && visited[i][j-1] == 0) { // go left
            int[] temp = {i, j, i, j-1, adjMatrix[i][j-1] - adjMatrix[i][j]};
            pq.add(temp); 
        }
        if ((j+1) < cols && visited[i][j+1] == 0) { // go right
            int[] temp = {i, j, i, j+1, adjMatrix[i][j+1] - adjMatrix[i][j]};
            pq.add(temp); 
        }
    }
}
