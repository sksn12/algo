import java.util.*;

class Solution {
    static String[] arr;
    static String[] sel;
    static boolean[] v;
    static Map<String,Integer> map;
    static List<String> list=new ArrayList<>();
    static int max;
    
    public String[] solution(String[] orders, int[] course) {
        // 1. courseCnt == orders 원소의 조합의 갯수 
        for(int courseCnt:course){
            map=new HashMap<>();
            max=0;
            
            for(String order:orders){
                arr=order.split("");
                Arrays.sort(arr);
                
                v=new boolean[arr.length];
                sel=new String[courseCnt];
                
                
                recursive(0,0);
            }
            
            map.forEach((key,value)->{
                if(value>=2 && value==max)list.add(key);
            });
        }
        
        Collections.sort(list);
        
        String[] answer=new String[list.size()];
        for(int i=0;i<answer.length;i++){
            answer[i]=list.get(i);
        }
        
        return answer;
    }
    
    public static void recursive(int level,int start){
        if(level==sel.length){
            String str="";
    
            for(int i=0;i<sel.length;i++){
                str+=sel[i];
            }
            
            max=Math.max(map.getOrDefault(str,0)+1,max);
            map.put(str,map.getOrDefault(str,0)+1);
            
            return;
        }
        
        for(int i=start;i<arr.length;i++){
            if(!v[i]){
                v[i]=true;
                sel[level]=arr[i];
                recursive(level+1,i+1);
                v[i]=false;
            }
            
        }
    }
    
}

// 1. XW랑 WX랑 다르게 식별

