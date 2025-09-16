import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N 입력
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();

        // N개의 카드 숫자 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // M 입력
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        // M개의 쿼리 처리
        for (int i = 0; i < M; i++) {
            int query = Integer.parseInt(st.nextToken());
            sb.append(map.getOrDefault(query, 0)).append(" ");
        }

        // 한 번에 출력
        System.out.println(sb);
    }
}
