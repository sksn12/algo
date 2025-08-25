package N과M시리즈;

import java.io.*;
import java.util.*;

public class N과M4 {
    static int N;
    static int[] path;
    static StringBuilder sb=new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        path=new int[M];
        recursive(0);
        System.out.println(sb);

    }

    public static void recursive(int level){
        if(level==path.length){
            boolean val=true;
            for(int i=0;i<path.length-1;i++){
                if(path[i]>path[i+1]){
                    val=false;
                    break;
                }
            }

            if(val){
                for(int sel:path){
                    sb.append(sel).append(" ");
                }
                sb.append("\n");
            }

            return;
        }

        for(int i=0;i<N;i++){
            path[level]=i+1;
            recursive(level+1);
        }
    }
}
