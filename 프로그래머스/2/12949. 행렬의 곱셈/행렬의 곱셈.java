class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int n = arr1.length;        // arr1의 행 개수
        int m = arr1[0].length;     // arr1의 열 개수
        int k = arr2[0].length;     // arr2의 열 개수

        int[][] answer = new int[n][k];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                for (int t = 0; t < m; t++) {
                    answer[i][j] += arr1[i][t] * arr2[t][j];
                }
            }
        }

        return answer;
    }
}