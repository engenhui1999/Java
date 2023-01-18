import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class sortofsorting {
        private static class sorting_algo implements Comparator<String>{
            @Override
            public int compare(String s1, String s2) {
                char s1_0 = s1.charAt(0);
                char s2_0 = s2.charAt(0);
                char s1_1 = s1.charAt(1);
                char s2_1 = s2.charAt(1);
                if (s1_0 < s2_0) { return -1; }
                else if (s1_0 > s2_0) { return 1;}
                else if (s1_1 < s2_1) {return -1;}
                else if (s1_1 > s2_1) {return 1;}
                return 0;
                //return s1.substring(0,2).compareTo(s2.substring(0,2));
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
            Comparator<String> sorting_algo = new sorting_algo();
            int numNames = 1;
    
            while (numNames != 0) {
                numNames = Integer.parseInt(br.readLine());
                if (numNames == 0) break;
    
                String [] names = new String[numNames];
                for (int i = 0; i < numNames; i++) {
                    names[i] = br.readLine();
                }
                
                Arrays.sort(names, sorting_algo);
    
                for (String name : names) {
                    pw.println(name);
                }
                pw.println("\n");
            }
            pw.close();
        }
}
