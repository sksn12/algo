import java.util.*;

class Solution {
    static class MaxNode implements Comparable<MaxNode>{
        int x;
        
        MaxNode(int x){
            this.x=x;
        }
        
        @Override
        public int compareTo(MaxNode n){
            return n.x-this.x;
        }
    }
    
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        PriorityQueue<MaxNode> pq2=new PriorityQueue<>(); // 최댓값 순 정렬
        
        int size=0;
        
        L:for(String s:operations){
            String[] str=s.split(" ");
            // 만약 size가 0이면 2개의 q다 버리기
            if(size==0){
                while(pq.size()!=0){
                    pq.poll();
                } 

                while(pq2.size()!=0){
                    pq2.poll();
                }
            }
            
            if(str[0].equals("I")){
                size+=1;
                pq.offer(Integer.parseInt(str[1]));
                pq2.offer(new MaxNode(Integer.parseInt(str[1])));
            }else{
                size-=1;
                if(size<0){
                    size=0;
                    continue L;
                }
                
                if(str[1].equals("-1")){
                    pq.poll();
                }else{
                    pq2.poll();
                }
            }
        }
        
        if(size==0)answer[0]=0;
        else answer[0]=pq2.poll().x;
        
        if(size==0)answer[1]=0;
        else answer[1]=pq.poll();
        
        return answer;
    }
}