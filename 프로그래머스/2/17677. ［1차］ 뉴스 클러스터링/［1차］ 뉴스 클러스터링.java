import java.util.*;

class Solution {
    static HashMap<String,Integer> str1_map=new HashMap<>();
    static HashMap<String,Integer> str2_map=new HashMap<>();
    
    public int solution(String str1, String str2) {
        int answer = 0;
        confirm(str1.split(""),true);
        confirm(str2.split(""),false);
        
        int cross_cnt=0; // 교집합 갯수
        int total_cnt=0; // 합집합 갯수
        
        for (Map.Entry<String, Integer> entry : str1_map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            
            int cnt=str2_map.getOrDefault(key,0);
             
            // str1에만 있는 값이라 합집합에 카운트
            if(cnt==0)total_cnt+=value;
            else{ // str1에도 있고, str2에도 있는 값 -> 교집합
                int max=Math.max(cnt,value);
                int min=Math.min(cnt,value);
                
                cross_cnt+=min;
                total_cnt+=max;
            }
        }
        
        // 겹치는 모든 값을 제외하고 str2에만 있는 값을 합집합에 넣어줌 -> 이미 위에서 겹치는 모든 값을 넣어주고 와서
        for (Map.Entry<String, Integer> entry : str2_map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            int cnt=str1_map.getOrDefault(key,0);
            
            if(cnt==0)total_cnt+=value;
        }
        if (total_cnt == 0) return 65536;
        
        answer=(int)(((double) cross_cnt/ (double) total_cnt)  * 65536);
    
        return answer;
    }
    
    // 문자열을 두 글자씩 끊어서 영어만 있다면 hashmap에 넣어줌
    public static void confirm(String[] str,boolean tmp){
        for(int i=0;i<str.length-1;i++){
            String s=str[i]+str[i+1];
            s=s.toUpperCase();
            
            // 영문자만 포함되어 있을경우만 사용
            if (s.matches("^[A-Z]+$")) {
                if(tmp){
                    str1_map.put(s,str1_map.getOrDefault(s,0)+1);
                }else{
                    str2_map.put(s,str2_map.getOrDefault(s,0)+1);
                }
            }
            
        }
    }
}