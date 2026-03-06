class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        int cnt=citations.length;
        
        for(int i=0;i<cnt;i++){
            int h=cnt-i;
            int over=0;
            int down=0;
            
            for(int j=0;j<cnt;j++){
                if(citations[j]>=h)over+=1;
                else down+=1;
            }
            
            if(h<=over && h>=down){
                return h;               
            }
        }
        return answer;
    }
}