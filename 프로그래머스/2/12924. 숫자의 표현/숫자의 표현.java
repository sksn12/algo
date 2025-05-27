import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 1;
        
        if(n==1)return answer;
        
        int half=(int) Math.ceil(n/2.0);
        
        int[] number=new int[half];
        
        for(int i=1;i<=half;i++){
            number[i-1]=i;
        }
        
        int p1=0;
        int p2=0;
        int sum=0;
        
        while(p2<half){
            sum+=number[p2];
            
            while(sum>n){
                sum-=number[p1++];
            }
            
            if(sum==n)answer+=1;
            
            p2+=1;
        }
        
        return answer;
    }
}