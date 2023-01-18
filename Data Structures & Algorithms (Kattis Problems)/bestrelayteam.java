import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class bestrelayteam {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));


    private static class Runner implements Comparable<Runner>{
        String name;
        double firstLeg;
        double otherLeg;

        private Runner(String name, double firstLeg, double otherLeg) {
            this.name = name;
            this.firstLeg = firstLeg;
            this.otherLeg = otherLeg;
        }

        public String getName() {
            return this.name;
        }

        public double getFirstLeg() {
            return this.firstLeg;
        }

        public double getOtherLeg() {
            return this.otherLeg;
        }

        @Override
        public int compareTo(Runner other) {
            //runners are compared solely based on otherLeg timings.
            if (this.otherLeg < other.getOtherLeg()) { return -1; }
            else if (this.otherLeg > other.getOtherLeg()) {return 1;}
            return 0;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private static Runner[] initialiseRunners(int size) throws IOException {
        Runner[] arr = new Runner[size];
        for (int i = 0; i < size; i++) {
            String[] input = br.readLine().split(" ");
            arr[i] = new Runner(input[0], Double.parseDouble(input[1]), Double.parseDouble(input[2]));
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        int numRunners = Integer.parseInt(br.readLine());
        Runner[] runnersArr = initialiseRunners(numRunners);
        Arrays.sort(runnersArr);
        double fastestTime = Double.POSITIVE_INFINITY;
        StringBuilder finalLineup = new StringBuilder();
        StringBuilder runnerLineup = new StringBuilder();

        for (Runner i: runnersArr) {
            int size = 1;
            double time = 0;
            runnerLineup.setLength(0);
            runnerLineup.append(i).append("\n");
            time += i.getFirstLeg();
            for (Runner k: runnersArr) {
                if (size == 4) { break; }
                if (k == i) { continue; }
                runnerLineup.append(k).append("\n");
                time += k.getOtherLeg();
                size++;
            }

            if (time < fastestTime) {
                fastestTime = time;
                finalLineup = new StringBuilder(String.valueOf(fastestTime)).append("\n").append(runnerLineup.toString());
            }
        }
        pw.println(finalLineup);
        pw.close();
    }  
}
