package N과M시리즈;

import java.io.*;
import java.util.*;

public class N과M2 {
    static int N;
    static int[] path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        path=new int[M];

        recursive(0,1);
    }

    public static void recursive(int level,int start){
        if(level==path.length){
            for(int p:path){
                System.out.printf(p+" ");
            }
            System.out.println();
            return;
        }

        for(int i=start;i<=N;i++){
            path[level]=i;
            recursive(level+1,i+1);
        }
    }

}
