
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M,N,K;
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};
    static Queue<int[]> q=new LinkedList<>();
    static List<Integer> area=new ArrayList<>();
    static int cnt,answer=0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        M=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        map=new int[M][N];

        for (int i = 0; i < K; i++) {
            st=new StringTokenizer(br.readLine());

            int sx=Integer.parseInt(st.nextToken());
            int sy=Integer.parseInt(st.nextToken());
            int ex=Integer.parseInt(st.nextToken());
            int ey=Integer.parseInt(st.nextToken());

            for (int j = sy; j < ey; j++) {
                for (int j2 = sx; j2 < ex; j2++) {
                    map[j][j2]=1;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]==0){
                    cnt=1;
                    answer+=1;
                    q.offer(new int[] {i,j});
                    BFS();
                    area.add(cnt);
                }
            }
        }

        Collections.sort(area);
        System.out.println(answer);
        for (int j = 0; j < area.size(); j++) {
            System.out.printf(area.get(j)+" ");
        }

    }

    private static void BFS() {
        while (!q.isEmpty()){
            int[] yx=q.poll();
            int y=yx[0];
            int x=yx[1];
            map[y][x]=1;

            for (int d = 0; d < 4; d++) {
                int ny=dy[d]+y;
                int nx=dx[d]+x;

                if(0<=ny && 0<=nx && ny<M && nx<N && map[ny][nx]==0){
                    cnt+=1;
                    map[ny][nx]=1;
                    q.offer(new int[] {ny,nx});
                }
            }
        }
    }
}
