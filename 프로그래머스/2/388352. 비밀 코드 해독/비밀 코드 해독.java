import java.util.*;

class Solution {
    static int[] sel;
    static int N;
    static int[][] q2;
    static int[] ans2;
    static int[] arr;
    static int answer=0;
    
    public int solution(int n, int[][] q, int[] ans) {
        N=n;
        q2=q;
        ans2=ans;
        sel=new int[5];
        arr=new int[N];
        
        for(int i=0;i<N;i++) arr[i]=i+1;
        
        recursive(0,0); 
        
        return answer;
    }
    
    public static void recursive(int start,int level){
        if(level==sel.length){
            
            for(int i=0;i<q2.length;i++){
                int cnt=0;
                
                for(int j=0;j<5;j++){
                    int now=q2[i][j];
                    
                    for(int z=0;z<5;z++){
                        if(now==sel[z])cnt+=1;
                    }
                }
                
                if(cnt!=ans2[i])return;
            }
            
            answer+=1;
            return;
        }
        
        for(int i=start;i<N;i++){
            sel[level]=arr[i];
            recursive(i+1,level+1);
        }
    }
}
