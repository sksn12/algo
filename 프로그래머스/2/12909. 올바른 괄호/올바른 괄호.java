import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Stack<Character> stack=new Stack<>();
        
        Character[] C=new Character[s.length()];
        
        for(int i=0;i<s.length();i++){
            C[i]=s.charAt(i);
        }
        
        for(Character c:C){
            if(c=='(')stack.push(c);
            else{
                if(stack.size()==0)return false;
                Character tmp=stack.pop();
            }
        }
        
        if(stack.size()!=0)return false;

        return answer;
    }
}