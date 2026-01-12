package 백준;

import java.io.*;
import java.util.*;

public class RGB거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());

        int[][] map=new int[N][3];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());

            int red=Integer.parseInt(st.nextToken());
            int green=Integer.parseInt(st.nextToken());
            int blue=Integer.parseInt(st.nextToken());

            map[i][0]=red;
            map[i][1]=green;
            map[i][2]=blue;
        }

        int[][] sel=new int[N][3];

        sel[0][0]= map[0][0];
        sel[0][1]= map[0][1];
        sel[0][2]= map[0][2];

        for (int i = 1; i < N; i++) {
            sel[i][0]=map[i][0]+Math.min(sel[i-1][1],sel[i-1][2]);
            sel[i][1]=map[i][1]+Math.min(sel[i-1][0],sel[i-1][2]);
            sel[i][2]=map[i][2]+Math.min(sel[i-1][0],sel[i-1][1]);
        }

        System.out.println(Math.min(Math.min(sel[N-1][0],sel[N-1][1]),sel[N-1][2]));
    }
}
