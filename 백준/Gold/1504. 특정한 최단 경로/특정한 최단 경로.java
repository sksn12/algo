
import java.io.*;
import java.util.*;

public class Main {
    static int N,E;
    static class Node implements Comparable<Node>{
        int number;
        int cost;

        Node(int number,int cost){
            this.number=number;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node n){
            return this.cost-n.cost;
        }
    }

    static PriorityQueue<Node> pq;
    static List<Node>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        map=new List[N+1];

        for (int i = 0; i <= N; i++) {
            map[i]=new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st=new StringTokenizer(br.readLine());

            int n1=Integer.parseInt(st.nextToken());
            int n2=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());

            map[n1].add(new Node(n2,c));
            map[n2].add(new Node(n1,c));
        }
        st=new StringTokenizer(br.readLine());
        int v1=Integer.parseInt(st.nextToken());
        int v2=Integer.parseInt(st.nextToken());


        int[] dist1 = dijkstra(1); // 시작 노드에서 들려야 하는 첫 정점의 거리
        int[] distV1 = dijkstra(v1); // 첫 정점과 두번째 정점의 거리
        int[] distV2 = dijkstra(v2); // 두번째 정점과 도착지점 N의 거리

        long path1 = (long)dist1[v1] + distV1[v2] + distV2[N]; // 1 → v1 → v2 → N
        long path2 = (long)dist1[v2] + distV2[v1] + distV1[N]; // 1 → v2 → v1 → N

        long answer = Math.min(path1, path2);
        System.out.println(answer >= Integer.MAX_VALUE ? -1 : answer);
    }

    public static int[] dijkstra(int start){
        pq=new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()){
            Node n=pq.poll();

            // 이미 확정된 최단 거리보다 크면 스킵
            if (n.cost > dist[n.number]) continue;

            for (int i = 0; i < map[n.number].size(); i++) {
                Node numberNode=map[n.number].get(i);
                int cost=numberNode.cost+n.cost;

                if(cost<dist[numberNode.number]){
                    dist[numberNode.number]=cost;
                    pq.offer(new Node(numberNode.number,cost));

                }
            }
        }

        return dist;
    }


}
