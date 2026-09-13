import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
        int dia;
        int iron;
        int stone;
        
        Node(int dia,int iron,int stone){
            this.dia=dia;
            this.iron=iron;
            this.stone=stone;
        }
        
        @Override
        public int compareTo(Node n){
            if(n.dia==this.dia){
                if(n.iron==this.iron)return n.stone-this.stone;
                else return n.iron-this.iron;
            }else return n.dia-this.dia;
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int total_pick=0;
        PriorityQueue<Node> pq=new PriorityQueue<>();
        
        for(int i:picks)total_pick+=i;
        
        int max_recursive=total_pick*5;
        max_recursive=Math.min(max_recursive,minerals.length);
        
        int dia=0;
        int iron=0;
        int stone=0;
        for(int i=0;i<max_recursive;i++){
            if(i%5==0){
                pq.offer(new Node(dia,iron,stone));
                
                dia=0;
                iron=0;
                stone=0;
            }
            
            if(minerals[i].equals("diamond"))dia+=1;
            else if(minerals[i].equals("iron"))iron+=1;
            else if(minerals[i].equals("stone"))stone+=1;
        }
        
        if(dia!=0 || iron!=0 || stone!=0)pq.offer(new Node(dia,iron,stone));
        
        while(!pq.isEmpty()){
            Node n=pq.poll();
            
            for(int i=0;i<picks.length;i++){
                if(picks[i]>0){
                    if(i==0)answer+=n.dia+n.iron+n.stone;
                    else if(i==1)answer+=(5*n.dia)+n.iron+n.stone;
                    else if(i==2)answer+=(25*n.dia)+(5*n.iron)+n.stone;
                    
                    picks[i]-=1;
                    break;
                }
            }
        }
        
        return answer;
    }
}



