import java.io.*;
import java.util.*;

public class teque {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args) throws IOException {
        int numOps = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Integer> frontHalf = new HashMap<>();
        HashMap<Integer, Integer> backHalf = new HashMap<>();
        int frontHead = 0;
        int frontTail = 1;
        int backHead = 0;
        int backTail = 1;

        for (int i = 0; i < numOps; i++) {
            String[] input = br.readLine().split(" ");
            int data = Integer.parseInt(input[1]);
            if (input[0].equals("push_back")) {
                backHalf.put(backTail, data);
                backTail++;
            } else if (input[0].equals("push_front")) {
                frontHalf.put(frontHead, data);
                frontHead--;
            } else if (input[0].equals("push_middle")) {
                frontHalf.put(frontTail, data);
                frontTail++;
            } else if (input[0].equals("get")) {
                if (data < frontHalf.size()) {
                    sb.append(frontHalf.get(data + frontHead + 1)).append('\n');
                } else {
                    sb.append(backHalf.get(data - frontHalf.size() + 1 + backHead)).append('\n');
                }
            }
            if (frontHalf.size() - 1 > backHalf.size()) {
                backHalf.put(backHead, frontHalf.get(frontTail - 1));
                backHead--;
                frontHalf.remove(frontTail - 1);
                frontTail--;
            } else if (frontHalf.size() < backHalf.size()) {
                frontHalf.put(frontTail, backHalf.get(backHead + 1));
                frontTail++;
                backHalf.remove(backHead + 1);
                backHead++;
            }
        }
        pw.println(sb);
        pw.close();
    }
}
