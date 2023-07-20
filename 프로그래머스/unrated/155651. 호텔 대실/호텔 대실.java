import java.util.*;

class Solution {
    static int[][] time;
    static int Y;
    
    public int solution(String[][] book_time) {
        
        Y=book_time.length;
        time=new int[Y][2];
        
        Change(book_time);
        
        Arrays.sort(time,(a,b)->{
            // 입실시간이 같은경우
            if(a[0]==b[0]){
                return a[1]-b[1]; // 퇴실이 빠른 순부터
            }else return a[0]-b[0];
        });
            
        
        // 퇴실시간만 q에 저장해 놓을 것임으로 우선순위 q를 이용하여 퇴실시간이 빠른 순으로 정렬!
        PriorityQueue<Integer> q=new PriorityQueue<>();
        
        for(int i=0;i<Y;i++){
            if(q.size()==0){
                q.offer(time[i][1]);
                continue;
            }
            
            // pq때문에 peek된 값은 현재 q에서 가장 퇴실시간이 빠른값
            int tmp=q.peek();
            // 현재 입실시간이 이전사람의 퇴실시간보다 크다면 입실가능!
            if(time[i][0]>=tmp){
                // 퇴실시간 초기화를 위해 q poll() & 새로운 값 offer
                q.poll();
                q.offer(time[i][1]);
            }else{
                q.offer(time[i][1]);
            }
        }
        
        
        return q.size();
    }
    
    // 문자열 시간 int형으로 변환
    static void Change(String[][] book_time){
        for(int i =0;i<Y;i++){
            int startTime = Integer.parseInt(book_time[i][0].replace(":",""));
            int endTime = Integer.parseInt(book_time[i][1].replace(":",""));
            
            endTime += 10;
            if(endTime%100 >= 60){
                endTime+=40;
            }
            time[i][0] = startTime;
            time[i][1] = endTime;
        }
        

    }
}