
import java.io.*;
import java.util.*;

public class Main {
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static boolean[][] area_see;
    static int N,area_cnt;
    static int answer=Integer.MAX_VALUE;
    static int[][] map;
    static Queue<int[]> q;
    static boolean[][] v;
    static class Node{
        int y,x,cnt,area_number;

        Node(int y,int x,int cnt,int area_number){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
            this.area_number=area_number;
        }
    }
    static Queue<Node> q2=new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        area_see=new boolean[N][N];
        map=new int[N][N];
        v=new boolean[N][N];

        // 0. 섬 위치 확인
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                if(Integer.parseInt(st.nextToken())==1){
                    area_see[i][j]=true;
                }
            }
        }

        // 1. 각 섬 영역을 나누기
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(area_see[i][j] && !v[i][j]){

                    // 1-1. 자신의 섬 범위 확인 전 초기 셋팅
                   area_cnt+=1;
                   q=new LinkedList<>();
                   q.offer(new int[] {i,j});
                   v[i][j]=true;
                   map[i][j]=area_cnt;

                   BFS();
                }
            }
        }

//        print();

        // 2. 각 섬에서 다른 섬까지의 최단 거리를 구함 (섬의 갯수만큼 반복)
        for(int cnt=1;cnt<=area_cnt;cnt++){
            v=new boolean[N][N];

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(map[i][j]==cnt && !v[i][j]){
                        v[i][j]=true;
                        q2=new LinkedList<>();
                        q2.offer(new Node(i,j,0,cnt));

                        bridge();
                    }
                }
            }
        }

        System.out.println(answer);

    }

    public static void bridge(){
        while(!q2.isEmpty()){
            Node n=q2.poll();

            for(int d=0;d<4;d++){
                int ny=dy[d]+n.y;
                int nx=dx[d]+n.x;

                if(0<=ny && 0<=nx && ny<N && nx<N && !v[ny][nx]){
                    v[ny][nx]=true;

                    // 2-1. 다음 영역이 현재 영역의 번호랑 같다면 같은 영역임으로 횟수 더하지 않고 전진
                    if(map[ny][nx]==n.area_number){
                        q2.offer(new Node(ny,nx,n.cnt,n.area_number));
                    }else if(map[ny][nx]==0){
                        q2.offer(new Node(ny,nx,n.cnt+1,n.area_number));
                    }else if(map[ny][nx]!=n.area_number){
                        answer=Math.min(answer,n.cnt);
                        return;
                    }
                }
            }
        }
    }

    public static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void BFS(){
        while(!q.isEmpty()){
            int[] yx=q.poll();

            for(int d=0;d<4;d++){
                int ny=dy[d]+yx[0];
                int nx=dx[d]+yx[1];

                if(0<=ny && 0<=nx && ny<N && nx<N && !v[ny][nx] && area_see[ny][nx]){
                    map[ny][nx]=area_cnt;
                    v[ny][nx]=true;
                    q.offer(new int[]{ny,nx});
                }
            }
        }
    }
}