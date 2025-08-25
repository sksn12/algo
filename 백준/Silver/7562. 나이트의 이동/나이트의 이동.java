import java.util.*;
import java.io.*;

public class Main {
    static int N,ey,ex;
    static int[] dy={-2,-1,1,2,2,1,-1,-2};
    static int[] dx={1,2,2,1,-1,-2,-2,-1};
    static int[][] map;

    static class Node{
        int y,x,cnt;

        Node(int y,int x,int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }
    }
    static Queue<Node> q;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int T=Integer.parseInt(st.nextToken());

        for(int tc=0;tc<T;tc++){
            st=new StringTokenizer(br.readLine());
            N=Integer.parseInt(st.nextToken());

            q=new LinkedList<>();

            st=new StringTokenizer(br.readLine());
            int sy=Integer.parseInt(st.nextToken());
            int sx=Integer.parseInt(st.nextToken());

            q.offer(new Node(sy,sx,0));

            st=new StringTokenizer(br.readLine());
            ey=Integer.parseInt(st.nextToken());
            ex=Integer.parseInt(st.nextToken());

            map=new int[N][N];

            if(sy==ey && sx==ex){
                System.out.println(0);
                continue;
            }
            BFS();
        }
    }

    public static void BFS(){
        while(!q.isEmpty()){
            Node n=q.poll();

            for(int d=0;d<8;d++){
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];

                if(0<=ny && ny<N && 0<=nx && nx<N && map[ny][nx]==0){
                    map[ny][nx]=1;

                    if(ny==ey && nx==ex){
                        System.out.println(n.cnt+1);
                        return;
                    }

                    q.offer(new Node(ny,nx,n.cnt+1));
                }
            }
        }
    }
}
