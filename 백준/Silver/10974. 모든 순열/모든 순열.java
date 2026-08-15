
import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static int[] sel;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());

        arr=new int[N];
        sel=new int[N];
        v=new boolean[N];

        for (int i = 1; i <=N ; i++) {
            arr[i-1]=i;
        }

        recursive(0);
    }

    public static void recursive(int level){
        if(level== arr.length){
            for (int i = 0; i < arr.length; i++) {
                System.out.printf(sel[i]+" ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(!v[i]){
                v[i]=true;
                sel[level]=arr[i];
                recursive(level+1);
                v[i]=false;
            }

        }
    }
}
