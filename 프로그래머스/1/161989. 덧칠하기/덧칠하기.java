class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 1;
        
        int p1=0;
        for(int p2=p1+1;p2<section.length;p2++){
            if(section[p2]-section[p1]<m){
                continue;
            }else{
                p1=p2;
                answer+=1;
            }
            
        }
        
        return answer;
    }
}

