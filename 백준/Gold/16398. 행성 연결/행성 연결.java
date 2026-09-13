import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static long answer = 0;

    static class Node implements Comparable<Node> {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        prim();
        System.out.println(answer);
    }

    public static void prim() {
        visited[0] = true;

        for (int i = 0; i < N; i++) {
            if (i != 0) {
                pq.offer(new Node(i, map[0][i]));
            }
        }

        int connected = 1;

        while (!pq.isEmpty() && connected < N) {
            Node current = pq.poll();
            if (visited[current.to]) continue;

            visited[current.to] = true;
            answer += current.cost;
            connected++;

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    pq.offer(new Node(i, map[current.to][i]));
                }
            }
        }
    }
}
