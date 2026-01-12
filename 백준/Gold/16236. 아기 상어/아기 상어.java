
import java.io.*;
import java.util.*;

public class Main {
    static class Shark{
        int y,x,size,eat;

        Shark(int y,int x,int size,int eat){
            this.y=y;
            this.x=x;
            this.size=size;
            this.eat=eat;
        }

    }
    static class Node implements Comparable<Node>{
        int y,x,d;

        Node(int y,int x,int d){
            this.y=y;
            this.x=x;
            this.d=d;
        }

        @Override
        public int compareTo(Node n){
            if(n.d==this.d){
                if(n.y==this.y){
                    return this.x-n.x;
                }else{
                    return this.y-n.y;
                }
            }

            return this.d-n.d;
        }
    }

    static int N,answer=0;
    static int[][] map;
    static Queue<Node> pq;
    static Shark shark;
    static boolean[][] v;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        pq=new PriorityQueue<Node>();

        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());

                if(map[i][j]==9){
                    shark=new Shark(i,j,2,0);
                    map[i][j]=0;
                }
            }
        }

        boolean val=false;
        while(true){
            // 1. bfs로 map을 돌면서, 현재 아기상어가 먹을수있는 물고기 위치의 최단거리를 pq에 넣음
            BFS();
            // 2. pq.size()==0 -> 아기상어가 먹을 수 있는 물고기가 없다 -> 종료
            if(pq.size()==0){
                System.out.println(answer);
                val=true;
                break;
            }

            // 3. pq에서 하나 꺼내서 아기 상어가 먹음, -> 상어 정보 업데이트
            eat();

            // 4. pq reset
            pq=new PriorityQueue<>();
        }

        if(!val)System.out.println(answer);
    }

    public static void BFS(){
        v=new boolean[N][N];
        q=new LinkedList<>();

        q.offer(new int[] {shark.y,shark.x,0});
        v[shark.y][shark.x]=true;

        while(!q.isEmpty()){
            int[] sharkInfo=q.poll();

            for(int d=0;d<4;d++){
                int ny=sharkInfo[0]+dy[d];
                int nx=sharkInfo[1]+dx[d];

                if(0<=ny && 0<=nx && ny<N && nx<N && !v[ny][nx] && map[ny][nx]<=shark.size){
                    v[ny][nx]=true;

                    if(map[ny][nx]!=0 && map[ny][nx]<shark.size){
                        pq.offer(new Node(ny,nx,sharkInfo[2]+1));
                    }else{
                        q.offer(new int[] {ny,nx,sharkInfo[2]+1});
                    }
                }
            }
        }
    }


    // 물고기 위치에서 아기상어 위치의 최단거리를 찾아야함
    public static void eat(){
        Node n=pq.poll();

        map[n.y][n.x]=0;
        answer+=n.d;

        // 상어 위치 업데이트
        shark.y=n.y;
        shark.x=n.x;

        // 상어 크기 증가
        shark.eat+=1;
        if(shark.size==shark.eat){
            shark.size+=1;
            shark.eat=0;
        }

    }
}
