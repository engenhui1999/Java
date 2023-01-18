import java.io.*;
import java.util.*;

public class conformity {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args) throws IOException {
        int num_Frosh = Integer.parseInt(br.readLine());
        HashMap<Long, Integer> map = new HashMap<>();
        int max_popularity = 1;
        for (int i = 0; i < num_Frosh; i++) {
            String[] input = br.readLine().split(" ");
            Arrays.sort(input);
            StringBuilder sb = new StringBuilder();
            for (String course : input) {
                sb.append(course);
            }
            long key = Long.parseLong(sb.toString());
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                int val = map.get(key);
                map.put(key, ++val);
                if (val > max_popularity) {
                    max_popularity = val;
                }
            }
        }
        Collection<Integer> values = map.values();
        int occurence = 0;
        for (int value : values) {
            if (value == max_popularity) {
                occurence++;
            }
        }

        pw.println(max_popularity * occurence);
        pw.close(); 
    }   
}
