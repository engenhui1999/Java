import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class t9spelling {
    public static void main(String[] args) throws IOException {
        String[] alphabetArr = initialiseAlphabets();
        StringBuilder str = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCases = Integer.parseInt(br.readLine());

        for(int i = 1; i <= numCases; i++) {
            String sentence = br.readLine();
            char[] lettersArr = sentence.toCharArray();
            String prevVal = "z"; //initialise it

            for (char letter : lettersArr) {
                if (letter == ' ') {
                    if(prevVal == "0") {str.append(" ");}
                    str.append("0");
                    prevVal = "0"; //"0" as the whitespaces are from pressing "0"
                    continue;
                }

                String currVal = alphabetArr[letter];
                if (currVal.charAt(0) == prevVal.charAt(0)) {
                    str.append(" ");
                }
                
                str.append(currVal);
                prevVal = currVal;
            }

            System.out.println("Case #" + i + ": " + str.toString());
            str.setLength(0);
        }
    }

    private static String[] initialiseAlphabets() {
        String[] arr = new String[256];
        arr['a'] = "2";
        arr['b'] = "22";
        arr['c'] = "222";
        arr['d'] = "3";
        arr['e'] = "33";
        arr['f'] = "333";
        arr['g'] = "4";
        arr['h'] = "44";
        arr['i'] = "444";
        arr['j'] = "5";
        arr['k'] = "55";
        arr['l'] = "555";
        arr['m'] = "6";
        arr['n'] = "66";
        arr['o'] = "666";
        arr['p'] = "7";
        arr['q'] = "77";
        arr['r'] = "777";
        arr['s'] = "7777";
        arr['t'] = "8";
        arr['u'] = "88";
        arr['v'] = "888";
        arr['w'] = "9";
        arr['x'] = "99";
        arr['y'] = "999";
        arr['z'] = "9999";
        return arr;
    }
}
