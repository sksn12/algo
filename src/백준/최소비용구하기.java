package 백준;


import java.io.*;
import java.util.*;

public class 최소비용구하기 {
    static int N,M;
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
    static int[] d;
    static int s,e;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M=Integer.parseInt(st.nextToken());

        map=new List[N+1];
        for(int i=1;i<=N;i++){
            map[i]=new ArrayList<>();
        }

        d=new int[N+1];
        Arrays.fill(d,Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());

            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());

            map[n].add(new Node(m,w));
        }

        st=new StringTokenizer(br.readLine());
        s=Integer.parseInt(st.nextToken());
        e=Integer.parseInt(st.nextToken());

        d[s]=0;
        pq.offer(new Node(s,0));

        dijstra();

        System.out.println(d[e]);
    }

    public static void dijstra(){
        while (!pq.isEmpty()){
            Node n=pq.poll();

            if(n.cost>d[n.n])continue;

            for (int i = 0; i < map[n.n].size(); i++) {
                Node node=map[n.n].get(i);
                int total_cost=node.cost+n.cost;

                if(total_cost<d[node.n]){
                    d[node.n]=total_cost;
                    pq.offer(new Node(node.n,total_cost));
                }
            }
        }
    }










}
