import java.util.*;

class Solution {
    static List<String> list=new LinkedList<>();
    static boolean[] v;
    
    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length+1];
        
        v=new boolean[tickets.length];
        
        DFS("ICN","ICN",0,tickets);
        
        Collections.sort(list);
        
        String[] str=list.get(0).split(" ");
        
        for(int i=0;i<answer.length;i++){
            answer[i]=str[i];
        }
        return answer;
    }
    
    public void DFS(String start,String route,int level,String[][] t){
        if(level==t.length){
            list.add(route);
            return;
        }
        
        for(int i=0;i<v.length;i++){
            if(!v[i] && t[i][0].equals(start)){
                v[i]=true;
                DFS(t[i][1],route+" "+t[i][1],level+1,t);
                v[i]=false;
            }
        }
    }
}