package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 안전영역 {
    static int N,answer=1,maxHeight=Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] v;
    static Queue<int[]> q;
    static int[] dy={0,1,0,-1};
    static int[] dx={1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        map=new int[N][N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
                maxHeight=Math.max(map[i][j],maxHeight);
            }
        }


        for (int i = 1; i < maxHeight; i++) {
            v=new boolean[N][N];

            Down(i); // 현재 i 값 이하인 높이들은 사용불가

            int cnt=0;

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if(!v[j][k]){
                        q=new LinkedList<>();
                        q.offer(new int[]{j,k});
                        v[j][k]=true;
                        BFS();
                        cnt+=1;
                    }
                }
            }

            answer=Math.max(cnt,answer);
        }

        System.out.println(answer);
    }

    private static void BFS() {
        while (!q.isEmpty()){
            int[] yx=q.poll();
            int y=yx[0];
            int x=yx[1];

            for (int d = 0; d < 4; d++) {
                int ny=y+dy[d];
                int nx=x+dx[d];

                if(0<=ny && 0<=nx && ny<N && nx<N && !v[ny][nx]){
                    q.offer(new int[] {ny,nx});
                    v[ny][nx]=true;
                }
            }
        }
    }

    private static void Down(int i) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                if(map[j][k]<=i)v[j][k]=true;
            }
        }

    }
}
