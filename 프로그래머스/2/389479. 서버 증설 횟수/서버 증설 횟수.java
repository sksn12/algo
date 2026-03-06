import java.util.*;

class Solution {
    static Queue<Integer> q;
        
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        // q안에 들어가는건 증설한 서버의 남은 시간, q.size() == 서버증설갯수
        q=new LinkedList<>();
        
        for(int player:players){
            int needServer=player/m;
            
            if(needServer>0){
                int plusServer=needServer-q.size();
                
                if(plusServer>0){
                    for(int i=0;i<plusServer;i++){
                        q.offer(k);
                    }
                    answer+=plusServer;
                }
            }
            
            rotateQ();
        }
        
        return answer;
    }
    
    static public void rotateQ(){
        int cnt=q.size();
        
        for(int i=0;i<cnt;i++){
            int refuseTime=q.poll()-1;
            if(refuseTime!=0)q.offer(refuseTime);
        }
    }
}