import java.util.*;

class Solution {
    static class Node{
        int y,x,cnt;
        
        Node(int y,int x,int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }
    }
    
    static int Y,X;
    static int[][] map;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static Queue<Node> q=new ArrayDeque<>();
    static boolean[][] v;
    
    public int solution(int[][] maps) {
        map=maps;
        Y=map.length;
        X=map[0].length;
        v=new boolean[Y][X];
        v[0][0]=true;
        
        q.offer(new Node(0,0,1));
        
        return BFS();
    }
    
    public static int BFS(){
        while(!q.isEmpty()){
            Node n=q.poll();
            
            for(int d=0;d<4;d++){
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];
                
                if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx] && map[ny][nx]==1){
                    // 도착점에 도달한 경우 종료 및 출력
                    if(ny==Y-1 && nx==X-1)return n.cnt+1;
                    
                    v[ny][nx]=true;
                    q.offer(new Node(ny,nx,n.cnt+1));
                }
            }
        }
        
        return -1;
    }
}