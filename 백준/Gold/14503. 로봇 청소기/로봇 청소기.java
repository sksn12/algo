import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int Y,X,answer=0;
    static int[][] map;
    static Queue<Node> q=new LinkedList<>();
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};
    static class Node{
        int y;
        int x;
        int d;

        Node(int y,int x,int d){
            this.y=y;
            this.x=x;
            this.d=d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());

        int y=Integer.parseInt(st.nextToken());
        int x=Integer.parseInt(st.nextToken());
        int d=Integer.parseInt(st.nextToken());
        if(y<0 || x<0 || y>=Y || x>=X){
            System.out.println(0);
            return;
        }
        q.offer(new Node(y,x,d));

        map=new int[Y][X];

        for (int i = 0; i < Y; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        BFS();
        System.out.println(answer);
    }

    private static void BFS() {
        while (!q.isEmpty()){
            Node N=q.poll();

            if(map[N.y][N.x]==0){
                map[N.y][N.x]=2;
                answer+=1;
            }

            boolean val=false;

            // 4칸 중 청소되지 않은 칸이 있는지 확인
            for (int d = 0; d < 4; d++) {
                int ny= N.y+dy[d];
                int nx=N.x+dx[d];

                if(0<=ny && 0<= nx && ny<Y && nx<X){
                    if(map[ny][nx]==0)val=true;
                }
            }

            // 청소되지 않은 칸이 있다면  [ 0 1 2 3 북 동 남 서 ]
            if(val){
                int ND=N.d;
                for (int d = 0; d < 4; d++) {
                    ND=ND-1;
                    if(ND<0)ND=3;

                    int ny= N.y+dy[ND];
                    int nx=N.x+dx[ND];

                    if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx]==0){
                        answer+=1;
                        map[ny][nx]=2;
                        q.offer(new Node(ny,nx,ND));
                        break;
                    }
                }
            }else {
                int ND=N.d;
                if(ND==0)ND=2;
                else if (ND==1) ND=3;
                else if (ND==2) ND=0;
                else if (ND==3)ND=1;

                int ny= N.y+dy[ND];
                int nx=N.x+dx[ND];

                if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx]!=1){
                    q.offer(new Node(ny,nx,N.d));
                }else{
                    q=new LinkedList<>();
                }
            }

        }
    }
}
