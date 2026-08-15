import java.util.*;

class Solution {
    public int solution(String word) {
        int answer = 0;
        String[] arr={"A","E","I","O","U"};
        
        StringBuilder sb=new StringBuilder();
        while(true){
            if(word.equals(sb.toString()))break;
    
            if(sb.length()<5)sb.append("A");
            // 가장 마지막 글자가 U라면 U를 전부 삭제하고 제일 뒷자리만 한단계 올리기
            else if(sb.substring(sb.length()-1).equals("U")){ 
                while(true){
                    if(sb.substring(sb.length()-1).equals("U"))sb.setLength(sb.length()-1);
                    else break;
                }
                
                for(int i=0;i<arr.length-1;i++){
                    if(sb.substring(sb.length()-1).equals(arr[i])){
                        sb.setLength(sb.length()-1);
                        sb.append(arr[i+1]);
                        break;
                    }
                }  
            }
            // 가장 마지막 글자가 U가 아니라면 마지막 글자 지우고 다음 글자를 찾아서 삽입
            else{
                for(int i=0;i<arr.length-1;i++){
                    if(sb.substring(sb.length()-1).equals(arr[i])){
                        sb.setLength(sb.length()-1);
                        sb.append(arr[i+1]);
                        break;
                    }
                }    
            }
            
            answer+=1;
        }
        
        return answer;
    }
}