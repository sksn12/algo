package 백준;

import java.io.*;
import java.util.*;

public class 숨바꼭질 {
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

    static List<Node>[] map;
    static PriorityQueue<Node> pq=new PriorityQueue<>();
    static int[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new List[N+1];
        D=new int[N+1];

        for (int i = 0; i <=N ; i++) {
            map[i]=new ArrayList<>();
        }

        for (int m = 0; m <M; m++) {
            st=new StringTokenizer(br.readLine());

            int n1=Integer.parseInt(st.nextToken());
            int n2=Integer.parseInt(st.nextToken());

            map[n1].add(new Node(n2,0));
            map[n2].add(new Node(n1,0));
        }

        Arrays.fill(D,Integer.MAX_VALUE);
        D[1]=0;

        pq.offer(new Node(1,0));
        dijkstra();

        int max=Integer.MIN_VALUE;

        for (int i = 1; i <D.length ; i++) {
            max=Math.max(max,D[i]);
        }

        int cnt=0;
        for (int i = 1; i <D.length ; i++) {
            if(max==D[i])cnt+=1;
        }

        for (int i = 1; i <D.length ; i++) {
            if(max==D[i]){
                System.out.println(i+" "+max+" "+cnt);
                break;
            }
        }
    }

    public static void dijkstra(){
        while (!pq.isEmpty()){
            Node n=pq.poll();

            for (int i = 0; i < map[n.n].size(); i++) {
                Node next=map[n.n].get(i);

                if(n.cost+1<D[next.n]){
                    D[next.n]=n.cost+1;
                    pq.offer(new Node(next.n,n.cost+1));
                }
            }
        }
    }
}
