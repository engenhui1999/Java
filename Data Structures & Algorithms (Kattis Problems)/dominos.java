import java.io.*;
import java.util.*;

public class dominos {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    private static int[] visited;
    private static ArrayList<ArrayList<Integer>> adjList;
    private static ArrayList<Integer> ts;
    public static void main(String[] args) throws IOException {
        int numTestcases = Integer.parseInt(br.readLine());
        for (int i = 0; i < numTestcases; i++) {
            String[] input = br.readLine().split(" ");
            int numDominos = Integer.parseInt(input[0]);
            int numLines = Integer.parseInt(input[1]);
            adjList = new ArrayList<>();
            visited = new int[numDominos];

            for (int j = 0; j < numDominos; j++) { adjList.add(new ArrayList<>()); }

            for (int j = 0; j < numLines; j++) {
                input = br.readLine().split(" ");
                int front = Integer.parseInt(input[0]);
                int back = Integer.parseInt(input[1]);
                adjList.get(front-1).add(back-1);
            }
            
            ts = new ArrayList<>();

            for (int j = 0; j < numDominos; j++) {
                if (visited[j] == 0) {
                   DFS(j);
                }
            }

            Collections.reverse(ts);
            visited = new int[numDominos];
            int count = 0;

            for (int elem : ts) {
                if (visited[elem] == 0) {
                    count++;
                    DFSrec(elem);
                }
            }
            pw.println(count);
        }
        pw.close();
    }

    private static void DFS(int i) {
        visited[i] = 1;
        for(int elem : adjList.get(i)) {
            if (visited[elem] == 0) {
                DFS(elem);
            }
        }
        ts.add(i);
    }

    private static void DFSrec(int i) {
        visited[i] = 1;
        for(int elem : adjList.get(i)) {
            if (visited[elem] == 0) {
                DFSrec(elem);
            }
        }
    }
}
