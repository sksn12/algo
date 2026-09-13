import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        HashMap<String,Integer> map=new HashMap<>();
        
        for(int i=0;i<survey.length;i++){
            String[] str=survey[i].split("");
            String s1=str[0];
            String s2=str[1];

            if(choices[i]>4){
                map.put( s2 , map.getOrDefault(s2,0) + choices[i]-4 );
                
            }else if(choices[i]<4){
                map.put( s1 , map.getOrDefault(s1,0) + 4-choices[i] );
            }
            
        }
        
        if(map.getOrDefault("R",0) >= map.getOrDefault("T",0) ){
            answer+="R";
        }else if(map.getOrDefault("R",0) < map.getOrDefault("T",0) ){
            answer+="T";          
        }
        
        if(map.getOrDefault("C",0) >= map.getOrDefault("F",0) ){
            answer+="C";
        }else if(map.getOrDefault("C",0) < map.getOrDefault("F",0) ){
            answer+="F";            
        }
        
        if(map.getOrDefault("J",0) >= map.getOrDefault("M",0) ){
            answer+="J";
        }else if(map.getOrDefault("J",0) < map.getOrDefault("M",0) ){
            answer+="M";            
        }
        
        if(map.getOrDefault("A",0) >= map.getOrDefault("N",0) ){
            answer+="A";
        }else if(map.getOrDefault("A",0) < map.getOrDefault("N",0) ){
            answer+="N";            
        }
        
        return answer;
    }
}