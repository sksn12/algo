package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 소문난칠공주 {
    static int N=5,answer=0;
    static String[][] map=new String[N][N];
    static int[] sel=new int[7];
    static int[] dy={0,-1,0,1};
    static int[] dx={1,0,-1,0};
    static Queue<int[]> q;
    static boolean[][] check;
    static boolean[][] v;
    static int cnt2;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            String[] str=br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j]=str[j];
            }
        }

        recursive(0,0);
        System.out.println(answer);

    }

    private static void recursive(int start, int level) {
        if(level==sel.length){
            int cnt=0;
            // 이다솜파가 4명 이상인지 확인!
            for (int i = 0; i < sel.length; i++) {
                int y=Math.abs(sel[i]/N);
                int x=sel[i]%N;

                if(map[y][x].equals("S"))cnt+=1;
            }

            // 4명 이상이라면 BFS로 모든 점이 이어져 있는지 확인!
            if(cnt>=4){
                q=new LinkedList<>();
                v=new boolean[N][N];
                check=new boolean[N][N];
                cnt2=1;

                for (int i = 0; i < sel.length; i++) {
                    int y=Math.abs(sel[i]/N);
                    int x=sel[i]%N;

                    check[y][x]=true; // 이어져있는지 확인하기 위한 check 배열

                    if(i==0){
                        q.offer(new int[] {y,x});
                        v[y][x]=true; // BFS를 돌릴때 q 재방문을 막아주는 방문배열
                    }
                }

                // 조합으로 나온 위치들을 true로 표기하고 q에 초기 값을 넣은다음 BFS안에서 조건문을 만족하는 값이 7이면 +1
                BFS();
                if(cnt2==7)answer+=1;

            }

            return;
        }

        for (int i = start; i <25 ; i++) {
            sel[level]=i;
            recursive(i+1,level+1);
        }
    }

    private static void BFS() {
        while (!q.isEmpty()){
            int[] yx=q.poll();
            int y=yx[0];
            int x=yx[1];

            for (int d = 0; d < 4; d++) {
                int ny=dy[d]+y;
                int nx=dx[d]+x;

                if(0<=ny && 0<=nx && ny<N && nx<N && !v[ny][nx] && check[ny][nx]){
                    v[ny][nx]=true;
                    q.offer(new int[] {ny,nx});
                    cnt2+=1;
                }

            }
        }

    }
}
