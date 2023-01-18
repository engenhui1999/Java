import java.io.*;

public class weakvertices {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void main(String[] args) throws IOException {
        while (true) {
            int vertices = Integer.parseInt(br.readLine());
            if (vertices == -1) { break; }

            StringBuilder sb = new StringBuilder();
            int[][] adjMatrix = new int[vertices][vertices];
            
            for (int i = 0; i < vertices; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < vertices; j++) {
                    adjMatrix[i][j] = Integer.parseInt(input[j]);
                }
            }

            for (int v = 0; v < vertices; v++) {
                int[] neighbours = new int[vertices];
                int size = 0;
                boolean weak = true;

                for (int e = 0; e < vertices; e++) {
                    if (adjMatrix[v][e] == 1) { neighbours[size++] = e; }
                }
                
                for (int i = 0; i < size; i++) {
                    for (int j = i + 1; j < size; j++) {
                        if (adjMatrix[neighbours[i]][neighbours[j]] == 1) { weak = false;}
                    }
                }

                if (weak) {
                    if (sb.length() == 0) {
                        sb.append(v);
                    } else {
                    sb.append(" ").append(v);
                    }
                }
            }
            pw.println(sb);
        }
        pw.close();
    }
}
