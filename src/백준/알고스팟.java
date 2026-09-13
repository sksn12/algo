package 백준;

import java.io.*;
import java.util.*;

public class 알고스팟 {
    static int Y,X;
    static int[][] map;
    static int[][] answer;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static class Node implements Comparable<Node>{
        int y;
        int x;
        int cost;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X=Integer.parseInt(st.nextToken());
        Y=Integer.parseInt(st.nextToken());

        map=new int[Y][X];
        answer=new int[Y][X];

        for (int i = 0; i < Y; i++) {
            st=new StringTokenizer(br.readLine());
            String[] str=st.nextToken().split("");
            for (int j = 0; j < X; j++) {
                map[i][j]=Integer.parseInt(str[j]);
                answer[i][j]=Integer.MAX_VALUE;
            }
        }

        answer[0][0]=0;
        pq.offer(new Node(0,0,0));
        dijkstra();

    }

    public static void dijkstra(){
        while (!pq.isEmpty()){
            Node n=pq.poll();

            if(n.y==Y-1 && n.x==X-1){
                System.out.println(answer[Y-1][X-1]);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int ny=dy[d]+n.y;
                int nx=dx[d]+n.x;

                if(0<=ny && 0<=nx && ny<Y && nx<X){
                    int next_cost=map[ny][nx]==0?0:1; // 다음 진행하는 값이 벽이 있으면 cost=1,아니면 0
                    int total_cost=next_cost+n.cost;

                    if(total_cost<answer[ny][nx]){
                        pq.offer(new Node(ny,nx,total_cost));
                        answer[ny][nx]=total_cost;
                    }
                }
            }

        }
    }
}
