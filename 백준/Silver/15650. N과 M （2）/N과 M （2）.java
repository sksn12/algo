
import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[] arr;
    static int[] sel;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        arr=new int[N];
        sel=new int[M];
        v=new boolean[N];

        for (int i = 1; i <=N; i++) {
            arr[i-1]=i;
        }

        recursive(0,0);
    }

    public static void recursive(int level,int start){
        if(level==M){
            for (int i = 0; i < sel.length; i++) {
                System.out.printf(sel[i]+" ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < N; i++) {
            if(!v[i]){
                v[i]=true;
                sel[level]=arr[i];
                recursive(level+1,i+1);
                v[i]=false;
            }
        }
    }
}
