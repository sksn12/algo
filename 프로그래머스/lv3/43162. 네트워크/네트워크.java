import java.util.*;

class Solution {
    static Queue<int[]> q=new LinkedList<>();
    static boolean[] v;
    static int answer=0;
    
    public int solution(int n, int[][] computers) {
        v=new boolean[n];
        
        for(int i=0;i<n;i++){
            if(!v[i])answer+=1;
           
            for(int j=0;j<n;j++){
                if(i==j)continue;
                
                if(!v[j] && computers[i][j]==1){
                   q.offer(new int[] {i,j});
                   v[j]=true;
                   BFS(n,computers); 
                }
            }
        }
        
        return answer;
    }
    
    public void BFS(int n, int[][] computers){
        while(!q.isEmpty()){
            int[] yx=q.poll();
            int y=yx[0]; // 0
            int x=yx[1];  // 1
            
            for(int i=0;i<n;i++){
                if(!v[i] && computers[x][i]==1){
                    v[i]=true;
                    q.offer(new int[] {x,i});
                }
            }
        }
    }
}