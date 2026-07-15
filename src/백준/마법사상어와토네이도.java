package 백준;

import java.io.*;
import java.util.*;

public class 마법사상어와토네이도 {
    static int N,TY,TX,D,answer;
    static int[][] map;
    static boolean[][] v;
    static int[] dy={0,1,0,-1};
    static int[] dx={-1,0,1,0};

    // 비율은 순서대로 (1,2,5,7,10)
    static int[][] send_Y={
            {-1,1,2,-2,0,-1,1,-1,1},
            {0,0,1,1,3,1,1,2,2},
            {-1,1,-2,2,0,-1,1,-1,1},
            {0,0,-1,-1,-3,-1,-1,-2,-2}
    };

    static int[][] send_X={
            {0,0,-1,-1,-3,-1,-1,-2,-2},
            {-1,1,-2,2,0,-1,1,-1,1},
            {0,0,1,1,3,1,1,2,2},
            {-1,1,-2,2,0,-1,1,-1,1}
    };

    static int[] move_a_Y={0,2,0,-2};
    static int[] move_a_X={-2,0,2,0};

    static double[] balance={0.01,0.01,0.02,0.02,0.05,0.07,0.07,0.1,0.1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map=new int[N][N];
        v=new boolean[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        TY=N/2;
        TX=N/2;
        D=0;
        v[TY][TX]=true;

        // 1. 토네이도 이동
        while(true){
            if(TY==0 && TX==0)break;

            int ny=dy[D]+TY;
            int nx=dx[D]+TX;

            if(!v[ny][nx]){
                v[ny][nx]=true;
                // 2. 모래 이동
                cal(map[ny][nx]); // 이동하려는 방향의 모래 비율 계산

                TY=ny;
                TX=nx;

                map[TY][TX]=0; // 토네이도 위치에는 모래가 남아 있지 않음

                D+=1;
                if(D==4)D=0;
            }else{
                D-=1;
                if(D<0)D=3;
            }
        }

        System.out.println(answer);
    }

    public static void cal(int send_total){
        int use_send=0;

        for(int i=0;i<send_Y[0].length;i++){
            int ny=TY+send_Y[D][i];
            int nx=TX+send_X[D][i];

            int cal_send = (int) Math.floor(balance[i] * (double) send_total);

            if(0<=ny && 0<=nx && ny<N && nx<N){
                if(cal_send>0){
                    map[ny][nx]+=cal_send;
                    use_send+=cal_send;
                }
            }else{
                // 모레가 범위를 넘어섰고 정수 1을 넘긴 경우
               if(cal_send>0){
                   answer+=cal_send;
                   use_send+=cal_send;
               }
            }
        }

        int a=send_total-use_send;
        // 남은 모래(a) 범위안에 들어가면 넣기 아니면 정답에 더하기

        int ny=move_a_Y[D]+TY;
        int nx=move_a_X[D]+TX;
        if(0<=ny && 0<=nx && ny<N && nx<N){
            map[ny][nx]+=a;
        }else answer+=a;
    }
}
