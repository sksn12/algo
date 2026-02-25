
import java.io.*;
import java.util.*;

public class Main {
    static int M,N;
    static int[][] map;
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

            // 시작 위치
            sy=M-1; sx=0;

            for (int j = zero; j>0 ; j--) {
                if(sy<=0) sx+=1;
                else sy-=1;
            }

            for (int j = one; j>0 ; j--) {
                map[sy][sx]+=1;

                if(sy<=0) sx+=1;
                else sy-=1;
            }

            for (int j = two; j>0 ; j--) {
                map[sy][sx]+=2;

                if(sy<=0) sx+=1;
                else sy-=1;
            }
        }

        for (int j = 1; j < M; j++) {
            for (int k = 1; k < M; k++) {
                int max=0;

                for (int d = 0; d < 3; d++) {
                    int ny=j+dy[d];
                    int nx=k+dx[d];

                    if(0<=ny && ny<M && 0<=nx && nx<M){
                        max=Math.max(map[ny][nx],max);
                    }
                }

                map[j][k]=max;
            }
        }

        print();

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
