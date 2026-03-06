
import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int y,x,cnt;

        Node(int y,int x,int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }
    }

    static Queue<Node> q;

    static String[][] map;
    static int Y,X;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static boolean[][] v;
    static int answer=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new String[Y][X];

        for (int i = 0; i < Y; i++) {
            String[] str=br.readLine().split("");
            for (int j = 0; j < X; j++) {
                map[i][j]=str[j];
            }
        }

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if(map[i][j].equals("L")){
                    v=new boolean[Y][X];
                    q=new ArrayDeque<>();

                    q.offer(new Node(i,j,0));
                    v[i][j]=true;

                    BFS();
                }
            }
        }

        System.out.println(answer);
    }

    public static void BFS(){
        while (!q.isEmpty()){
            Node n=q.poll();

            for (int d = 0; d < 4; d++) {
                int ny=dy[d]+n.y;
                int nx=dx[d]+n.x;

                if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx] && map[ny][nx].equals("L")){
                    v[ny][nx]=true;
                    int next=n.cnt+1;
                    answer=Math.max(next,answer);
                    q.offer(new Node(ny,nx,next));
                }
            }
        }

    }
}
