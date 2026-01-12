package 백준;

import java.io.*;
import java.util.*;

public class 회전초밥 {
    static int answer=0;
    static int[] D;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int d=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());

        int[] sushi=new int[N];
        D=new int[d+1]; // 초밥 종류

        for(int i=0;i<N;i++){
            sushi[i]=Integer.parseInt(br.readLine());
        }

        // 초기 스시 갯수 세기
        for(int i=0;i<k;i++){
            D[sushi[i]]+=1;
        }

        count();

        int p2=k;
        for(int p1=1;p1<N;p1++){
            D[sushi[p1-1]]-=1;

            if(p2==N)p2=0;
            D[sushi[p2++]]+=1;

            count();
        }

        System.out.println(answer);
    }

    public static void count(){
        // 먹을수있는 스시 종류 세기
        int sum=0;
        for(int i=1;i<D.length;i++){
            if(D[i]>0)sum+=1;
        }
        if(D[c]==0)sum+=1;
        answer=Math.max(answer,sum);
    }
}