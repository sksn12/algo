import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][M];

        // 기본값 1로 초기화
        for (int i = 0; i < M; i++) {
            Arrays.fill(map[i], 1);
        }

        // 가장자리 길이
        int size = 2 * M - 1;

        // 차분 배열
        int[] plus = new int[size + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            // 1 증가 시작
            plus[zero] += 1;

            // 2 증가 시작 (사실 +1을 한 번 더)
            plus[zero + one] += 1;
        }

        // 누적합
        for (int i = 1; i < size; i++) {
            plus[i] += plus[i - 1];
        }

        // 가장자리 채우기
        int sy = M - 1;
        int sx = 0;

        for (int i = 0; i < size; i++) {
            map[sy][sx] += plus[i];

            if (sy > 0) sy--;
            else sx++;
        }

        // 내부 채우기 (왼쪽 위 대각선 값 복사)
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                map[i][j] = map[i - 1][j];
            }
        }

        print();
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}