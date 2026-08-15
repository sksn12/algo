class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        L:for(int i=1;i<=number;i++){
            int powerCnt=1;
            for(int j=1;j<=Math.abs(i/2);j++){
                if(i%j==0)powerCnt+=1;
                
                if(powerCnt>100){
                    powerCnt=power;
                    answer+=powerCnt;
                    continue L;
                }
            }
            if(powerCnt>limit)powerCnt=power;
            answer+=powerCnt;
        }
        
        return answer;
    }
}