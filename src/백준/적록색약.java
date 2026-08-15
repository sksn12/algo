package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 적록색약 {
    static int N,cnt;
    static char[][] map1,map2;
    static int[] dy={-1,0,1,0};
    static int[] dx={0,-1,0,1};
    static boolean[][] v;
    static Queue<node> q;

    static class node{
        int y;
        int x;

        node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());

        map1=new char[N][N];
        map2=new char[N][N];

        for (int i = 0; i < N; i++) {
            String str= br.readLine();
            for (int j = 0; j < N; j++) {
                map1[i][j]=str.charAt(j);

                // 보통사람과 적록색약을 갖은 사람의 배열을 나눠놓음
                if(map1[i][j]=='G')map2[i][j]='R';
                else map2[i][j]=map1[i][j];
            }
        }

        q=new LinkedList<>();
        v=new boolean[N][N];

        // 보통사람
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!v[i][j]){
                    q.offer(new node(i,j));
                    BFS(map1);
                    cnt+=1;
                }
            }
        }
        System.out.print(cnt+" ");

        // 초기화
        q=new LinkedList<>();
        v=new boolean[N][N];
        cnt=0;

        // 적록색약
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!v[i][j]){
                    q.offer(new node(i,j));
                    BFS(map2);
                    cnt+=1;
                }
            }
        }
        System.out.print(cnt);
    }

    private static void BFS(char[][] map) {
        while (!q.isEmpty()){
            node n=q.poll();
            v[n.y][n.x]=true;

            // 위 오 아 왼
            for (int d = 0; d < 4; d++) {
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];

                if(0<=ny && 0<=nx && ny<N && nx<N && !v[ny][nx] && map[ny][nx]==map[n.y][n.x]){
                    v[ny][nx]=true;
                    q.offer(new node(ny,nx));
                }
            }
        }
    }
}
