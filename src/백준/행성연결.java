package 백준;

import java.io.*;
import java.util.*;

public class 행성연결 {
    static int N;
    static int[][] map;
    static boolean[] v;

    static class Node implements Comparable<Node>{
        int y,x,cost;

        Node(int y,int x,int cost){
            this.y=y;
            this.x=x;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node n){
            return this.cost-n.cost;
        }
    }

    static PriorityQueue<Node> pq=new PriorityQueue<>();
    static int answer=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map=new int[N][N];
        v=new boolean[N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        v[0]=true;

        for (int i = 0; i < N; i++) {
            if(map[0][i]!=0)pq.offer(new Node(0,i,map[0][i]));
        }

        BFS();

        System.out.println(answer);
    }

    public static void BFS(){
        while (!pq.isEmpty()){
            Node n=pq.poll();

            if(!v[n.x]){
                v[n.x]=true;
                answer+=n.cost;

                for (int i = 0; i < N; i++) {
                    if(map[n.x][i]!=0)pq.offer(new Node(n.x,i,map[n.x][i]));
                }
            }
        }
    }
}
