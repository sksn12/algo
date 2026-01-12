package 코드트리;

import java.io.*;
import java.util.*;

public class 청소는즐거워 {
    static int N;
    static int answer=0;
    static int[][] map;
    static boolean[][] v;
    static int[] dy={0,1,0,-1};
    static int[] dx={-1,0,1,0};

    // 1, 2, 5, 7, 10, a
    static int[][] ndy={
            {-1,1,-2,2,0,-1,1,-1,1,0},
            {-1,-1,0,0,2,0,0,1,1,1},
            {-1,1,-2,2,0,-1,1,-1,1,0},
            {1,1,0,0,-2,0,0,-1,-1,-1}
    };
    static int[][] ndx={
            {1,1,0,0,-2,0,0,-1,-1,-1},
            {-1,1,-2,2,0,-1,1,-1,1,0},
            {-1,-1,0,0,2,0,0,1,1,1},
            {-1,1,-2,2,0,-1,1,-1,1,0}
    };

    static int Y,X,D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        map=new int[N][N];
        v=new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        Y=N/2;
        X=N/2;
        v[Y][X]=true;

        D=0;
        while (true){
            if(Y==0 && X==0)break;

            int ny=Y+dy[D];
            int nx=X+dx[D];

            if(0<=ny && 0<=nx && ny<N && nx<N){
                if(!v[ny][nx]){
                    v[ny][nx]=true;
                    Y=ny;
                    X=nx;

//                    System.out.println(Y+" "+X+" "+D);

                    clean();

                    D+=1;
                    if(D>3)D=0;
                }else{
                    if(D==0)D=3;
                    else D-=1;
                }
            }

//            print();
        }

        System.out.println(answer);
    }

    public static void clean(){
        int munzi=map[Y][X];
        map[Y][X]=0;
        int one= (int) Math.floor(munzi*0.01);
        int two= (int) Math.floor(munzi*0.02);
        int five= (int) Math.floor(munzi*0.05);
        int seven= (int) Math.floor(munzi*0.07);
        int ten= (int) Math.floor(munzi*0.1);

        for (int d = 0; d < 10; d++) {
            int ny=Y+ndy[D][d];
            int nx=X+ndx[D][d];

            if(0<=ny && 0<=nx && ny<N && nx<N){
                if(d==0 || d==1)map[ny][nx]+=one;
                else if(d==2 || d==3)map[ny][nx]+=two;
                else if(d==4)map[ny][nx]+=five;
                else if(d==5 || d==6)map[ny][nx]+=seven;
                else if(d==7 || d==8)map[ny][nx]+=ten;
                else if(d==9){
                    map[ny][nx]+=munzi-((one+two+seven+ten)*2+five);
                }
            }else{
                if(d==0 || d==1)answer+=one;
                else if(d==2 || d==3)answer+=two;
                else if(d==4)answer+=five;
                else if(d==5 || d==6)answer+=seven;
                else if(d==7 || d==8)answer+=ten;
                else if(d==9){
                    answer+=munzi-((one+two+seven+ten)*2+five);
                }
            }
        }
    }

    public static void print(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("=============");
    }
}
