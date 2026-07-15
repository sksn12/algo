import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        int[] cnt=new int[want.length];
        
        for(int i=0;i<10;i++){
            String s=discount[i];
            
            int index=indexLoc(s,want);
            
            if(index>=0){
                cnt[index]+=1;
            }
        }
        
        boolean val2=false;
        for(int i=0;i<cnt.length;i++){
            if(number[i]>cnt[i])val2=true;
        }
            
        if(!val2) answer+=1;
        
        int p1=0;
        for(int p2=10;p2<discount.length;p2++){
            int index=indexLoc(discount[p1],want);
            int index2=indexLoc(discount[p2],want);
            
            if(index>=0){
               cnt[index]-=1; 
            }
            
            if(index2>=0){
                cnt[index2]+=1;
            }
            
            boolean val=false;
            for(int i=0;i<cnt.length;i++){
                if(number[i]>cnt[i])val=true;
            }
            
            if(!val) answer+=1;
            
            p1+=1;
        }
        
        return answer;
    }
    
    public static int indexLoc(String s,String[] want){
        for(int i=0;i<want.length;i++){
            if(s.equals(want[i]))return i;
        }
        
        return -1;
    }
}