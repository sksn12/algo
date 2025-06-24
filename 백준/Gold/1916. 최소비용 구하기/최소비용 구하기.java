import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int node;
        int cost;

        Node(int node,int cost){
            this.node=node;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node node){
            return this.cost-node.cost;
        }
    }

    static int N,M,S,E;
    static List<Node>[] graph;
    static int[] cost;
    static PriorityQueue<Node> pq=new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(br.readLine());

        graph=new List[N+1];

        for (int i = 0; i <=N ; i++) {
            graph[i]=new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());

            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());

            graph[n].add(new Node(m,c));
        }

        st=new StringTokenizer(br.readLine());

        S=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        cost=new int[N+1];
        Arrays.fill(cost,Integer.MAX_VALUE);
        cost[S]=0;
        pq.offer(new Node(S,0));

        dijkstra();

        System.out.println(cost[E]);
    }

    public static void dijkstra(){
        while (!pq.isEmpty()){
            Node n=pq.poll();

            if (n.cost > cost[n.node]) continue;
            
            for(int i=0;i<graph[n.node].size();i++){
                // 이전 노드의 비용 + 현재 비용 < 기존 비용보다 작다면 값을 넣어줌
                if(cost[n.node] + graph[n.node].get(i).cost < cost[graph[n.node].get(i).node]){
                    cost[graph[n.node].get(i).node]=cost[n.node] + graph[n.node].get(i).cost;
                    pq.offer(new Node(graph[n.node].get(i).node,cost[graph[n.node].get(i).node]));
                }
            }
        }
    }
}
