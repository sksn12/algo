import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int vertex, cost;
        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, E;
    static List<Node>[] graph;
    static final int INF = 200000000; // 충분히 큰 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c)); // 양방향
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1, v1, v2에서 각각 다익스트라 실행
        int[] dist1 = dijkstra(1);
        int[] distV1 = dijkstra(v1);
        int[] distV2 = dijkstra(v2);

        // 두 가지 경로 계산
        long path1 = (long)dist1[v1] + distV1[v2] + distV2[N]; // 1 → v1 → v2 → N
        long path2 = (long)dist1[v2] + distV2[v1] + distV1[N]; // 1 → v2 → v1 → N

        long answer = Math.min(path1, path2);
        System.out.println(answer >= INF ? -1 : answer);
    }

    // 다익스트라 함수
    static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > dist[cur.vertex]) continue;

            for (Node next : graph[cur.vertex]) {
                int newCost = cur.cost + next.cost;
                if (newCost < dist[next.vertex]) {
                    dist[next.vertex] = newCost;
                    pq.offer(new Node(next.vertex, newCost));
                }
            }
        }

        return dist;
    }
}
