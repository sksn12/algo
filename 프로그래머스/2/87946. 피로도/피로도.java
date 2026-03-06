import java.util.*;

class Solution {
    static int[][] d;
    static int[] path;
    static boolean[] v;
    static int answer=0;
    
    public int solution(int k, int[][] dungeons) {
        
        d=dungeons;
        path=new int[dungeons.length];
        v=new boolean[dungeons.length];
        
        recursive(0,k);
        
        return answer;
    }
    
    public static void recursive(int level,int k){
        if(level==d.length){
            int cnt=0;
            
            for(int i=0;i<path.length;i++){
                int minDungeon=d[path[i]][0];
                int spenDungeon=d[path[i]][1];
                
                if(k>=minDungeon){
                    k-=spenDungeon;
                    cnt+=1;
                }
            }
            
            answer=Math.max(cnt,answer);
            
            return;
        }
        
        for(int i=0;i<d.length;i++){
            if(!v[i]){
               v[i]=true;
               path[level]=i;
               recursive(level+1,k);
               v[i]=false;
            }
            
        }
    }
}