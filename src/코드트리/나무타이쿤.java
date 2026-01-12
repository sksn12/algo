package 코드트리;

import java.io.*;
import java.util.*;

public class 나무타이쿤 {
    static int N,Y;
    static int[][] map;
    static int[] dy={0,0,-1,-1,-1,0,1,1,1};
    static int[] dx={0,1,1,0,-1,-1,-1,0,1};
    static Queue<int[]> vita=new ArrayDeque<>();
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        Y=Integer.parseInt(st.nextToken());

        map=new int[N][N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        vita.add(new int[] {N-1,0});
        vita.add(new int[] {N-2,0});
        vita.add(new int[] {N-1,1});
        vita.add(new int[] {N-2,1});

        for (int i = 0; i < Y; i++) {
            st=new StringTokenizer(br.readLine());
            int d=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            v=new boolean[N][N];

            // 1. 영양제 이동
            move(d,m);

            // 4. 높이가 2이상인 리브로수 위치를 찾아 높이를 베고 영양제 사기
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if(map[j][k]>=2 && !v[j][k]){
                        map[j][k]-=2;
                        vita.offer(new int[] {j,k});
                    }
                }
            }
        }

        int answer=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer+=map[i][j];
            }
        }
        System.out.println(answer);
    }

    public static void move(int d,int m){
        Queue<int[]> tmp=new ArrayDeque<>();
        while(!vita.isEmpty()){
            int[] yx=vita.poll();
            int ny=yx[0];
            int nx=yx[1];

            for (int i = 0; i < m; i++) {
                ny+=dy[d];
                nx+=dx[d];

                if(ny>N-1)ny=0;
                if(nx>N-1)nx=0;
                if(ny<0)ny=N-1;
                if(nx<0)nx=N-1;
            }
            tmp.add(new int[] {ny,nx});

            // 2. 영양제 투입
            map[ny][nx]+=1;
        }

        // 3. 영양제 투입한 리브로수의 대각선마다 갯수 확인
        while (!tmp.isEmpty()){
            int[] yx=tmp.poll();

            int cnt=0;
            for (int i = 1; i <= 4; i++) {
                int ny=dy[i*2]+yx[0];
                int nx=dx[i*2]+yx[1];

                if(0<=ny && 0<=nx && ny<N && nx<N && map[ny][nx]>=1)cnt+=1;
            }

            map[yx[0]][yx[1]]+=cnt;
            v[yx[0]][yx[1]]=true;
        }
    }

}





