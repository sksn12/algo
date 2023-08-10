package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기 {
    static int Y,X,cnt;
    static int[][] map;
    static int[] dy={1,0,1,0}; // 아 오 위 왼
    static int[] dx={0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());
        cnt=Integer.parseInt(st.nextToken());

        for (int i = 0; i < Y; i++) {
            st=new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < X; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int loop=Math.min(Math.round(Y/2),Math.round(X/2));

        for (int i = 0; i < cnt; i++) {
            int sy=0;
            int sx=0;

            for (int j = 0; j <loop ; j++) {
                sy+=j;
                sx+=j;
                int start=map[sy][sx];
                int d=0;

                while (d<4) {
                    int ny=dy[d]+sy;
                    int nx=dx[d]+sx;

                    if( j<=ny && 0+j<=nx && Y-j<ny && X-j<nx){
                        map[sy][sx]=map[ny][nx];
                    }

                }
            }
        }

    }



}
