import java.util.*;
import java.io.*;

public class Main {
    static int[] arr;
    static int[] path;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int K=Integer.parseInt(st.nextToken());
            if(K==0)break;

            arr=new int[K];
            path=new int[6];

            for(int i=0;i<K;i++){
                arr[i]=Integer.parseInt(st.nextToken());
            }

            recursive(0,0);
            System.out.println();
        }
    }

    public static void recursive(int start,int level){
        if(level==6){
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<6;i++){
                sb.append(path[i]+" ");
            }

            System.out.println(sb);
            return;
        }

        for(int i=start;i<arr.length;i++){
            path[level]=arr[i];
            recursive(i+1,level+1);
        }
    }
}
