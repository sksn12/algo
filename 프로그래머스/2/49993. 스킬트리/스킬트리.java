import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        int[] Alpa=new int[26];
        
        for(int i=0;i<skill.length();i++){
            Alpa[(skill.charAt(i)-'0')-17]=i+1;
        }
        
        L:for(String skill_tree:skill_trees){
            int seq=1;
            
            for(int i=0;i<skill_tree.length();i++){
                int skill_seq=Alpa[(skill_tree.charAt(i)-'0')-17];
                
                if(skill_seq!=0){
                    if(skill_seq==seq)seq+=1;
                    else continue L;
                }
            }
            
            System.out.println(skill_tree);
            answer+=1;
        }
        
        return answer;
    }
}