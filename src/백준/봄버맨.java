package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 봄버맨 {
    static int Y,X,N;
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};
    static char[][] map;
    static Queue<node> q=new LinkedList<>();
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
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());

        map=new char[Y][X];

        for (int i = 0; i < Y; i++) {
            String str=br.readLine();
            for (int j = 0; j < X; j++) {
                map[i][j]=str.charAt(j);
            }
        }

        if(N==1)print();
        else {
            // 초기 1초 버리기
            for (int i = 1; i < N; i++) {
                // 폭탄 폭발
                if (i % 2 == 0) {
                    boolean[][] v = new boolean[Y][X];
                    Boom(v);
                } else { // 폭탄 설치
                    Plant();
                }
            }

            // 출력
            print();
        }
    }

    private static void Plant() {
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if(map[i][j]=='O')q.offer(new node(i,j));
                map[i][j]='O';
            }
        }
    }

    private static void Boom(boolean[][] v) {
        while (!q.isEmpty()){
            node n=q.poll();
            v[n.y][n.x]=true;
            map[n.y][n.x]='.';

            for (int d = 0; d < 4; d++) {
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];

                if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx]){
                    v[ny][nx]=true;
                    map[ny][nx]='.';
                }
            }
        }
    }

    private static void print() {
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
