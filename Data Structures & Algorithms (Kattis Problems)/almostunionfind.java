import java.io.*;

public class almostunionfind {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    private static int[] root;
    private static Container[] container;

    public static void main(String[] args) throws IOException {
        while(true) {
            String temp = br.readLine();
            if (temp == null || temp == "") {
                break;
            }
            String[] input = temp.split(" ");
            int numIntegers = Integer.parseInt(input[0]);
            int numCommands = Integer.parseInt(input[1]);

            root = new int[numIntegers + 1];
            container = new Container[numIntegers + 1];

            for (int i = 1; i < numIntegers + 1; i++) {
                root[i] = i;
                container[i] = new Container(i);
            }
            for (int i = 0; i < numCommands; i++) {
                input = br.readLine().split(" ");
                switch (Integer.parseInt(input[0])) {
                    case 1:
                        union(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                        break;
                    case 2:
                        move(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                        break;
                    case 3:
                        printElementsAndSum(Integer.parseInt(input[1]));
                        break;
                }
            }
        }
        pw.close();
    }
    
    private static void union(int p, int q) {
        int rootP = root[p];
        int rootQ = root[q];
        Container tempP = Container.parent(container[rootP]);
        Container tempQ = Container.parent(container[rootQ]);
        if (tempP == tempQ) { return; }
        tempQ.sum += tempP.sum;
        tempQ.elements += tempP.elements;
        tempP.root = tempQ;
        /* redundant as Container.parent will skip this
        tempP.sum = 0;
        tempP.elements = 0;
        */
    }

    private static void move (int p, int q) {
        int rootP = root[p];
        int rootQ = root[q];
        Container tempP = Container.parent(container[rootP]);
        Container tempQ = Container.parent(container[rootQ]);
        if (tempP == tempQ) { return; }
        tempP.sum -= p;
        tempP.elements--;
        tempQ.sum += p;
        tempQ.elements++;
        root[p] = rootQ;
    }

    private static void printElementsAndSum (int p) {
        int rootP = root[p];
        Container tempP = Container.parent(container[rootP]);
        pw.println(tempP.elements + " " + tempP.sum);
    }

    private static class Container {
        private long sum = 0;
        private int elements = 0;
        private Container root = this;

        private Container(int value) {
            sum = value;
            elements = 1;
        }

        private static Container parent(Container c) {
            return c.root == c ? c : parent(c.root);
        }
    }
}