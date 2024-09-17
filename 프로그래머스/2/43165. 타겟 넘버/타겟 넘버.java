class Solution {
    static boolean[] plusOrMinus;
    static int answer=0;
    
    public int solution(int[] numbers, int target) {
        
        plusOrMinus=new boolean[numbers.length];
        
        recursive(numbers,target,0);
        
        return answer;
    }
    
    public static void recursive(int[] numbers,int target,int level){
        if(level==numbers.length){
            int sum=0;
            
            for(int i=0;i<numbers.length;i++){
                if(plusOrMinus[i])sum+=numbers[i];
                else sum-=numbers[i];
            }
            
            if(sum==target)answer+=1;
            return;
        }
        
        plusOrMinus[level]=true;
        recursive(numbers,target,level+1);
        plusOrMinus[level]=false;
        recursive(numbers,target,level+1);
    }
}