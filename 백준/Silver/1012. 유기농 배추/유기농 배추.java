
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T,Y,X,answer;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static int[][] map;
    static boolean[][] v;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        T=Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st=new StringTokenizer(br.readLine());

            X=Integer.parseInt(st.nextToken());
            Y=Integer.parseInt(st.nextToken());
            int K=Integer.parseInt(st.nextToken());

            v=new boolean[Y][X];
            map=new int[Y][X];
            answer=0;

            for (int k = 0; k < K; k++) {
                st=new StringTokenizer(br.readLine());

                int x=Integer.parseInt(st.nextToken());
                int y=Integer.parseInt(st.nextToken());

                map[y][x]=1;
            }

            q=new ArrayDeque<>();

            for (int i = 0; i < Y; i++) {
                for (int j = 0; j < X; j++) {
                    if(map[i][j]==1 && !v[i][j]){
                        answer+=1;
                        v[i][j]=true;
                        q.offer(new int[] {i,j});
                        BFS();
                    }
                }
            }

            System.out.println(answer);
        }
    }

    public static void BFS(){
        while (!q.isEmpty()){
            int[] yx=q.poll();

            for (int d = 0; d < 4; d++) {
                int ny=dy[d]+yx[0];
                int nx=dx[d]+yx[1];

                if(0<=ny && ny<Y && 0<=nx && nx<X && map[ny][nx]==1 && !v[ny][nx]){
                    v[ny][nx]=true;
                    q.offer(new int[] {ny,nx});
                }

            }
        }
    }
}
