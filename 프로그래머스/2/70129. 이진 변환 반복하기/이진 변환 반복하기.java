import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        String str=s;
        int cnt=0;
        int zeroCnt=0;
        
        while(true){
            if(str.equals("1"))break;
            
            String[] strArr=str.split("");
            
            String tmpStr="";
            cnt+=1;
            
            for(int i=0;i<strArr.length;i++){
                if(strArr[i].equals("1"))tmpStr+="1";
                else zeroCnt+=1;
            }
            
            str=Integer.toBinaryString(tmpStr.length());
        }
        
        answer[0]=cnt;
        answer[1]=zeroCnt;
        
        return answer;
    }
}