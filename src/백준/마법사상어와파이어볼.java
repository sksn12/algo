package 백준;

import java.io.*;
import java.util.*;

public class 마법사상어와파이어볼 {
    static int N,M,K;

    static class Node{
        int w;
        int d;
        int s;
        int y;
        int x;

        Node(int w,int d,int s){
            this.w=w;
            this.d=d;
            this.s=s;
        }

        Node(int w,int d,int s,int y,int x){
            this.w=w;
            this.d=d;
            this.s=s;
            this.y=y;
            this.x=x;
        }
    }

    static Queue<Node>[][] map;
    static int[] dy={-1,-1,0,1,1,1,0,-1};
    static int[] dx={0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        map=new Queue[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j]=new ArrayDeque<>();
            }
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());

            int y=Integer.parseInt(st.nextToken())-1;
            int x=Integer.parseInt(st.nextToken())-1;
            int w=Integer.parseInt(st.nextToken());
            int s=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());

            map[y][x].add(new Node(w,d,s));
        }

        for(int t=0;t<K;t++){
            move();
            cal();
        }

        int answer=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int size=map[i][j].size();

                if(size>0){
                    for(int z=0;z<size;z++){
                        Node n=map[i][j].poll();
                        answer+=n.w;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static void move(){
        Queue<Node> q=new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int size=map[i][j].size();

                if(size>0){
                    // 해당 위치에 있는 불의 갯수들을 한번에 모두 이동 (큐를 안쓰면 이동한 값이 또 이동함)
                    for(int z=0;z<size;z++){
                        Node n=map[i][j].poll();
                        q.offer(new Node(n.w,n.d,n.s,i,j));
                    }
                }
            }
        }

        while(!q.isEmpty()){
            Node n=q.poll();

            int ny=n.y;
            int nx=n.x;

            // 속력만큼 이동
            for(int c=0;c<n.s;c++){
                ny+=dy[n.d];
                nx+=dx[n.d];

                if(ny>=N)ny=0;
                else if(ny<0)ny=N-1;

                if(nx>=N)nx=0;
                else if(nx<0)nx=N-1;
            }

            map[ny][nx].add(new Node(n.w,n.d,n.s));
        }
    }

    // 한 위치에 2개 이상의 불이 있으면 계산하기
    public static void cal(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int size=map[i][j].size();

                if(size>1){
                    int total_weight=0;
                    int total_speed=0;
                    int total_fire_cnt=0;
                    List<Integer> total_dir=new ArrayList<>();

                    for(int c=0;c<size;c++){
                        Node n=map[i][j].poll();

                        total_weight+=n.w;
                        total_speed+=n.s;
                        total_fire_cnt+=1;
                        total_dir.add(n.d);
                    }

                    int cal_w=total_weight/5;
                    int cal_s=total_speed/total_fire_cnt;

                    if(cal_w>0) {
                        boolean allEven = true;
                        boolean allOdd = true;
                        for (int dir : total_dir) {
                            if (dir % 2 == 0) allOdd = false;
                            else allEven = false;
                        }
                        if (allEven || allOdd) {
                            // 0 2 4 6
                            map[i][j].add(new Node(cal_w, 0, cal_s));
                            map[i][j].add(new Node(cal_w, 2, cal_s));
                            map[i][j].add(new Node(cal_w, 4, cal_s));
                            map[i][j].add(new Node(cal_w, 6, cal_s));
                        } else {
                            // 1 3 5 7
                            map[i][j].add(new Node(cal_w, 1, cal_s));
                            map[i][j].add(new Node(cal_w, 3, cal_s));
                            map[i][j].add(new Node(cal_w, 5, cal_s));
                            map[i][j].add(new Node(cal_w, 7, cal_s));
                        }
                    }
                }
            }
        }
    }
}
