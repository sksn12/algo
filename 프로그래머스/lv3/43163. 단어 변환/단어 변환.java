import java.util.*;

class Solution {
    static class node{
        String str;
        int cnt;
        
        node(String str,int cnt){
            this.str=str;
            this.cnt=cnt;
        }
        
    }
    
    static Queue<node> q=new LinkedList<>();
    static int answer=0;
    
    public int solution(String begin, String target, String[] words) {
        boolean val=false;
        for(String s:words){
            if(s.equals(target))val=true;
        }
        
        if(!val)return 0;
        
        q.offer(new node(begin,0));
        
        BFS(target,words);
        
        return answer;
    } 
    
    public void BFS(String target, String[] words){
        while(!q.isEmpty()){
            node n=q.poll();
            
            if(n.str.equals(target)){
                answer=n.cnt;
                return;
            }
            
            String[] s=n.str.split("");
            
            for(int i=0;i<words.length;i++){
                int cnt=0;
                String[] s2=words[i].split("");
                
                for(int j=0;j<s.length;j++){
                    if(!s[j].equals(s2[j])){
                        cnt+=1;
                    }
                }
                
                if(cnt==1){
                    q.offer(new node(words[i],n.cnt+1));
                }
            }
        }
    }
}