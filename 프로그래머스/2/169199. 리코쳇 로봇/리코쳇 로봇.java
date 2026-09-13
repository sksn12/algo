import java.util.*;

class Solution {
    static int Y,X;
    static boolean[][] v;
    static int[][] map;
    static Queue<Node> q=new ArrayDeque<>();
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    
    static class Node{
        int y,x,cnt;
        
        Node(int y,int x,int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }
    }
    
    public int solution(String[] board) {
        Y=board.length;
        X=board[0].split("").length;
        
        map=new int[Y][X];
        v=new boolean[Y][X];
        
        for(int i=0;i<Y;i++){
            String[] str=board[i].split("");
            
            for(int j=0;j<X;j++){
                if(str[j].equals("."))map[i][j]=0; // 갈수있음
                else if(str[j].equals("D"))map[i][j]=1; // 못감
                else if(str[j].equals("G"))map[i][j]=2; // 도착지점
                else if(str[j].equals("R")){
                    map[i][j]=0; // 갈수있음 && 시작지점이라 queue에 넣기
                    q.offer(new Node(i,j,0));
                    v[i][j]=true;
                }
            }
        }

        return BFS();
    }
    
    public static int BFS(){
        while(!q.isEmpty()){
            Node n=q.poll();
            if(map[n.y][n.x]==2) return n.cnt;
            
            for(int d=0;d<4;d++){
                int ny=n.y;
                int nx=n.x;
                
                // 가려는 방향으로 끝까지 가야함
                while(true){
                    int nextY=ny+dy[d];
                    int nextX=nx+dx[d];

                    if(nextY<0 || nextX<0 || nextY>=Y || nextX>=X || map[nextY][nextX]==1) break;
                    
                    ny=nextY;
                    nx=nextX;
                }
                
                if(!v[ny][nx]){
                    v[ny][nx]=true;
                    q.offer(new Node(ny,nx,n.cnt+1));
                }        
            }
            
        }
 
        return -1;
    }
}