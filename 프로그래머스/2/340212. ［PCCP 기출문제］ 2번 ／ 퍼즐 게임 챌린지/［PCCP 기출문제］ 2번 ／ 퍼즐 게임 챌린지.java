import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;
        int left=1;
        int right=100000;
        
        while(left<=right){
            int mid=(left+right)/2;
            
            // 현재 레벨이면 얼마의 시간이 걸리는지 계산 = total
            long total = 0;

            for (int i = 0; i < diffs.length; i++) {

                if (diffs[i] <= mid) {
                    total += times[i];
                } else {
                    int d = diffs[i] - mid;

                    if (i == 0) {
                        total += (long) times[i] * d + times[i];
                    } else {
                        total += (long) (times[i] + times[i - 1]) * d
                               + times[i];
                    }
                }
            }
            
            if(limit<total){
                left=mid+1;
            }else{
                answer=Math.min(answer,mid);
                right=mid-1;
            }
        }
        
        
        return answer;
    }
}