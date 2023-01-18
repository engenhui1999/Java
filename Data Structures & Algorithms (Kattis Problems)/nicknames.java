import java.io.*;

public class nicknames {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args) throws IOException {
        int numNames = Integer.parseInt(br.readLine());
        Node root = new Node(br.readLine());
        for (int i = 1; i < numNames; i++) {
            String name = br.readLine();
            root = root.insert(new Node(name));
        }

        int numNicknames = Integer.parseInt(br.readLine());
        for (int i = 0; i < numNicknames; i++) {
            String nickname = br.readLine();
            pw.println(root.search(nickname, nickname.length()));
        }
        pw.close();
    }

    private static class Node {
        private Node left = null;
        private Node right = null;
        private Node parent = null;
        private String content;
        private int height = 0;
        private int size = 1;

        private Node(String content) {
            this.content = content;
        }

        private static int height(Node t) {
            return t == null ? -1 : t.height;
        }

        private static int size(Node t) {
            return t == null ? 0 : t.size;
        }

        private static Node rotateLeft(Node t) {
            Node w = t.right;
            w.parent = t.parent;
            t.parent = w;
            t.right = w.left;
            if (w.left != null) { w.left.parent = t; }
            w.left = t;
            t.size = size(t.left) + size(t.right) + 1;
            w.size = size(w.left) + size(w.right) + 1;
            t.height = Math.max(height(t.left), height(t.right)) + 1;
            w.height = Math.max(height(w.left), height(w.right)) + 1;
            return w;
        }

        private static Node rotateRight(Node w) {
            Node t = w.left;
            t.parent = w.parent;
            w.parent = t;
            w.left = t.right;
            if (t.right != null) { t.right.parent = w; }
            t.right = w;
            w.size = size(w.left) + size(w.right) + 1;
            t.size = size(t.left) + size(t.right) + 1;
            w.height = Math.max(height(w.left), height(w.right)) + 1;
            t.height = Math.max(height(t.left), height(t.right)) + 1;
            return t;
        }

        private int balFactor() {
            if (this.left == null && this.right == null) {
                return 0;
            } else if (this.left == null) {
                return -1 - this.right.height;
            } else if (this.right == null) {
                return this.left.height + 1;
            } else {
                return this.left.height - this.right.height;
            }
        }

        private Node insert(Node node) {
            this.size++;
            if (node.content.compareTo(this.content) < 0) {
                if (this.left == null) {
                    this.left = node;
                    node.parent = this;
                } else {
                    this.left = this.left.insert(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                    node.parent = this;
                } else {
                    this.right = this.right.insert(node);
                }
            }

            int leftHeight = this.left == null ? -1 : this.left.height;
            int rightHeight = this.right == null ? -1 : this.right.height;
            this.height = Math.max(leftHeight, rightHeight) + 1;

            int bf = this.balFactor();
            if (bf >= -1 && bf <= 1) { return this; }
            if (bf == 2) {
                if (this.left.balFactor() == -1) {
                    this.left = rotateLeft(this.left);
                    return rotateRight(this);
                } else {
                    return rotateRight(this);
                }
            } else {
                if (this.right.balFactor() == 1) {
                    this.right = rotateRight(this.right);
                    return rotateLeft(this);
                } else {
                    return rotateLeft(this);
                }
            }
        }

        private int search(String nickname, int length) {
            String smallestString = nickname;
            String largestString = nickname + "z".repeat(10 - length);
            return rank(largestString, false) - rank(smallestString, true);
        }

        private int rank(String nickname, boolean smallest) {
            int rank = 0;
            if (this.content.compareTo(nickname) > 0) {
                return this.left == null ? 0 : rank + this.left.rank(nickname, smallest);
            } else if (this.content.compareTo(nickname) < 0 ) {
                rank += size(this.left) + 1;
                return this.right == null ? rank : rank + this.right.rank(nickname, smallest);
            } else {
                return smallest ? rank + size(this.left) : rank + size(this.left) + 1;
            }
        }
    }
}
