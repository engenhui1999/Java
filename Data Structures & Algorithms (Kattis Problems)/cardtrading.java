import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class cardtrading {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    private static int[] cardArray;
    private static long[][] saleArray;
    private static costSort costSort = new costSort();
    private static int numCombos;

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        int highestInteger = Integer.parseInt(input[1]);
        numCombos = Integer.parseInt(input[2]);
        String[] deck = br.readLine().split(" ");

        cardArray = new int[highestInteger];
        saleArray = new long[highestInteger][5];

        for (String card : deck) {
            cardArray[Integer.parseInt(card) - 1]++;
        }

        for (int i = 0; i < highestInteger; i++) {
            String[] salesInput = br.readLine().split(" ");
            saleArray[i][0] = Long.parseLong(salesInput[0]) * 1;
            saleArray[i][1] = Long.parseLong(salesInput[1]) * 1;
            saleArray[i][2] = saleArray[i][0] * (2 - cardArray[i]) + saleArray[i][1] * cardArray[i]; //net cost of card.
            saleArray[i][3] = i; //to keep track of which net cost is for which card.
            saleArray[i][4] = cardArray[i];
        }

        Arrays.sort(saleArray, costSort);

        int currCombos = 0;
        long profit = 0;
        
        for (long[] card : saleArray) {
            if (currCombos < numCombos) {
                profit += buyCard(card);
                currCombos++;
            }
            else { profit += sellCard(card); }
        }
        pw.println(profit);
        pw.close();
    }
    
    private static class costSort implements Comparator<long[]> {
        @Override
        public int compare(long[] a1, long[] a2) {
            if (a1[2] < a2[2]) { return -1; }
            if (a1[2] > a2[2]) { return 1; }
            return 0;
        }
    }

    private static long buyCard(long[] card) {
        return card[0] * (2 - card[4]) * -1;
    }

    private static long sellCard(long[] card) {
        return card[4] * card[1];
    }
}
