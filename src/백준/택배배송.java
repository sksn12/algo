package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 택배배송 {
    static class Node implements Comparable<Node>{
        int v;
        int w;

        Node(int v,int w){
            this.v=v;
            this.w=w;
        }

        @Override
        public int compareTo(Node v){
            return this.w-v.w;
        }
    }

    static int V,E;
    static List<Node>[] map;
    static Queue<Node> q;
    static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        map=new List[V+1];

        for (int i = 1; i <=V ; i++) {
            map[i]=new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st=new StringTokenizer(br.readLine());

            int nv=Integer.parseInt(st.nextToken());
            int pv=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());

            map[nv].add(new Node(pv,w));
            map[pv].add(new Node(nv,w));
        }

        q=new PriorityQueue<>();
        q.offer(new Node(1,0));

        d=new int[V+1];
        Arrays.fill(d,Integer.MAX_VALUE);

        dijkstra();

        System.out.println(d[V]);
    }

    private static void dijkstra() {
        while (!q.isEmpty()){
            Node n=q.poll();

            for (Node pn:map[n.v]) {
                if(d[pn.v]>pn.w+n.w){
                    d[pn.v]=pn.w+n.w;
                    q.offer(new Node(pn.v,d[pn.v]));
                }
            }
        }
    }
}
