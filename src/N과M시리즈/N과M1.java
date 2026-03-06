package N과M시리즈;

import java.io.*;
import java.util.*;

public class N과M1 {
    static int N;
    static int[] path;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        path=new int[M];
        v=new boolean[N];

        recursive(0);
    }

    public static void recursive(int level){
        if(level==path.length){
            for(int sel:path){
                System.out.printf(sel+" ");
            }
            System.out.println();
            return;
        }

        for(int i=0;i<N;i++){
            if(!v[i]){
                v[i]=true;
                path[level]=i+1;
                recursive(level+1);
                v[i]=false;
            }
        }
    }
}
