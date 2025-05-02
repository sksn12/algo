import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        
        for(int i=0;i<scoville.length;i++){
            pq.offer(scoville[i]);
        }
        
        while(true){
            if(pq.size()<2){
                int low=pq.poll();
                
                if(low>=K)return answer;
                else return -1;
            }
            
            int Low=pq.poll();
            
            if(Low>=K)return answer;
            else{
                int second=pq.poll();
                answer+=1;
                pq.offer(Low+(second*2));
            }
        }
        
    }
}