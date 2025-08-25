import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        
        for (int i = 1; i <= order.length; i++) {
            // 메인 벨트에서 상자 가져오기
            stack.push(i);
            
            // 스택에서 처리할 수 있는 상자를 계속 꺼냄
            while (!stack.isEmpty() && stack.peek() == order[idx]) {
                stack.pop();
                idx++;
                answer++;
            }
        }
        
        return answer;
    }
}
