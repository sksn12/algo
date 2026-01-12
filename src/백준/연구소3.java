package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소3 {
    static int[][] map;
    static int[][] map2;
    static int[][] sel;
    static boolean[][] v;
    static int N,M;
    static List<int[]> list=new ArrayList<>();
    static Queue<int[]> q=new LinkedList<>();
    static int[] dy={0,0,1,-1};
    static int[] dx={1,-1,0,0};
    static int answer=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new int[N][N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());

                if(map[i][j]==2)list.add(new int[] {i,j});
            }
        }

        sel=new int[M][2];

        recursive(0,0);

        if(answer==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void recursive(int level, int idx) {
        if(M==level){
            map2=new int[N][N];
            v=new boolean[N][N];

            for (int i = 0; i < sel.length; i++) {
                q.offer(new int[] {sel[i][0],sel[i][1],0});

                v[sel[i][0]][sel[i][1]]=true;
            }

            BFS();

            // 모든 칸에 바이러스를 퍼트릴 수 없으면 return;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map2[i][j]==0 && map[i][j]==0){
                        return;
                    }
                }
            }

            // 모든 칸에 바이러스가 퍼지면 몇 초가 걸렸는지 찾음
            int tmp=0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tmp=Math.max(map2[i][j],tmp);
                }
            }

            // 몇초가 걸린지 확인한 tmp랑 answer을 비교해서 가장 작은값 출력
            answer=Math.min(tmp,answer);

            return;
        }

        for (int i = idx; i < list.size(); i++) {
            sel[level][0]=list.get(i)[0];
            sel[level][1]=list.get(i)[1];
            recursive(level+1,i+1);
        }
    }

    private static void BFS() {
        while (!q.isEmpty()){
            int[] yx=q.poll();
            int y=yx[0];
            int x=yx[1];
            int cnt=yx[2];

            for (int d = 0; d < 4; d++) {
                int ny=y+dy[d];
                int nx=x+dx[d];

                if(0<=ny && 0<=nx && ny<N && nx<N && (map[ny][nx]==0 || map[ny][nx]==2 )&& !v[ny][nx]){
                    v[ny][nx]=true;
                    map2[ny][nx]=cnt+1;
                    q.offer(new int[] {ny,nx,cnt+1});
                }
            }
        }

    }
}
