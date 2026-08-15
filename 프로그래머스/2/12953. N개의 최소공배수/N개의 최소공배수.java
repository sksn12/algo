import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        
        Arrays.sort(arr);
        
        int maxIndex=arr[arr.length-1];
        int p1=1;
        
        while(true){
            boolean val=false;
            int max=maxIndex*p1;
            
            for(int i=0;i<arr.length;i++){
                if(max%arr[i]!=0)val=true;
            }
            
            if(!val){
                answer=max;
                break;
            }
            
            p1+=1;
        }
        
        return answer;
    }
}