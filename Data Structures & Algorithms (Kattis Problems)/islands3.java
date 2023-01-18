import java.io.*;

public class islands3 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    private static int[][] image;
    private static int[][] visited;
    private static int numRows;
    private static int numCols;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        numRows = Integer.parseInt(input[0]);
        numCols = Integer.parseInt(input[1]);
        image = new int[numRows][numCols];
        visited = new int[numRows][numCols];
        int islands = 0;

        for (int i = 0; i < numRows; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < numCols; j++) {
                image[i][j] = input[j].equals("W") ? 0
                            : input[j].equals("L") ? 1 : 2;
            }
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (visited[i][j] == 0 && image[i][j] == 1) {
                    DFS(i, j);
                    islands++;
                }
            }
        }

        pw.println(islands);
        pw.close();
    }

    private static void DFS(int row, int column) {
        visited[row][column] = 1;
        if (row > 0 && visited[row - 1][column] == 0 && image[row - 1][column] > 0) { DFS(row - 1, column);}
        if ((row + 1) < numRows && visited[row + 1][column] == 0 && image[row + 1][column] > 0) { DFS(row + 1, column); }
        if (column > 0 && visited[row][column - 1] == 0 && image[row][column - 1] > 0) { DFS(row, column - 1); }
        if ((column + 1) < numCols && visited[row][column + 1] == 0 && image[row][column + 1] > 0) { DFS(row, column + 1); }
    }
}
