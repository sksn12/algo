
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dy={-1,-2,-2,-1,1,2,2,1};
    static int[] dx={-2,-1,1,2,2,1,-1,-2};
    static Queue<chass> q;
    static boolean[][] map;
    static class chass{
        int y,x,cnt;

        chass(int y,int x,int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T=Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            N=Integer.parseInt(br.readLine());
            q=new ArrayDeque<>();
            map=new boolean[N][N];

            st=new StringTokenizer(br.readLine());
            int sy=Integer.parseInt(st.nextToken());
            int sx=Integer.parseInt(st.nextToken());
            q.offer(new chass(sy, sx,0));

            st=new StringTokenizer(br.readLine());
            int ey=Integer.parseInt(st.nextToken());
            int ex=Integer.parseInt(st.nextToken());

            if(sy==ey && sx==ex){
                System.out.println(0);
                continue;
            }

            map[sy][sx]=true;
            BFS(ey,ex);
        }
    }

    public static void BFS(int ey,int ex){
        while (!q.isEmpty()){
            chass c=q.poll();

            for (int d = 0; d < 8; d++) {
                int ny=c.y+dy[d];
                int nx=c.x+dx[d];

                if(0<=ny && ny<N && 0<=nx && nx<N && !map[ny][nx]){
                    if(ey==ny && ex==nx){
                        System.out.println(c.cnt+1);
                        return;
                    }

                    map[ny][nx]=true; // 방문 했던 기준을 통해 시간복잡도를 줄임, 어차피 BFS는 최단거리임으로 뒤에 오는 순서는 해당 경로를 거치고 최단경로가 될수없음(목적지에 도착하는 경료여도 제일빨리간놈만)
                    q.offer(new chass(ny,nx,c.cnt+1));
                }
            }
        }
    }
}
