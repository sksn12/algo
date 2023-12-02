import java.util.*;

class Solution {
    
    static class node{
        int y;
        int x;

        node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
    
    static Queue<node> q=new LinkedList<>();
    static List<Integer> list=new ArrayList<>();
    static char[][] map;
    static boolean[][] v;
    static int sum;
    static int[] dy={-1,0,1,0};    
    static int[] dx={0,1,0,-1};   
    static int Y,X;
    
    public int[] solution(String[] maps) {
        
        Y=maps.length;
        X=maps[0].length();
        
        v=new boolean[Y][X];
        map=new char[Y][X];
        
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                map[i][j]=maps[i].charAt(j);
            }
        }
        
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                if(maps[i].charAt(j)!='X' && !v[i][j]){
                    sum=0; 
                    q.offer(new node(i,j));
                    BFS(); 
                    list.add(sum);
                }
            }
        }
        
        if(list.size()==0){
            int[] answer=new int[1];
            answer[0]=-1;
            return answer;
        }
        
        Collections.sort(list);
        
        int[] answer = new int[list.size()];
    
        for(int i=0;i<list.size();i++){
            answer[i]=list.get(i);
        }
        
        
        return answer;
        
    }
    
    static void BFS(){
        while(!q.isEmpty()){
            node n=q.poll();
            sum+=map[n.y][n.x]-'0';
            
            v[n.y][n.x]=true;
            
            for(int d=0;d<4;d++){
                int ny=dy[d]+n.y;
                int nx=dx[d]+n.x;
                
                if(0<=ny && 0<= nx && ny<Y && nx<X && !v[ny][nx] && map[ny][nx]!='X'){
                    v[ny][nx]=true;
                    q.offer(new node(ny,nx));
                } 
            }
        }
    }
}

