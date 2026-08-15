import java.util.*;

class Solution {
    static int[] arr;
    static int[] sel=new int[3];
    static int answer=0;
    
    public int solution(int[] number) {
        arr=number;
        recursive(0,0);
        return answer;
    }
    
    public static void recursive(int start,int level){
        if(level==sel.length){
            int sum=0;
            
            for(int i=0;i<sel.length;i++){
                sum+=sel[i];
            }
            
            if(sum==0)answer+=1;
            return;
        }
        
        for(int i=start;i<arr.length;i++){
            sel[level]=arr[i];
            recursive(i+1,level+1);
        }
    }
}