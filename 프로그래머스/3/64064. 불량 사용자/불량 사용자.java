import java.util.*;

class Solution {
    static List<Integer>[] list;
    static boolean[] v;
    static int[] path;
    static Set<String> answer=new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {        
        list=new List[banned_id.length];
        
        for(int i=0;i<banned_id.length;i++){
            list[i]=new ArrayList<>();
        }
        
        for(int i=0;i<list.length;i++){
            String[] s1=banned_id[i].split("");
            
            for(int c=0;c<user_id.length;c++){
                String[] s2=user_id[c].split("");
                boolean val=false;
                
                if(s1.length!=s2.length)continue;
                else{
                    for(int j=0;j<s1.length;j++){
                        if(s1[j].equals("*"))continue;
                        if(!s1[j].equals(s2[j]))val=true;
                    }
                }
                
                if(!val)list[i].add(c);
            }
        }
        
        // 조합짜기 -> 숫자넣고 오름차순 정렬, set에 넣고 set size를 정답으로 출력 
        v=new boolean[list.length];
        path=new int[list.length];
        
        recursive(0);
        
        return answer.size();
    }
    
    public static void recursive(int level){
        if(level==list.length){
            boolean[] val=new boolean[8];
            
            for(int i:path){
                if(val[i]){
                    return;
                }
                val[i]=true;
            }
            
            String[] str=new String[path.length];
            for(int i=0;i<path.length;i++){
                str[i]=Integer.toString(path[i]);
            } 
            
            Arrays.sort(str);
            
            String tt="";
            for(String s:str){
                tt+=s;
            }
            
            answer.add(tt);
            
            return;
        }
        
        for(int i=level;i<list.length;i++){
            if(!v[i]){
                v[i]=true;
                for(int z=0;z<list[i].size();z++){
                    path[level]=list[i].get(z);
                    recursive(level+1);
                }
                v[i]=false;
            }
        }
    }
}