import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int i=0;i<commands.length;i++){
            int s=commands[i][0]-1;
            int e=commands[i][1];
            int p=commands[i][2];
            
            ArrayList<Integer> list=new ArrayList<>();
            for(int s1=s;s1<e;s1++){
                list.add(array[s1]);
            }
            
            Collections.sort(list);
            
            answer[i]=list.get(p-1);
        }
        return answer;
    }
}