package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 마법사상어와파이어스톰 {
    static int N=1,Q,max_area=0;
    static int[][] map;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static Queue<int[]> q;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++)N*=2;
        Q=Integer.parseInt(st.nextToken());

        map=new int[N][N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        st=new StringTokenizer(br.readLine());
        for (int q = 0; q < Q; q++) {
            int l=Integer.parseInt(st.nextToken());

            // 1. 구역 나누기
            int L=1;
            for (int i = 0; i < l; i++)L*=2;
            divide(L);

            // 2. 모든 얼음마다 사방으로 인접한 얼음이 3개 미만이면 해당 얼음 -1
            melting();
        }

        // 3. 남아있는 얼음의 합 출력
        System.out.println(print_ice_total());

        // 4. 가장 큰 덩어리가 차지하는 칸의 갯수 출력 (BFS로 가장 큰 영역 찾기)
        System.out.println(print_bigger_ice_area());
    }

    public static int print_ice_total(){
        int sum=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum+=map[i][j];
            }
        }
        return sum;
    }

    public static int print_bigger_ice_area(){
        q=new ArrayDeque<>();
        v=new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!v[i][j] && map[i][j]!=0){
                    q.offer(new int[] {i,j});
                    v[i][j]=true;
                    max_area=Math.max(BFS(1),max_area);
                }
            }
        }

        return max_area;
    }

    public static int BFS(int area){
        while (!q.isEmpty()){
            int[] yx=q.poll();

            for (int d = 0; d < 4; d++) {
                int ny=dy[d]+yx[0];
                int nx=dx[d]+yx[1];

                if(0<=ny && 0<=nx && ny<N && nx<N && map[ny][nx]>0 && !v[ny][nx]){
                    area+=1;
                    v[ny][nx]=true;
                    q.offer(new int[] {ny,nx});
                }
            }
        }

        return area;
    }

    public static void divide(int L){
        int[][] tmp = new int[N][N];

        for (int sy = 0; sy < N; sy += L) {
            for (int sx = 0; sx < N; sx += L) {

                for (int y = 0; y < L; y++) {
                    for (int x = 0; x < L; x++) {
                        // 회전 후 좌표 계산
                        tmp[sy + x][sx + L - 1 - y] = map[sy + y][sx + x];
                    }
                }

            }
        }

        // 회전된 결과를 다시 map에 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j]=tmp[i][j];
            }
        }
    }

    public static void melting(){
        // 동시에 얼음을 녹이기 위해 ice_list에 녹이려는 얼음 위치 담아둠
        List<int[]> ice_list=new ArrayList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {

                int cnt=0;

                for (int d = 0; d < 4; d++) {
                    int ny=dy[d]+y;
                    int nx=dx[d]+x;

                    if(0<=ny && 0<=nx && ny<N && nx<N && map[ny][nx]>0){
                        cnt+=1;
                    }
                }

                if(cnt<3 && map[y][x]!=0)ice_list.add(new int[] {y,x});
            }
        }

        for (int i = 0; i < ice_list.size(); i++) {
            int[] yx=ice_list.get(i);
            map[yx[0]][yx[1]]-=1;
        }
    }
}
