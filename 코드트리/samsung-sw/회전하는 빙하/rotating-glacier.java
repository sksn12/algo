import java.util.*;
import java.io.*;

public class Main {
    static int N,Q;
    static int[][] map;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static Queue<Node> q=new ArrayDeque<>();
    static boolean[][] v;
    static int answer=0;
    static class Node{
        int y,x;

        Node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=(int) Math.pow(2,Integer.parseInt(st.nextToken()));
        Q=Integer.parseInt(st.nextToken());

        map=new int[N][N];
        v=new boolean[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        st=new StringTokenizer(br.readLine());

        for(int i=0;i<Q;i++){
            int level=Integer.parseInt(st.nextToken());

            if(level>0){
                rotate(level);
            }

            melt();
        }
        
        sum();
        max_size();

        System.out.println(answer);

    }

    public static void rotate(int level) {
        if (level == 0) {
            return;
        }

        int size = (int) Math.pow(2,level);
        int half = size / 2;

        int[][] tmp = new int[N][N];

        for (int startY = 0; startY < N; startY += size) {
            for (int startX = 0; startX < N; startX += size) {

                for (int y = 0; y < half; y++) {
                    for (int x = 0; x < half; x++) {

                        // 왼쪽 아래(3) → 왼쪽 위(1)
                        tmp[startY + y][startX + x]
                                = map[startY + half + y][startX + x];

                        // 왼쪽 위(1) → 오른쪽 위(2)
                        tmp[startY + y][startX + half + x]
                                = map[startY + y][startX + x];

                        // 오른쪽 아래(4) → 왼쪽 아래(3)
                        tmp[startY + half + y][startX + x]
                                = map[startY + half + y][startX + half + x];

                        // 오른쪽 위(2) → 오른쪽 아래(4)
                        tmp[startY + half + y][startX + half + x]
                                = map[startY + y][startX + half + x];
                    }
                }
            }
        }

        map = tmp;
    }
    public static void melt() {
        boolean[][] melt = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] <= 0) {
                    continue;
                }

                int cnt = 0;

                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];

                    if (0 <= ny && ny < N &&
                        0 <= nx && nx < N &&
                        map[ny][nx] > 0) {
                        cnt++;
                    }
                }

                if (cnt < 3) {
                    melt[i][j] = true;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (melt[i][j]) {
                    map[i][j]--;
                }
            }
        }
    }

    public static void sum(){
        int sum=0;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sum+=map[i][j];
            }
        }

        System.out.println(sum);
    }

    public static void max_size(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]>0 && !v[i][j]){
                    q.offer(new Node(i,j));
                    v[i][j]=true;
                    BFS();
                }
            }
        }
    }

    public static void BFS(){
        int cnt=1;

        while(!q.isEmpty()){
            Node n=q.poll();

            for(int d=0;d<4;d++){
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];

                if(0<=ny && 0<=nx && ny<N && nx<N && !v[ny][nx] && map[ny][nx]>0){
                    q.offer(new Node(ny,nx));
                    v[ny][nx]=true;
                    cnt+=1;
                }
            }
        }

        answer=Math.max(answer,cnt);
    }


}