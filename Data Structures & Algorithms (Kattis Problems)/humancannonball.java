import java.io.*;
import java.util.*;

public class humancannonball {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    private static PriorityQueue<IntegerPair> pq;
    private static Vertex[] vertexs;
    public static void main(String[] args) throws IOException {
        pq = new PriorityQueue<>((a, b) -> Double.compare(a.weight, b.weight));
        String[] input = br.readLine().split(" ");
        Vertex start = new Vertex(Double.parseDouble(input[0]), Double.parseDouble(input[1]), false);
        start.weight = 0;
        input = br.readLine().split(" ");
        Vertex end = new Vertex(Double.parseDouble(input[0]), Double.parseDouble(input[1]), false);

        pq.add(new IntegerPair(0, start));
        pq.add(new IntegerPair(end.weight, end));

        int numCannons = Integer.parseInt(br.readLine());
        vertexs = new Vertex[numCannons + 2];
        vertexs[0] = start;
        vertexs[1] = end;

        for (int i = 2; i < numCannons + 2; i++) {
            input = br.readLine().split(" ");
            Vertex cannon = new Vertex(Double.parseDouble(input[0]), Double.parseDouble(input[1]), true);
            vertexs[i] = cannon;
            pq.add(new IntegerPair(cannon.weight, cannon));
        }

        while (!pq.isEmpty()) {
            IntegerPair curr = pq.poll();
            if (curr.weight != curr.vertex.weight) { continue; }
            for (Vertex neighbour : vertexs) {
                if (curr.vertex == neighbour) { continue; }
                relax(curr.vertex, neighbour);
            }
        }
        pw.println(end.weight);
        pw.close();
    }

    private static class IntegerPair {
        private double weight;
        private Vertex vertex;

        private IntegerPair(double weight, Vertex vertex) {
            this.weight = weight;
            this.vertex = vertex;
        }
    }

    private static class Vertex {
        private double x;
        private double y;
        private double weight = Double.POSITIVE_INFINITY;
        private boolean cannon;

        private Vertex(double x, double y, boolean cannon) {
            this.x = x;
            this.y = y;
            this.cannon = cannon;
        }
    }

    private static void relax(Vertex u, Vertex v) {
        double dist = Math.hypot(u.x - v.x, u.y - v.y);
        double time = 0;
        if (u.cannon) {
            dist = Math.abs(dist - 50);
            time += 2;
        }
        time += dist / 5;
        if (v.weight > (u.weight + time)) {
            v.weight = u.weight + time;
            pq.add(new IntegerPair(v.weight, v));
        }
    }
}
