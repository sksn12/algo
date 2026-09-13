import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        String[][] map=new String[100][100];
        
        for(int i=0;i<100;i++){
            for(int j=0; j<100; j++){
                map[i][j]="test";
            }
        }
        
        int max=0;
        
        for(int i=0;i<keymap.length;i++){
            String[] str=keymap[i].split("");
            
            max=Math.max(str.length,max);
            for(int j=0;j<str.length;j++){
                map[i][j]=str[j];
            }
        }
        
        L:for(int i=0;i<targets.length;i++){
            String[] str=targets[i].split("");
            int sum=0;
            for(int j=0;j<str.length;j++){
                String target=str[j];
                int minSum=Integer.MAX_VALUE;
                for(int c=0;c<keymap.length;c++){
                    for(int z=0;z<max;z++){
                        if(map[c][z].equals(target)) minSum=Math.min(minSum,z+1);
                    }
                }
                if(minSum==Integer.MAX_VALUE){
                    answer[i]=-1;
                    continue L;
                }else sum+=minSum;
            }
            
            answer[i]=sum; 
        }
        
        return answer;
    }
}