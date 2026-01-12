
import java.io.*;
import java.util.*;

public class Main {
    static int T,N,answer;
    static int[] arr={1,2,3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T=Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st=new StringTokenizer(br.readLine());
            answer=0;
            N=Integer.parseInt(st.nextToken());
            dfs(0);

            System.out.println(answer);
        }
    }

    public static void dfs(int total){
        if(total>N)return;

        if(total==N){
            answer+=1;
            return;
        }

        dfs(total+1);
        dfs(total+2);
        dfs(total+3);
    }
}


/*
     [문제] 현재 순열이나 부분집합으로 조합을 짜려고 햇는데 1,3,5의 갯수가 정해지지 않아 사용 불가능
     -> dfs 써야할듯
 */