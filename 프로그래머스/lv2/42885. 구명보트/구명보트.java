import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        boolean[] v=new boolean[people.length];
        
        Arrays.sort(people);
        boolean val=false;
        int tmp=0;
        
        
        int size = people.length - 1;
        for(int i=0;i<people.length;i++){
            if(val)break;
            
            int now=people[i];
            v[i]=true;

            int nearIndex=0;
            
            // 짝이 없는 순간이 한번이라도 나오면 그 뒤에는 짝 검사 할필요가 없음 (오름차순이라)
            for(int j=size;j>=i + 1;j--){
                // people이 오름차순 정렬이 되어 있어 뒤로 갔을때 조건을 만족하면 현재 값이랑 짝이 될수 있는 가장 큰수!
                if(now + people[j] <= limit && !v[j]){
                    nearIndex=j;
                    size = nearIndex - 1;
                    break;
                }
            } 
            
            v[nearIndex]=true;
            if(nearIndex==0){
                val=true;
            }else{
                tmp+=1;
            }
        }
        
        answer=people.length-tmp;
        
        return answer;
    }
}