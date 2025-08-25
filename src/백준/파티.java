package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 파티 {
    static int N,M,X;
    static class Node implements Comparable<Node>{
        int n;
        int cost;

        Node(int n,int cost){
            this.n=n;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node n){
            return this.cost-n.cost;
        }
    }

    static PriorityQueue<Node> pq=new PriorityQueue<>();
    static List<Node>[] map;
    static int[] back;
    static int[] go;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new List[N+1];

        for (int i = 1; i <=N ; i++) {
            map[i]=new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());

            map[n].add(new Node(m,w));
        }

        go=new int[N+1];
        back=new int[N+1];

        Arrays.fill(go,Integer.MAX_VALUE);
        Arrays.fill(back,Integer.MAX_VALUE);

        // 1. 먼저 파티장에서 각각 집으로 돌아가는 최소 비용을 구함
        pq.offer(new Node(X,0));
        back[X]=0;
        dijkstra(false);

        int answer=Integer.MIN_VALUE;

        // 2. 각각의 모든 집들을 시작점으로 지정하고, 나머지 집에 도착하는데 걸리는 비용을 go에 담아둠
        for (int i = 1; i <=N ; i++) {
            if(i==X)continue; // X는 도착점임으로 pass
            Arrays.fill(go,Integer.MAX_VALUE);
            pq=new PriorityQueue<>();
            go[i]=0;
            pq.offer(new Node(i,0));

            dijkstra(true);

            // 그럼 현재 집에서 X에 도달하는데 걸리는 비용 + X에서 현재 집에 도착하는데 걸리는 비용을 합쳐주면됨
            answer=Math.max(answer,back[i]+go[X]);
        }
        System.out.println(answer);
    }

    public static void dijkstra(boolean val){
        while (!pq.isEmpty()){
            Node n=pq.poll();

            // 이미 내 비용이 확인하려는(=이동하려는) 비용 보다 크면 q에 넣을필요없음 -> 시간 단축
            if(!val){
                if(n.cost>back[n.n])continue;
            }else{
                if(n.cost>go[n.n])continue;
            }

            for (int i = 0; i < map[n.n].size(); i++) {
                Node next=map[n.n].get(i);
                int next_cost=next.cost+n.cost;

                // back 일떄
                if(!val){
                    if (next_cost<back[next.n]){
                        back[next.n]=next_cost;
                        pq.offer(new Node(next.n,next_cost));
                    }
                }else{
                    if (next_cost<go[next.n]){
                        go[next.n]=next_cost;
                        pq.offer(new Node(next.n,next_cost));
                    }
                }
            }
        }
    }

}
