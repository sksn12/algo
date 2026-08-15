package 백준;

import java.io.*;
import java.util.*;

public class 네트워크연결 {
    static int N,E;
    static class Node implements Comparable<Node>{
        int next;
        int cost;
        Node(int next,int cost){
            this.next=next;
            this.cost=cost;
        }
        @Override
        public int compareTo(Node n){
            return this.cost-n.cost;
        }
    }

    static Queue<Node> pq=new PriorityQueue<>();
    static boolean[] v;
    static List<Node>[] map;
    static int answer=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        E=Integer.parseInt(st.nextToken());

        v=new boolean[N+1];
        map=new ArrayList[N+1];

        for(int i=0;i<map.length;i++){
            map[i]=new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine());

            int n1=Integer.parseInt(st.nextToken());
            int n2=Integer.parseInt(st.nextToken());
            int cost=Integer.parseInt(st.nextToken());

            map[n1].add(new Node(n2,cost));
            map[n2].add(new Node(n1,cost));
        }

        pq.offer(new Node(1,0));
        Prim();

        System.out.println(answer);
    }

    public static void Prim(){
        while(!pq.isEmpty()){
            Node n=pq.poll();

            if(!v[n.next]){
                v[n.next]=true;
                answer+=n.cost;

                for(int i=0;i<map[n.next].size();i++){
                    Node nextNode=map[n.next].get(i);
                    pq.offer(new Node(nextNode.next,nextNode.cost));
                }
            }
        }
    }
}
