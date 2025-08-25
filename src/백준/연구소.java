package 백준;

import java.io.*;
import java.util.*;

public class 연구소 {
    static List<Node> list=new ArrayList<>();
    static Node[] path=new Node[3];
    static class Node{
        int y,x;

        Node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
    static int Y,X,answer=0;
    static int[][] map;
    static int[][] copyMap;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new int[Y][X];

        for(int i=0;i<Y;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<X;j++){
                int tmp=Integer.parseInt(st.nextToken());

                map[i][j]=tmp;
                if(tmp==0)list.add(new Node(i,j));
            }
        }

        recursive(0,0);

        System.out.println(answer);
    }

    // 1. 3개의 벽을 세울 수 있는 모든 위치 경우의 수를 만듬 (조합으로)
    public static void recursive(int level,int start){
        if(level==path.length){
            copyMap=new int[Y][X];

            for(int i=0;i<Y;i++){
                for(int j=0;j<X;j++){
                    copyMap[i][j]=map[i][j];
                }
            }

            // 1-1. 현재 위치에 벽을 세움
            for(int i=0;i<path.length;i++){
                copyMap[path[i].y][path[i].x]=1;
            }

            BFS();
            return;
        }

        for(int i=start;i<list.size();i++){
            path[level]=list.get(i);
            recursive(level+1,i+1);
        }
    }

    // 2. 바이러스
    public static void BFS(){
        Queue<Node> q=new LinkedList<>();

        // 2-1. BFS 탐색 전 초기 셋팅
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                if(map[i][j]==2){
                    q.offer(new Node(i,j));
                }
            }
        }

        while(!q.isEmpty()){
            Node n=q.poll();

            for(int d=0;d<4;d++){
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];

                if(0<=ny && 0<=nx && ny<Y && nx<X && copyMap[ny][nx]==0){
                    copyMap[ny][nx]=2;
                    q.offer(new Node(ny,nx));
                }
            }
        }

        // 2-2. 바이러스에 감영되지 않은 영역 갯수 세기
        int sum=0;
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                if(copyMap[i][j]==0){
                    sum+=1;
                }
            }
        }
        answer=Math.max(sum,answer);
    }
}
