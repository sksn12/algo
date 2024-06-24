
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M,answer=Integer.MAX_VALUE;
    static int[][] map;
    static Queue<node> q=new LinkedList<>();
    static int[] dy={1,1,1};
    static int[] dx={-1,0,1};

    static class node{
        int fuel;
        int dir;
        int y,x;

        node(int fuel,int dir,int y,int x){
            this.fuel=fuel;
            this.dir=dir;
            this.y=y;
            this.x=x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        
        map=new int[N][M];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < M; i++) {
            q.offer(new node(map[0][i],-1,0,i));
        }

        BFS();

        System.out.println(answer);
    }

    private static void BFS() {
        while (!q.isEmpty()){
            node n=q.poll();

            for (int i = 0; i < 3; i++) {
                if(n.dir==i)continue;

                int ny=dy[i]+n.y;
                int nx=dx[i]+n.x;

                if(0<=ny && 0<=nx && ny<N && nx<M){
                    int addFuel=n.fuel+map[ny][nx];

                    if(ny==N-1)answer=Math.min(answer,addFuel);
                    else q.offer(new node(addFuel,i,ny,nx));
                }
            }
        }

    }
}
