import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node>{
        int y,x,cnt;

        Node(int y,int x, int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }

        @Override
        public int compareTo(Node n){
            return this.cnt-n.cnt;
        }
    }

    static int [][] map;
    static int Y,X;
    static PriorityQueue<Node> pq=new PriorityQueue<>();
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        X=Integer.parseInt(st.nextToken());
        Y=Integer.parseInt(st.nextToken());

        map=new int[Y][X];

        for (int i = 0; i < Y; i++) {
            String[] str=br.readLine().split("");
            for (int j = 0; j < X; j++) {
                map[i][j]=Integer.parseInt(str[j]);
            }
        }

        if(map[0][0]==1)pq.offer(new Node(0,0,1));
        else pq.offer(new Node(0,0,0));

        if(Y==1 && X==1){
            if(map[0][0]==1)System.out.println(1);
            else System.out.println(0);
        }
        
        v=new boolean[Y][X];
        v[0][0]=true;
        BFS();

    }

    public static void BFS(){
        while (!pq.isEmpty()){
            Node n=pq.poll();

            for (int d = 0; d < 4; d++) {
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];

                if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx]){

                    if(ny==Y-1 && nx==X-1){
                        if(map[ny][nx]==1)System.out.println(n.cnt+1);
                        else System.out.println(n.cnt);

                        return;
                    }

                    v[ny][nx]=true;

                    if(map[ny][nx]==1)pq.offer(new Node(ny,nx,n.cnt+1));
                    else pq.offer(new Node(ny,nx,n.cnt));

                }
            }
        }
    }
}
