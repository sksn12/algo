package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 나이트의이동 {
    static int N,ey,ex,cnt;
    static boolean[][] map;
    static int[] dy={-2,-1,1,2,2,1,-1,-2};
    static int[] dx={1,2,2,1,-1,-2,-2,-1};
    static Queue<Node> q;

    static class Node{
        int y;
        int x;
        int cnt;

        Node(int y,int x,int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            N=Integer.parseInt(br.readLine());
            map=new boolean[N][N];
            q=new LinkedList<>();
            cnt=0;

            StringTokenizer st=new StringTokenizer(br.readLine());
            int sy=Integer.parseInt(st.nextToken());
            int sx=Integer.parseInt(st.nextToken());

            q.offer(new Node(sy,sx,0));

            st=new StringTokenizer(br.readLine());
            ey=Integer.parseInt(st.nextToken());
            ex=Integer.parseInt(st.nextToken());

            BFS();
        }



    }

    private static void BFS() {
        while (!q.isEmpty()){
            Node node=q.poll();

            if(node.y==ey && node.x==ex){
                System.out.println(node.cnt);
                break;
            }

            for (int d = 0; d < 8; d++) {
                int ny=node.y+dy[d];
                int nx=node.x+dx[d];

                if(0<=ny && 0<=nx && ny<N && nx<N && !map[ny][nx]){
                    map[ny][nx]=true;
                    q.offer(new Node(ny,nx, node.cnt+1));
                }
            }
        }

    }
}

