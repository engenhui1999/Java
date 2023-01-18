import java.io.*;
import java.util.*;

public class kattissquest {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args) throws IOException {
        int numCommands = Integer.parseInt(br.readLine());
        TreeMap<Integer, PriorityQueue<Integer>> treeMap = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < numCommands; i++) {
            String[] input = br.readLine().split(" ");
            String command = input[0];
            if (command.equals("add")) {
                int energyConsumption = Integer.parseInt(input[1]);
                int goldReward = Integer.parseInt(input[2]);
                if (treeMap.containsKey(energyConsumption)) {
                    treeMap.get(energyConsumption).add(goldReward);
                } else {
                    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
                    priorityQueue.add(goldReward);
                    treeMap.put(energyConsumption, priorityQueue);
                }
            }
            if (command.equals("query")) {
                long totalGold = 0;
                int energyConsumption = Integer.parseInt(input[1]);
                while (energyConsumption > 0 && treeMap.ceilingKey(energyConsumption) != null) {
                    int key = treeMap.ceilingKey(energyConsumption);
                    PriorityQueue<Integer> priorityQueue = treeMap.get(key);
                    totalGold += priorityQueue.poll();
                    if (priorityQueue.isEmpty()) {
                        treeMap.remove(key);
                    }
                    energyConsumption -= key;
                }
                pw.println(totalGold);
            }
        }
        pw.close();
    }
}
