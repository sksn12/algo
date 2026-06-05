package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 한줄로서기 {
    static int N;
    static int[] arr;
    static int[] path;
    static int[] map;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        arr=new int[N];
        path=new int[N];
        map=new int[N];
        v=new boolean[N];

        for (int i = 0; i < N; i++) {
            arr[i]=i+1;
            map[i]=Integer.parseInt(st.nextToken());
        }

        recursive(0);
    }

    private static void recursive(int level) {
        if(level==N){
            Test();
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!v[i]){
                v[i]=true;
                path[level]=arr[i];
                recursive(level+1);
                v[i]=false;
            }

        }

    }

    private static void Test() {
        for (int i = 0; i < N; i++) {

            int val=0;
            for (int j = 0; j < map[j]; j++) {
                if(path[j]>arr[i]) val+=1;
            }
            System.out.println(val +" "+arr[i]);
            if(val!=map[i])return;
        }

        for (int i:path) {
            System.out.printf(i+" ");
        }
    }
}
