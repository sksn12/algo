
import java.io.*;
import java.util.*;

public class Main {

    static int Y,X;
    static int answer=Integer.MAX_VALUE;
    static String[][] map;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static class Node {
        int y,x,cnt;
        String name;

        Node(int y,int x,int cnt,String name){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
            this.name=name;
        }
    }
    static Queue<Node> q=new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new String[Y][X];

        for(int i=0;i<Y;i++){
            String[] str=br.readLine().split("");
            for(int j=0;j<X;j++){
                map[i][j]=str[j];
            }
        }

        // 1. 모든 물의 위치 먼저 큐에 넣기 -> 고슴도치보다 물을 먼저 이동시키기 위해
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                if(map[i][j].equals("*")){
                    q.offer(new Node(i,j,0,"*"));
                }
            }
        }

        // 2.고슴도치 시작점 넣기
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                if(map[i][j].equals("S")){
                    q.offer(new Node(i,j,0,"S"));
                }
            }
        }

        // 3. 고슴도치가 도착점이 도달하는 최단 경로 찾기
        BFS();

        if(answer==Integer.MAX_VALUE){
            System.out.println("KAKTUS");
        }else{
            System.out.println(answer);
        }
    }

    public static void BFS(){
        while(!q.isEmpty()){
            Node n=q.poll();

            for(int d=0;d<4;d++){
                int ny=dy[d]+n.y;
                int nx=dx[d]+n.x;

                if(0<=ny && 0<=nx && ny<Y && nx<X && !map[ny][nx].equals("X")){
                    if(n.name.equals("*")){
                        // 3-1. 현재 큐에서 나온값이 물인경우 고슴도치의 위치와 빈 위치만 이동가능, 물인경우는 이동 x(방문처리)
                        if(map[ny][nx].equals("S") || map[ny][nx].equals(".")){
                            map[ny][nx]="*";
                            q.offer(new Node(ny,nx,n.cnt+1,"*"));
                        }
                    }else if(n.name.equals("S")){
                        // 3-2. 현재 큐에서 나온값이 고슴도치인 경우 도착지에 가거나 빈값만 갈수있음
                        if(map[ny][nx].equals("D")){
                            answer=n.cnt+1;
                            return;
                        }else if(map[ny][nx].equals(".")){
                            map[ny][nx]="S";
                            q.offer(new Node(ny,nx,n.cnt+1,"S"));
                        }
                    }
                }
            }
        }
    }
}
