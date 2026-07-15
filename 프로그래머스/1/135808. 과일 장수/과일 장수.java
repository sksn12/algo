import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        if(score.length<m)return 0;
        
        Integer[] arr=new Integer[score.length];
        
        for(int i=0;i<score.length;i++){
            arr[i]=Integer.valueOf(score[i]);
        }
        
        Arrays.sort(arr,Collections.reverseOrder());
        
        int[] tmp=new int[score.length];
        
        for(int i=1;i<=arr.length;i++){
            if((i-1)%m==0){
                answer+=tmp[m-1]*m;
                tmp=new int[m];
            }
            
            tmp[(i-1)%m]=arr[i-1];
            
            if(i==arr.length && i%m==0){
                answer+=tmp[m-1]*m;
            }
        }
        
        
        // System.out.println(arr[0]);
        return answer;
    }
}