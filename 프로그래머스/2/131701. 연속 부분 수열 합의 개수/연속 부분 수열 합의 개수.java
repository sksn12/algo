import java.util.*;

class Solution {
    Set<Integer> answer=new HashSet<>();
    
    public int solution(int[] elements) {
        
        int[] newElements=new int[elements.length*2];
        
        for(int i=0;i<2;i++){
            for(int j=0;j<elements.length;j++){
                if(i==1){
                    newElements[j+elements.length]=elements[j];                
                }else newElements[j]=elements[j];
            }
        }
        
        for(int i=0;i<elements.length;i++){
           L:for(int j=0;j<newElements.length;j++){
               int sum=0;
               
               for(int z=j;z<=j+i;z++){
                   if(z>=newElements.length)continue L;
                   sum+=newElements[z];    
               }
               
               answer.add(sum);
           }
            
            
        }
        
        return answer.size();

    }
    
}