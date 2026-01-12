import java.util.*;

class Solution {
    static int N=0;
    static List<Integer> list=new ArrayList<>();
    static double[] map; // 계산된 넓이
    
    public double[] solution(int k, int[][] ranges) {
        
        // 초기 콜라츠 추측
        Collatz(k);
        
        map=new double[N+1];
        
        // 각 배열의 넓이 계산
        for(int i=0;i<N;i++){
            cal(i);
        }
        
        double[] answer = new double[ranges.length];
        
        for(int i=0;i<ranges.length;i++){
            int a=ranges[i][0];
            int b=N+ranges[i][1];
            
            if(a>b){
                answer[i]=-1;
            }else{
                double sum=0;
                
                for(int s=a;s<b;s++){
                    sum+=map[s];
                }
                
                answer[i]=sum;
            }
            
        }
        
        return answer;
    }
    
    public static void cal(int i){
        int a=list.get(i);
        int b=list.get(i+1);
        
        int max=Math.max(a,b);
        int min=Math.min(a,b);
        
        map[i]=min+(max-min)*0.5;
    }
    
    public static void Collatz(int k){
        list.add(k);
        
        while(true){
            if(k==1)break;
            
            if(k%2==0){
                k=k/2;
                list.add(k);
            }
            else{
                k=k*3+1;
                list.add(k);
            }
            
            N+=1;
        }
    }
}