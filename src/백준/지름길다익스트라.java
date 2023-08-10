package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 지름길다익스트라 {
    static class node implements Comparable<node>{
        int s,e,w;
        node(int s,int e,int w){
            this.s=s;
            this.e=e;
            this.w=w;
        }

        @Override
        public int compareTo(node n){
            return this.w-n.w;
        }
    }
    static int N,D;
    static node[] map;
    static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        d=new int[D+1];
        for (int i = 0; i < D+1; i++) {
            d[i]=i;
        }

        map=new node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map[i]=new node(s,e,w);
        }

        // map은 정렬안됨!
        for (int i = 0; i <map.length; i++) {
            System.out.println(map[i].s+" "+map[i].e+" "+map[i].w+" ");
        }

        dijkstra();
        System.out.println(d[D]);
    }

    private static void dijkstra() {
        PriorityQueue<node> q=new PriorityQueue<>();

        q.offer(new node(0,0,0));
        d[0]=0;

        while (!q.isEmpty()){
            node n=q.poll();

            for (node m:map) {
                if(m.s>=n.e){
                    if(m.e>D)continue;
                    int tmp=m.s-n.e;
                    if(d[m.e]>d[n.e]+m.w+tmp){
                        d[m.e]=d[n.e]+m.w+tmp;
                        q.offer(new node(n.e,m.e,d[m.e]));
                    }
                }
            }

            d[D]=Math.min(d[n.e]+D-n.e,d[D]);

        }
    }
}
