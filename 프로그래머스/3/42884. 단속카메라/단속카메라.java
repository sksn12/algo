import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1])); // 종료 시점 기준 정렬

        int answer = 1;
        int camera = routes[0][1]; // 첫 차량의 끝나는 지점에 카메라 설치

        for (int i = 1; i < routes.length; i++) {
            // 카메라가 차량의 진입 시점보다 앞에 있으면 안 찍힘 → 새로운 카메라 필요
            if (camera < routes[i][0]) {
                camera = routes[i][1]; // 이 차량의 끝나는 지점에 카메라 설치
                answer++;
            }
        }

        return answer;
    }
}
