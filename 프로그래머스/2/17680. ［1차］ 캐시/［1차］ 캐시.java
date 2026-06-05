import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        Queue<String> q=new LinkedList<>();
        
        for(String citie:cities){
            String c=citie.toLowerCase();
            
            if(q.contains(c)){
                q.remove(c);
                q.offer(c);
                answer+=1;
            }else{
                if(q.size()<cacheSize){
                    q.offer(c);
                }
                else if(q.size()>=cacheSize && cacheSize!=0){
                    q.poll();
                    q.offer(c);
                }
                
                answer+=5;
            }
        }
        
        return answer;
    }
}