package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 바이러스 {

    static boolean[][] map;
    static boolean[] v;
    static int answer=0;
    static int N,M;
    static Queue<Integer> q=new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        M=Integer.parseInt(st.nextToken());

        map=new boolean[N+1][N+1];
        v=new boolean[N+1];

        for (int i = 1; i <= M ; i++) {
            st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());

            map[n][m]=true;
            map[m][n]=true;
        }

        for(int i=2;i<=N;i++){
            if(map[1][i])q.offer(i);
        }

        BFS();

        for (int i = 2; i <=N ; i++) {
            if(v[i])answer+=1;
        }
        System.out.println(answer);
    }

    private static void BFS() {
        while (!q.isEmpty()){
            int n=q.poll();
            v[n]=true;

            for (int i = 1; i <=N; i++) {
                if(!v[i] && map[n][i])q.offer(i);
            }
        }
    }
}
