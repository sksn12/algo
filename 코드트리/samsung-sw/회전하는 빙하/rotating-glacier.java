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

    public static void rotate(int level){
        int max=(int) Math.pow(2,level);
        int[][] tmp=new int[N][N];

        for(int y=0;y<N;y+=max){
            for(int x=0;x<N;x+=max){
                int half=(int) Math.pow(2,level-1);

                int cnt=0;
                for(int ry=y;ry<y+max;ry+=half){
                    for(int rx=x;rx<x+max;rx+=half){
                        cnt+=1;

                        // 1사분면 -> 2사분면으로
                        if(cnt==1){
                            for(int sy=ry;sy<ry+half;sy++){
                                for(int sx=rx;sx<rx+half;sx++){
                                    tmp[sy][sx+half]=map[sy][sx];
                                }
                            }
                        }
                        // 2사분면 -> 3사분면으로
                        else if(cnt==2){
                            for(int sy=ry;sy<ry+half;sy++){
                                for(int sx=rx;sx<rx+half;sx++){
                                    tmp[sy+half][sx]=map[sy][sx];
                                }
                            }
                        }
                        // 4사분면 -> 1사분면으로
                        else if(cnt==3){
                            for(int sy=ry;sy<ry+half;sy++){
                                for(int sx=rx;sx<rx+half;sx++){
                                    tmp[sy-half][sx]=map[sy][sx];
                                }
                            }
                        }
                        // 3사분면 -> 4사분면으로
                        else{
                            for(int sy=ry;sy<ry+half;sy++){
                                for(int sx=rx;sx<rx+half;sx++){
                                    tmp[sy][sx-half]=map[sy][sx];
                                }
                            }
                        }
                    }
                }
            }
        }

        map=tmp;
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