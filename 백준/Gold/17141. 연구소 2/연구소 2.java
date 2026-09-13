
import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] map;
    static List<Node> list=new ArrayList<>();
    static Node[] path;
    static class Node{
        int y,x,cnt;

        Node(int y,int x,int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }
    }
    static Queue<Node> q;
    static int answer=Integer.MAX_VALUE;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==2)list.add(new Node(i,j,0));
            }
        }

        path=new Node[M];

        recursive(0,0);

        // 바이러스를 어떻게 놓아도 모든 칸에 바이러스 전파가 불가능한 경우
        if (answer==Integer.MAX_VALUE)answer=-1;

        System.out.println(answer);
    }

    public static void recursive(int level,int start){
        if(level==M){
            int[][] copyMap=new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j <N; j++) {
                    // 벽의 위치를 -1로 표시
                    if(map[i][j]==1)copyMap[i][j]=-1;
                    else copyMap[i][j]=-99; // 나머지는 -99로 표시
                }
            }

            // q에다 현재 선택한 바이러스의 위치를 넣어줌
            q=new ArrayDeque<>();
            for (int i = 0; i < path.length; i++) {
                copyMap[path[i].y][path[i].x]=0;
                q.offer(new Node(path[i].y,path[i].x,0));
            }

            BFS(copyMap);

            return;
        }

        for (int i = start; i < list.size(); i++) {
            path[level]=list.get(i);
            recursive(level+1,i+1);
        }
    }

    public static void BFS(int[][] copyMap){
        while (!q.isEmpty()){
            Node n=q.poll();

            for (int d = 0; d <4; d++) {
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];

                if(0<=ny && 0<=nx && ny<N && nx<N && copyMap[ny][nx]==-99){
                    copyMap[ny][nx]=n.cnt+1;
                    q.offer(new Node(ny,nx,n.cnt+1));
                }
            }
        }


        int time=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <N; j++) {
                if(copyMap[i][j]==-99)return;
                time=Math.max(copyMap[i][j],time);
            }
        }

        answer=Math.min(answer,time);
    }
}
