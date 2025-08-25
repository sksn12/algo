import java.util.*;

class Solution {
    static boolean[] v;
    static int answer=0,t;
    static int[] n;
    
    public int solution(int[] numbers, int target) {
        
        v=new boolean[numbers.length];
        n=new int[numbers.length];
        t=target;
        
        for(int i=0;i<numbers.length;i++){
            n[i]=numbers[i];
        }
        
        recursive(0);
        
        return answer;
    }
    
    public void recursive(int level){
        if(level==v.length){
            int sum=0;
            for(int i=0;i<v.length;i++){
                if(v[i])sum+=n[i];
                else sum-=n[i];
            }
            
            if(sum==t)answer+=1;
            return;
        }
        
        v[level]=true;
        recursive(level+1);
        v[level]=false;
        recursive(level+1);
    }
}