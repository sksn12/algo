package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분수열의합 {
    static int N,S;
    static int[] arr;
    static boolean[] v;
    static int answer=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        S=Integer.parseInt(st.nextToken());

        arr=new int[N];
        v=new boolean[N];

        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }

        recursive(0);
        System.out.println(answer);
    }
    public static void recursive(int level){
        if(level==N){
            int total=0;
            boolean val=false;

            for (int i = 0; i < N; i++) {
                if(v[i]){
                    total+=arr[i];
                    val=true;
                }
            }

            // val -> 공집합 조건 제거
            if(total==S && val){
                answer+=1;
            }
            return;
        }

        v[level]=true;
        recursive(level+1);
        v[level]=false;
        recursive(level+1);
    }
}
