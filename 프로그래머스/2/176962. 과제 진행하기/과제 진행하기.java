import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
        String subject;
        int startTime;
        int refuseTime;
        
        Node(String subject,int startTime,int refuseTime){
            this.subject=subject;
            this.startTime=startTime;
            this.refuseTime=refuseTime;
        }
        
        @Override
        public int compareTo(Node n){
            return this.startTime-n.startTime;
        }
    }
    
    static class StackNode{
        String subject;
        int refuseTime;
        
        StackNode(String subject,int refuseTime){
            this.subject=subject;
            this.refuseTime=refuseTime;
        }
    }
    
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        
        Queue<Node> pq=new PriorityQueue<>();
        Stack<StackNode> stack=new Stack<>();
        
        for(String[] plan:plans){
            pq.offer(new Node(plan[0], TimeCal(plan[1]), Integer.parseInt(plan[2])));
        }
        
        int index=0;
        while(pq.size()>1){
            Node n1=pq.poll();
            Node n2=pq.peek();
            
            int refuseTime=(n1.startTime+n1.refuseTime)-n2.startTime;
            
            if(refuseTime>0){
                stack.push(new StackNode(n1.subject,refuseTime));
            }else{
                // 남은시간이 0보다작으면 해당 과목은 끝남
                answer[index++]=n1.subject;
                
                // 남은시간이 마이너스인 만큼 반복적으로 stack에 쌓인값을 제거 
                while(refuseTime<0 && stack.size()>0){
                    StackNode s=stack.pop();
                    int stackRefuseTime=s.refuseTime+refuseTime;
                    refuseTime=stackRefuseTime;

                    if(stackRefuseTime<=0)answer[index++]=s.subject;
                    else stack.push(new StackNode(s.subject,stackRefuseTime));
                }
            }
        }
        
        answer[index++]=pq.poll().subject;
        
        int size=stack.size();
        
        for(int i=0;i<size;i++){
            answer[index++]=stack.pop().subject;
        }
        
        return answer;
    }
    
    public int TimeCal(String time){
        String[] hourAndMinute=time.split(":");
        
        return Integer.parseInt(hourAndMinute[0])*60 +Integer.parseInt(hourAndMinute[1]);
    }
}