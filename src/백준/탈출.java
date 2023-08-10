package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈출 {
    static int Y,X;
    static boolean val=false;
    static char[][] map;
    static boolean[][] v;
    static int[][] second;
    static Queue<node> qw=new LinkedList<>();
    static Queue<node> qs=new LinkedList<>();
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};

    static class node{
        int y;
        int x;
        int cnt;

        node(int y,int x, int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new char[Y][X];
        v=new boolean[Y][X];
        second=new int[Y][X];



        for (int i = 0; i < Y; i++) {
            String str=br.readLine();
            for (int j = 0; j < X; j++) {
                map[i][j]=str.charAt(j);
                second[i][j]=-1; // 물이 퍼지는 시간 배열을 -1로 초기화
                if(map[i][j]=='S')qs.offer(new node(i,j,0));
                else if(map[i][j]=='*')qw.offer(new node(i,j,0));
            }
        }

        // 물이 퍼지는게 가능한 지역을 먼저 BFS로 탐색하고 그 지역에 cnt를 넣어둠
        // 그래서 고슴도치가 이동하는 BFS를 돌릴때 고슴도치의 cnt가 물의 cnt보다 작아야만 전진가능 (같아도 안됨)

        // 물 먼저 BFS
        BFS_water();

        // 고슴도치 이동 BFS
        BFS_s();

        // 동굴 도착 x
        if(!val) System.out.println("KAKTUS");
    }

    private static void BFS_water() {
        while (!qw.isEmpty()){
            node n=qw.poll();
            second[n.y][n.x]=n.cnt;

            for (int d = 0; d < 4; d++) {
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];

                if(0<=ny && 0<=nx && ny<Y && nx<X && second[ny][nx]==-1 && (map[ny][nx]=='.'|| map[ny][nx]=='S')){
                    second[ny][nx]=n.cnt+1;
                    qw.offer(new node(ny,nx,n.cnt+1));
                }
            }
        }
    }

    private static void BFS_s() {
        while (!qs.isEmpty()){
            node n=qs.poll();
            if(map[n.y][n.x]=='D'){
                System.out.println(n.cnt);
                val=true;
                break;
            }

            v[n.y][n.x]=true;

            for (int d = 0; d < 4; d++) {
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];

                if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx] && (map[ny][nx]=='.'||map[ny][nx]=='D') && (n.cnt+1<second[ny][nx]||second[ny][nx]==-1)){
                    v[ny][nx]=true;
                    qs.offer(new node(ny,nx,n.cnt+1));
                }
            }
        }
    }
}
