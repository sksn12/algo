package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class z {
    static int N,Y,X;
    static int num=0;
    static int[] dy={0,0,1,1};
    static int[] dx={0,1,0,1};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        int cnt=2;
        for (int i = 1; i < N; i++) {
            cnt*=2;
        }

        map=new int[cnt][cnt];
        // y,x는 시작점
        recursive(cnt,0,0);

        for (int i = 0; i < cnt;i++) {
            for (int j = 0; j < cnt; j++) {
                System.out.printf(map[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println(map[Y][X]);
    }

    private static void recursive(int n,int y,int x) {
        if(n/2==1){
            for (int d = 0; d < 4; d++) {
                int ny=dy[d]+y;
                int nx=dx[d]+x;

                map[ny][nx]=num++;
            }


            return;
        }
        // 왼쪽 위
        recursive(n/2,0, 0);
        // 오른쪽 위
        recursive(n/2,0,n/2);
        // 왼쪽 아래
        recursive(n/2,n/2,0);
        // 오른쪽 아래
        recursive(n/2,n/2,n/2);
    }
}
