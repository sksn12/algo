import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr = new int[6][3]; // 승 무 패
    static int[][] match = new int[15][2];
    static boolean ok;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 15경기 조합 미리 생성
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                match[idx][0] = i;
                match[idx][1] = j;
                idx++;
            }
        }

        for (int t = 0; t < 4; t++) {
            ok = false;
            st = new StringTokenizer(br.readLine());

            boolean valid = true;

            for (int i = 0; i < 6; i++) {
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
                arr[i][2] = Integer.parseInt(st.nextToken());

                // 🔥 사전 검증
                if (arr[i][0] + arr[i][1] + arr[i][2] != 5) {
                    valid = false;
                }
            }

            if (valid) dfs(0);

            System.out.print(ok ? 1 : 0);
            if (t < 3) System.out.print(" ");
        }
    }

    static void dfs(int game) {
        if (ok) return;

        if (game == 15) {
            ok = true;
            return;
        }

        int a = match[game][0];
        int b = match[game][1];

        // a 승 b 패
        if (arr[a][0] > 0 && arr[b][2] > 0) {
            arr[a][0]--;
            arr[b][2]--;
            dfs(game + 1);
            arr[a][0]++;
            arr[b][2]++;
        }

        // 무승부
        if (arr[a][1] > 0 && arr[b][1] > 0) {
            arr[a][1]--;
            arr[b][1]--;
            dfs(game + 1);
            arr[a][1]++;
            arr[b][1]++;
        }

        // a 패 b 승
        if (arr[a][2] > 0 && arr[b][0] > 0) {
            arr[a][2]--;
            arr[b][0]--;
            dfs(game + 1);
            arr[a][2]++;
            arr[b][0]++;
        }
    }
}
