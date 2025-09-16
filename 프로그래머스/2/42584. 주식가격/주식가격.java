class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = i + 1; j < n; j++) {
                cnt++;
                if (prices[j] < prices[i]) break;
            }
            answer[i] = cnt;
        }

        return answer;
    }
}
