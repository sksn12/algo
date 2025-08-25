import java.util.*;

class Solution {
    public int[] solution(int k, int[] scores) {        
        List<Integer> list=new ArrayList<>(); 
        PriorityQueue<Integer> pq=new PriorityQueue<>(); 
        
        for(int score:scores){
            if(pq.size()<k){ 
                pq.offer(score); 
                int min=pq.poll();  
                list.add(min); 
                pq.offer(min); 
            }else{ 
                int min=pq.poll(); 
                
                if(min<score){
                    pq.offer(score);
                    
                    int min2=pq.poll(); 
                    list.add(min2);
                    pq.offer(min2);
                }else{
                    list.add(min);
                    pq.offer(min);
                }
            }
        }
        
        int[] answer = new int[list.size()];
        
        for(int i=0;i<list.size();i++){
            answer[i]=list.get(i);
        }
        
        return answer;
    }
}