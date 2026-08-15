package 싸피11;

import java.io.*;
import java.util.*;

public class 활주로건설 {
    static int N,X,answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T=Integer.parseInt(st.nextToken());

        for(int t=1;t<=T;t++){
            st=new StringTokenizer(br.readLine());

            N=Integer.parseInt(st.nextToken());
            X=Integer.parseInt(st.nextToken());
            answer=0;

            map=new int[N][N];

            // 0. 지형 셋팅
            for(int i=0;i<N;i++){
                st=new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            // 1. 모든 경우 확인
            for(int i=0;i<N;i++){
                row_high(i,0);
                col_high(0,i);
            }

            System.out.println("#"+t+" "+answer);
        }
    }

    // 2. 현재 시작점의 방향으로 활주로 조건이 만족하는지 확인
    public static void row_high(int fix,int start){
        boolean[][] v=new boolean[N][N];

        for(int i=0;i<N-1;i++){
            // 현재 내 위치의 높이보다 다음 높이가 더 크다면 반드시 활주로를 설치해야함
            if(map[fix][start]<map[fix][start+1]){
                // 내 위치의 활주로를 설치해도 다음 활주로의 높이가 크다면 완됨
                if(map[fix][start]+1<map[fix][start+1])return;

                // 활주로의 X(길이)만큼 현재 내값이랑 같은지 확인
                int high=map[fix][start];
                for(int j=0;j<X;j++){
                    if(start-j<0)return;
                    if(high!=map[fix][start-j])return;
                    if(v[fix][start-j])return;
                    v[fix][start-j]=true;
                }

            }

            start+=1;
        }

        // 2-1. 반대 확인
        row_low(fix,N-1,v);
    }

    public static void row_low(int fix,int start,boolean[][] v){
        for(int i=0;i<N-1;i++){
            if(map[fix][start-1]>map[fix][start]){
                if(map[fix][start-1]>map[fix][start]+1)return;

                int high=map[fix][start];
                for(int j=0;j<X;j++){
                    if(start+j>=N)return;
                    if(high!=map[fix][start+j])return;
                    if(v[fix][start+j])return;
                    v[fix][start+j]=true;
                }
            }

            start-=1;
        }

        answer+=1;
    }

    // 0 i
    public static void col_high(int start,int fix){
        boolean[][] v=new boolean[N][N];

        for(int i=0;i<N-1;i++){
            // 현재 내 위치의 높이보다 다음 높이가 더 크다면 반드시 활주로를 설치해야함
            if(map[start][fix]<map[start+1][fix]){
                // 내 위치의 활주로를 설치해도 다음 활주로의 높이가 크다면 완됨
                if(map[start][fix]+1<map[start+1][fix])return;

                // 활주로의 X(길이)만큼 현재 내값이랑 같은지 확인
                int high=map[start][fix];
                for(int j=0;j<X;j++){
                    if(start-j<0)return;
                    if(high!=map[start-j][fix])return;
                    if(v[start-j][fix])return;
                    v[start-j][fix]=true;
                }

            }

            start+=1;
        }

        // 2-1. 반대 확인
        col_low(N-1,fix,v);
    }

    public static void col_low(int start,int fix,boolean[][] v){
        for(int i=0;i<N-1;i++){
            if(map[start-1][fix]>map[start][fix]){
                if(map[start-1][fix]>map[start][fix]+1)return;

                int high=map[start][fix];
                for(int j=0;j<X;j++){
                    if(start+j>=N)return;
                    if(high!=map[start+j][fix])return;
                    if(v[start+j][fix])return;
                    v[start+j][fix]=true;
                }
            }

            start-=1;
        }

        answer+=1;
    }
}
