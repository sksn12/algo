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

    static String[][] map;
    static Queue<Node> q;
    static boolean[][] v;
    static int[] answer=new int[5];

    static int Y=5;
    static int X=5;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    
    public int[] solution(String[][] places) {
        L:for(int i=0;i<5;i++){
            map=new String[5][5];
            
            // 각 p마다 독립적으로 실행하기 위해 우선 map에 값만 넣어줌
            for(int j=0;j<5;j++){
                String[] str=places[i][j].split("");
                for(int z=0;z<5;z++)map[j][z]=str[z];
            }
            
            for(int j=0;j<5;j++){
                for(int z=0;z<5;z++){
                    if(map[j][z].equals("P")){
                        q=new ArrayDeque<>();
                        v=new boolean[5][5];
                        v[j][z]=true;
                        q.offer(new Node(j,z,0));
                        
                        // 한명이라도 거리두기를 지키지 않는다면 0넣고 다음으로
                        if(BFS(i)){
                            answer[i]=0;
                            continue L;
                        }
                    }
                }
            }
            
            answer[i]=1;
        }
        
        return answer;
    }
    
    public static boolean BFS(int i){
        while(!q.isEmpty()){
            Node n=q.poll();
            
            if(n.cnt>=2)return false;
            
            for(int d=0;d<4;d++){
                int ny=dy[d]+n.y;
                int nx=dx[d]+n.x;
                
                if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx] && !map[ny][nx].equals("X")){
                    if(map[ny][nx].equals("P"))return true;
                    
                    q.offer(new Node(ny,nx,n.cnt+1));
                    v[ny][nx]=true;
                }
            }
        }
        return false;
    }
}