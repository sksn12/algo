package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 네트워크연결 {
    static int N,M,answer=Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        M=Integer.parseInt(br.readLine());

        map=new int[N+1][N+1]; // 인접 행렬 생성

        for (int i = 0; i < M; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());

            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());

            map[a][b]=c;
            map[b][a]=c;
        }

        // dfs를 돌릴때 현재 라인 i,총합 cnt, 방문배열? 들고 다니면 됨
       boolean[] v=new boolean[N+1];
        dfs(1,0,v);

        System.out.println(answer);
    }

    private static void dfs(int idx, int total, boolean[] v) {
        if(idx==N){
            answer=Math.min(answer,total);
            return;
        }

        for (int i = idx; i <= N ; i++) {
            if(map[idx][i]!=0 && !v[i]){
                v[i]=true;
                System.out.println(idx+" "+i+" "+map[idx][i]);

                dfs(idx+1,total+map[idx][i],v);
            }
        }
    }
}
