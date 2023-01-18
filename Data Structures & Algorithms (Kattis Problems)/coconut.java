import java.io.*;
import java.util.*;

public class coconut {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static Deque<Player> queue = new ArrayDeque<Player>();
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        int numSyllables = Integer.parseInt(input[0]);
        int numPlayers = Integer.parseInt(input[1]);

        for (int i = 1; i <= numPlayers; i++) {
            queue.add(new Player(i, 0));
        }

        while (queue.size() != 1) {
            for (int i = 1; i < numSyllables; i++) { queue.add(queue.remove()); }
            if (queue.size() == 1) { break; }
            queueNextTouch();
        }
        pw.println(queue.peek().id);
        pw.close();
    }

    private static void queueNextTouch() {
        Player next = queue.remove();
        
        if (next.stageOfHands == 0) {
            queue.offerFirst(new Player(next.id, 3));
            queue.offerFirst(new Player(next.id, 1));
        } else if (next.stageOfHands == 1 || next.stageOfHands == 3) {
            queue.add(next);
            next.stageOfHands++;
        } else if (next.stageOfHands == 2 || next.stageOfHands == 4) {
            next.stageOfHands++;
        }
    }

    private static class Player {
        private int id;
        private int stageOfHands;

        private Player(int id, int stageOfHands) {
            this.id = id;
            /*
            stage of hands
            0 - fists together
            1 - left fist
            2 - left palms
            3 - right fist
            4 - right palms
            5 - hand out
            */
            this.stageOfHands = stageOfHands;
        }
    }
}
