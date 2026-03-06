
import java.io.*;
import java.util.*;

public class Main {
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static int[][] map;
    static int Y,X;
    static Queue<int[]> q=new ArrayDeque<>();
    static int day=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X=Integer.parseInt(st.nextToken());
        Y=Integer.parseInt(st.nextToken());
        map=new int[Y][X];

        for (int i = 0; i < Y; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if(map[i][j]==1)q.offer(new int[] {i,j});
            }
        }

        BFS();

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if(map[i][j]==0){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(day-1);
    }

    public static void BFS(){
        while (!q.isEmpty()){
            int size=q.size();

            for (int i = 0; i < size; i++) {
                int[] yx=q.poll();
                int y=yx[0];
                int x=yx[1];

                for (int d = 0; d < 4; d++) {
                    int ny=dy[d]+y;
                    int nx=dx[d]+x;

                    if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx]==0){
                        map[ny][nx]=1;
                        q.offer(new int[] {ny,nx});
                    }
                }
            }

            day+=1;
        }
    }
}
