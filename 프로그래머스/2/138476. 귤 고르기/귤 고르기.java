import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer,Integer> map=new HashMap<>();
        
        for(int t:tangerine){
            map.put(t,map.getOrDefault(t,0)+1);
        }
        
         Map<Integer, Integer> sortedMap = map.entrySet().stream()
        .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()) 
        .collect(Collectors.toMap(
            Map.Entry::getKey,                                            
            Map.Entry::getValue,                                          
            (oldValue, newValue) -> oldValue,            
            LinkedHashMap::new                           
        ));

        
        for(int key:sortedMap.keySet()){
            if(k<=0)break;
            
            k-=sortedMap.get(key);
            answer+=1;
        }
        
        return answer;
    }
}