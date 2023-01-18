import java.io.*;
import java.util.*;

public class workstations {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        int numResearchers = Integer.parseInt(input[0]);
        int lockAfter = Integer.parseInt(input[1]);
        int count = 0;

        PriorityQueue<Researcher> queueReseacher = new PriorityQueue<>();
        PriorityQueue<Long> queueWorkstation = new PriorityQueue<>();

        for (int i = 0; i < numResearchers; i++) {
            input = br.readLine().split(" ");
            queueReseacher.add(new Researcher(Long.parseLong(input[0]), Long.parseLong(input[1])));
        }

        while (!queueReseacher.isEmpty()) {
            Researcher researcher = queueReseacher.poll();
            if (queueWorkstation.isEmpty() || queueWorkstation.peek() > researcher.arrival) {
                queueWorkstation.add(researcher.arrival + researcher.stay);
            } else {
                while(!queueWorkstation.isEmpty()) {
                    long workstation = queueWorkstation.poll();
                    if ((workstation + lockAfter) >= researcher.arrival) {
                        count++;
                        queueWorkstation.add(researcher.arrival + researcher.stay);
                        break;
                    }
                }
            }
        }

        pw.println(count);
        pw.close();
    }

    private static class Researcher implements Comparable<Researcher> {
        private long arrival;
        private long stay;

        private Researcher(long arrival, long stay) {
            this.arrival = arrival;
            this.stay = stay;
        }

        @Override
        public int compareTo(Researcher r1){
            if (this.arrival == r1.arrival) {
                return 0;
            }
            return this.arrival < r1.arrival ? -1 : 1;
        }
    }
}
