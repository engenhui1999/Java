import java.io.*;

public class joinstrings {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args) throws IOException {
        int numStrings = Integer.parseInt(br.readLine());
        int numOps = numStrings - 1;
        int finalInd = 1;
        NewList[] strings = new NewList[numStrings + 1];
        for (int i = 1; i <= numStrings; i++) {
            strings[i] = NewList.create(br.readLine());
        }
        for (int i = 0; i < numOps; i++) {
            String[] input = br.readLine().split(" ");
            finalInd = Integer.parseInt(input[0]);
            NewList.append(strings[finalInd],strings[Integer.parseInt(input[1])]);
        }
        pw.println(strings[finalInd]);
        pw.close();
    }

    private static class NewList {
        private Node head;
        private Node tail;
        private static class Node {
            private String data;
            private Node next;

            private Node(String data){
                this.data = data;
                this.next = null;
            }
        }

        private static NewList create(String data) {
            Node newNode = new Node(data);
            NewList list = new NewList();
            list.head = newNode;
            list.tail = newNode;
            return list;
        }

        private static NewList append(NewList list1, NewList list2) {
            list1.tail.next = list2.head;
            list1.tail = list2.tail;
            list2.head = null;
            return list1;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node curr = this.head;
            if (curr == null) {
                return "";
            }
            while(curr.next != null) {
                sb.append(curr.data);
                curr = curr.next;
            }
            sb.append(curr.data);
            return sb.toString();
        }
    }
}