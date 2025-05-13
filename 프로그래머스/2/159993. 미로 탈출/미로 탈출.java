import java.util.*;

class Solution {
    static class Node{
        int y;
        int x;
        int cnt;
        
        Node(int y,int x,int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }
    }
    
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static Queue<Node> LQ=new LinkedList<>();
    static boolean[][] LV;
    static Queue<Node> EQ=new LinkedList<>();
    static boolean[][] EV;
    static String[][] map;
    static int Y,X;
    static int answer=Integer.MAX_VALUE;
    
    public int solution(String[] maps) {
        
        Y=maps.length;
        X=maps[0].length();
        map=new String[Y][X];
        LV=new boolean[Y][X];
        EV=new boolean[Y][X];
        
        for(int i=0;i<Y;i++){
            String[] str=maps[i].split("");
            for(int j=0;j<X;j++){
                map[i][j]=str[j];
                if(str[j].equals("S")){
                    LQ.offer(new Node(i,j,0));
                    LV[i][j]=true;
                }
            }
        }
        
        LBFS();
        if(answer==Integer.MAX_VALUE)return -1;
        return answer;
    }
    
    public static void LBFS(){
        while(!LQ.isEmpty()){
            Node node=LQ.poll();
            
            for(int d=0;d<4;d++){
                int ny=node.y+dy[d];
                int nx=node.x+dx[d];
                
                if(0<=ny && 0<=nx && ny<Y && nx<X && !LV[ny][nx] && !map[ny][nx].equals("X")){
                    if(map[ny][nx].equals("L")){
                        EV[ny][nx]=true;
                        EQ.offer(new Node(ny,nx,node.cnt+1));
                        EBFS();
                        return;
                    }else{
                        LV[ny][nx]=true;
                        LQ.offer(new Node(ny,nx,node.cnt+1));
                    }
                }
            }
        }
    }
    
     public static void EBFS(){
        while(!EQ.isEmpty()){
            Node node=EQ.poll();
            
            for(int d=0;d<4;d++){
                int ny=node.y+dy[d];
                int nx=node.x+dx[d];
                
                if(0<=ny && 0<=nx && ny<Y && nx<X && !EV[ny][nx] && !map[ny][nx].equals("X")){
                    if(map[ny][nx].equals("E")){
                        answer=Math.min(answer,node.cnt+1);
                        return;
                    }else{
                        EV[ny][nx]=true;
                        EQ.offer(new Node(ny,nx,node.cnt+1));
                    }
                }
            }
        }
    }
}