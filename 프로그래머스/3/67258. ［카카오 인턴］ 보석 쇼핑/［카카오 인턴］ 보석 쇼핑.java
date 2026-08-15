import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int start=0;
        int end=Integer.MAX_VALUE;
        
        Set<String> set=new HashSet<>();
        
        for(String gem:gems){
            set.add(gem);
        }
        
        int left=0;
        int right=0;
        
        HashMap<String,Integer> map=new HashMap<>();
        
        while(right<gems.length){
            map.put(gems[right],map.getOrDefault(gems[right],0)+1);
            
            if(map.size()==set.size()){
                
                // 모든 원소가 있다면 left를 증가시키면서 하나씩 감소, 만약 HashMap에 있는 value가 0 이되면 left멈추기
                while(true){
                    if(map.size()!=set.size())break;
                    
                    int gemCnt=map.get(gems[left])-1;
                    if(gemCnt==0){
                        map.remove(gems[left]);
                    }else{
                        map.put(gems[left],gemCnt);
                    }
                    
                    int len=right-left;
                
                    if(len<=end-start){
                        if(len==end-start){
                            start=Math.min(start,left);
                            
                            if(start==left){
                                end=right;
                            }
                        }else{
                            start=left;
                            end=right;
                        }
                    }
                    
                    left+=1;
                }
            }
            
            right+=1;
        }
        
        answer[0]=start+1;
        answer[1]=end+1;
        return answer;
    }
}