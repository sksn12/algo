import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        StringBuilder sb=new StringBuilder();
        while(n>0){
            sb.append(n%k);
            n=n/k;
        }
        
        String[] str=sb.reverse().toString().split("0");
        
        // 소수가 맞는지 확인(1과 본인만 나눠야함)
        for(int i=0;i<str.length;i++){
            if(str[i].length()>0){
                long tmp=Long.parseLong(str[i]); // n이 백만일때 2진수로 만들면 21억이 넘는 수가 나올수가 있어서 long으로             
                if(tmp < 2) continue;

                boolean isPrime = true;

                for(long j=2; j*j <= tmp; j++){
                    if(tmp % j == 0){
                        isPrime = false;
                        break;
                    }
                }

                if(isPrime) answer++;
                    }
                }
        
        return answer;
    }
}