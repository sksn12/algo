import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = nums.length/2;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int num:nums) map.put(num, map.getOrDefault(num,0)+1 );
        
        return Math.min(answer,map.size());
    }
}