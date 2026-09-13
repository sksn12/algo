package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 로봇청소기 {
    static class Node{
        int y,x,d;

        Node(int y,int x,int d){
            this.y=y;
            this.x=x;
            this.d=d;
        }
    }

    static int Y,X;
    static int[][] map;
    static int answer=0;
    static int[] dy={-1,0,1,0}; // 북 동 남 서
    static int[] dx={0,1,0,-1};
    static Queue<Node> q=new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new int[Y][X];

        st=new StringTokenizer(br.readLine());

        int sy=Integer.parseInt(st.nextToken());
        int sx=Integer.parseInt(st.nextToken());
        int sd=Integer.parseInt(st.nextToken());

        q.offer(new Node(sy,sx,sd));

        for (int i = 0; i < Y; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        BFS();

    }

    private static void BFS() {
        while (!q.isEmpty()){
            Node n=q.poll();

            if(map[n.y][n.x]==0 ){
                answer+=1;
            }

            boolean val=false; // 4방 탐색을 통해 청소할 수 있는 위치가 있는지 확인
            for (int d = 0; d < 4; d++) {
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];

                if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx]==0){
                    val=true;
                }
            }

            // 청소할 수 있는 위치가 있다!
            if(val){
                int nd=n.d; // 큐에서 가지고 있던 현재 방향

                if(nd==3)nd=0;
                else nd+=1;

                int ny=n.y+dy[nd];
                int nx=n.x+dx[nd];

                // 반시계 방향으로 90도 회전한 후 그 방향으로 전진했을때 청소가 가능하면 전진, 안되면 방향만 바꾼 값을 q에 넣기
                if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx]==0){
                    q.offer(new Node(ny,nx,nd));
                }else q.offer(new Node(n.y,n.x,nd));
            }else{ // 후진
                int nd=0;
                if(n.d==2)nd=0;
                else if(n.d==0)nd=2;
                else if(n.d==1)nd=3;
                else if(n.d==3)nd=1;

                int ny=n.y+dy[nd];
                int nx=n.x+dx[nd];

                if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx]==0){
                    q.offer(new Node(ny,nx,n.d));
                }else{
                    System.out.println(answer);
                    return;
                }
            }
        }
    }
}
