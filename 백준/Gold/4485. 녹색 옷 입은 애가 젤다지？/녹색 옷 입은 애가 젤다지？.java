
import java.io.*;
import java.util.*;

public class Main {
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

    static int N;
    static int[][] map;
    static Queue<Node> pq;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T=1;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N=Integer.parseInt(st.nextToken());
            if(N==0)break;

            map=new int[N][N];

            // 각각 공간마다 본인 위치로 오기 위해 최소비용을 계산해줌
            cost=new int[N][N];

            for(int i=0;i<N;i++){
                st=new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j]=Integer.parseInt(st.nextToken());
                    cost[i][j]=Integer.MAX_VALUE;
                }
            }

            pq=new PriorityQueue<>();
            cost[0][0]=map[0][0];
            pq.offer(new Node(0,0,map[0][0]));
            dij();

            System.out.println("Problem "+T+":"+" "+cost[N-1][N-1]);
            T+=1;
        }

    }

    public static void dij(){
        while(!pq.isEmpty()){
            Node n=pq.poll();

            for(int d=0;d<4;d++){
                int ny=dy[d]+n.y;
                int nx=dx[d]+n.x;

                if(0<=ny && 0<=nx && ny<N && nx<N){
                    int nowCost=n.cost+map[ny][nx];

                    if(nowCost<cost[ny][nx]){
                        cost[ny][nx]=nowCost;
                        pq.offer(new Node(ny,nx,nowCost));
                    }
                }
            }
        }
    }

}
