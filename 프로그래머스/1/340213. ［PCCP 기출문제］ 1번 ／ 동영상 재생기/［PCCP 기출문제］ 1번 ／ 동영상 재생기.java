class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int max_time=secondChange(video_len);
        int start_time=secondChange(pos);
        int os=secondChange(op_start);
        int oe=secondChange(op_end);
        
        if(os<=start_time && start_time<=oe){
            start_time=oe;
        }
        
        for(int i=0;i<commands.length;i++){
            String command=commands[i];
            
            if(command.equals("next"))start_time+=10;
            else start_time-=10;
            
            if(start_time<0)start_time=0;
            else if(start_time>max_time)start_time=max_time;
   
            if(os<=start_time && start_time<=oe){
                start_time=oe;
            }
        }
        
        StringBuilder answer = new StringBuilder();
        
        String p=Integer.toString(start_time/60);
        String c=Integer.toString(start_time%60);
            
        if(p.length()==1)answer.append("0");
        answer.append(p);
        answer.append(":");
        if(c.length()==1)answer.append("0");
        answer.append(c);
        
        return answer.toString();
    }
    
    public static int secondChange(String s){
        String[] str=s.split(":");
        int time=Integer.parseInt(str[0])*60;
        int second=Integer.parseInt(str[1]);
        
        return time+second;
    }
}