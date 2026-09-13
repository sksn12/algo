import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        String str2=Integer.toString(storey);
        String[] str=str2.split("");
        
        for(int i=str.length-1;0<=i;i--){
            int tmp=Integer.parseInt(str[i]);
            
            if(tmp>5){
                answer+=10-tmp;
                if(i>0) str[i-1]=Integer.toString(Integer.parseInt(str[i-1])+1);
                else  answer += 1; // 가장 앞자리에서 올림 발생
            }else if(tmp==5){
               if(i>0){
                   int next=Integer.parseInt(str[i-1]);
                   if(next>=5)str[i-1]=Integer.toString(next+1);
                   answer+=5;
               }else answer+=5;
            }else{
                answer+=tmp;
            }
        }
        
        
        return answer;
    }
}