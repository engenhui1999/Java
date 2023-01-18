import java.io.*;
import java.util.*;

public class lostmap {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args) throws IOException{
        int numVillages = Integer.parseInt(br.readLine());
        int[][] adjMatrix = new int[numVillages][numVillages];
        ArrayList<ArrayList<Integer>> edgeLists = new ArrayList<ArrayList<Integer>>(numVillages);
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));

        for (int i = 0; i < numVillages; i++) {
            String[] input = br.readLine().split(" ");
            edgeLists.add(new ArrayList<Integer>());
            for (int j = 0; j < numVillages; j++) {
                adjMatrix[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 0; i < numVillages; i++) {
            for (int j = i; j < numVillages; j++) {
                int[] temp = {i, j, adjMatrix[i][j]};
                pq.add(temp);
            }
        }

        UnionFind T = new UnionFind(numVillages);

        while(!pq.isEmpty()) {
            int[] currentEdge = pq.remove();
            if (!T.isSameSet(currentEdge[0], currentEdge[1])) {
                T.unionSet(currentEdge[0], currentEdge[1]);
                edgeLists.get(currentEdge[0]).add(currentEdge[1]);
            }
        }

        for (int i = 0; i < numVillages; i++) {
            for (int elem : edgeLists.get(i)) {
                pw.println((i + 1) + " " + (elem + 1));
            }
        }

        pw.close();
    }
    
    private static class UnionFind {
        public int[] p;
        public int[] rank;
        public int[] setSize;
      
        public UnionFind(int N) {
          p = new int[N];
          rank = new int[N];
          setSize = new int[N];
          for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
            setSize[i] = 1;
          }
        }
      
        public int findSet(int i) { 
          if (p[i] == i) return i;
          else {
            p[i] = findSet(p[i]);
            return p[i]; 
          } 
        }
      
        public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
      
        public void unionSet(int i, int j) { 
          if (!isSameSet(i, j)) {
            int x = findSet(i), y = findSet(j);
            if (rank[x] > rank[y]) { 
                p[y] = x; 
                setSize[x] = setSize[x] + setSize[y]; 
            }
            else { 
                p[x] = y; 
                setSize[y] = setSize[x] + setSize[y];
              if (rank[x] == rank[y]) 
                rank[y] = rank[y]+1; 
            } 
          } 
        }
    }
}
