
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static class Node implements Comparable<Node>{
        int node;
        int cost;

        Node(int node,int cost){
            this.node=node;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node n){
            return this.cost-n.cost;
        }
    }
    static List<Node>[] map;
    static int[] v;
    static PriorityQueue<Node> pq=new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new List[N + 1];
        v = new int[N + 1];
        Arrays.fill(v,-1);

        for (int i = 0; i <= N; i++) {
            map[i]=new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());

            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());

            map[n].add(new Node(m,c));
            map[m].add(new Node(n,c));
        }

        pq.offer(new Node(1,0));
        BFS();

        int max=0;
        int answer=0;
        for (int i = 1; i <=N; i++) {
            max=Math.max(max,v[i]);
            answer+=v[i];
        }

        System.out.println(answer-max);
    }

    public static void BFS(){
        while (!pq.isEmpty()){
            Node n=pq.poll();

            if(v[n.node]==-1){
                v[n.node] = n.cost;

                for (int i = 0; i < map[n.node].size(); i++) {
                    Node next_node=map[n.node].get(i);
                    pq.offer(new Node(next_node.node,next_node.cost));
                }
            }
        }
    }
}