package 백준;

import java.io.*;
import java.util.*;

public class 말이되고픈원숭이 {
    static int Y,X;
    static class Node{
        int y,x,cnt,refuse;

        Node(int y,int x,int cnt,int refuse){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
            this.refuse=refuse;
        }
    }
    static int[] dy={0,0,-1,1};
    static int[] dx={1,-1,0,0};
    static int[] dy2={-2,-1,1,2,2,1,-1,-2};
    static int[] dx2={1,2,2,1,-1,-2,-2,-1};
    static Queue<Node> q=new LinkedList<>();
    static Node[][] v;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new int[Y][X];
        v=new Node[Y][X];

        for(int i=0;i<Y;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<X;j++){
                map[i][j]=Integer.parseInt(st.nextToken());

                v[i][j]=new Node(i,j,0,0);
            }
        }

        q.offer(new Node(0,0,0,K));

        System.out.println(BFS());
    }

    public static int BFS(){
        while(!q.isEmpty()){
            Node n=q.poll();

            for(int d=0;d<4;d++){
                int ny=dy[d]+n.y;
                int nx=dx[d]+n.x;

                if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx]==0){
                    if(ny==Y-1 && nx==X-1){
                        return n.cnt+1;
                    }

                    // 현재 오는 원숭이가 말처럼 움직일수있는 횟수가 기존보다 높은 경우는 무조건 갈수있음
                    if(n.refuse > v[ny][nx].refuse){
                        q.offer(new Node(ny,nx,n.cnt+1,n.refuse));
                        v[ny][nx]=new Node(ny,nx,n.cnt+1,n.refuse);
                    }

                    // 현재 오는 원숭이가 말처럼 움직일수있는 횟수가 기존보다 적다면 무조건 적게 움직였어야만 통과
                    else if(n.refuse <= v[ny][nx].refuse && n.cnt+1<v[ny][nx].cnt ){
                        q.offer(new Node(ny,nx,n.cnt+1,n.refuse));
                        v[ny][nx]=new Node(ny,nx,n.cnt+1,n.refuse);
                    }
                }
            }

            // 말처럼 움직이기
            for(int d=0;d<8;d++){
                int ny=dy2[d]+n.y;
                int nx=dx2[d]+n.x;

                if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx]==0){
                    if(ny==Y-1 && nx==X-1){
                        return n.cnt+1;
                    }

                    // 현재 오는 원숭이가 말처럼 움직일수있는 횟수가 기존보다 높은 경우는 무조건 갈수있음
                    if(n.refuse-1 > v[ny][nx].refuse){
                        q.offer(new Node(ny,nx,n.cnt+1,n.refuse-1));
                        v[ny][nx]=new Node(ny,nx,n.cnt+1,n.refuse-1);
                    }

                    // 현재 오는 원숭이가 말처럼 움직일수있는 횟수가 기존보다 적다면 무조건 적게 움직였어야만 통과
                    else if(n.refuse-1 <= v[ny][nx].refuse && n.cnt+1 < v[ny][nx].cnt ){
                        q.offer(new Node(ny,nx,n.cnt+1,n.refuse-1));
                        v[ny][nx]=new Node(ny,nx,n.cnt+1,n.refuse-1);
                    }
                }
            }
        }

        return -1;
    }
}
