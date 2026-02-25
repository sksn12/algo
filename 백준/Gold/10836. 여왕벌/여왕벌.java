
import java.io.*;
import java.util.*;

public class Main {
    static int M,N;
    static int[][] map;
    static int[][] grow;
    static int sy,sx;
    static int[] dy={0,-1,-1};
    static int[] dx={-1,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());

        map=new int[M][M];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j]=1;
            }
        }

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            int zero=Integer.parseInt(st.nextToken());
            int one=Integer.parseInt(st.nextToken());
            int two=Integer.parseInt(st.nextToken());

            grow=new int[M][M];

            // 시작 위치
            sy=M-1; sx=0;

            for (int j = zero; j>0 ; j--) {
                if(sy<=0) sx+=1;
                else sy-=1;
            }

            for (int j = one; j>0 ; j--) {
                map[sy][sx]+=1;
                grow[sy][sx]=1;

                if(sy<=0) sx+=1;
                else sy-=1;
            }

            for (int j = two; j>0 ; j--) {
                map[sy][sx]+=2;
                grow[sy][sx]=2;

                if(sy<=0) sx+=1;
                else sy-=1;
            }

//            System.out.println(i+1 +"일차 대각쪽 먼저 증가 완료");
//            print();
//            System.out.println("-----------------------");

            for (int j = 1; j < M; j++) {
                for (int k = 1; k < M; k++) {
                    int max=0;

                    for (int d = 0; d < 3; d++) {
                        int ny=j+dy[d];
                        int nx=k+dx[d];

                        if(0<=ny && ny<M && 0<=nx && nx<M){
                            max=Math.max(grow[ny][nx],max);
                        }
                    }

                    grow[j][k]=max;
                    map[j][k]+=max;
                }
            }

//            System.out.println(i+1 +"일차 나머지 증가 완료");
            print();
//            System.out.println("-----------------------");
        }
    }

    public static void print(){
        for (int i = 0; i <M ; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
