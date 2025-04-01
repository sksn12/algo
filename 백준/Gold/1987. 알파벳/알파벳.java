import java.util.*;
import java.io.*;

public class Main{
    
    static int Y, X, answer = 0;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static char[][] map;
    static boolean[] alpha = new boolean[26]; // A~Z 알파벳 사용 여부
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new char[Y][X];
        for (int i = 0; i < Y; i++) {
            String line = br.readLine();
            for (int j = 0; j < X; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        alpha[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(answer);
    }

    public static void dfs(int y, int x, int depth) {
        answer = Math.max(answer, depth);

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (0 <= ny && ny < Y && 0 <= nx && nx < X) {
                int idx = map[ny][nx] - 'A';
                if (!alpha[idx]) {
                    alpha[idx] = true;
                    dfs(ny, nx, depth + 1);
                    alpha[idx] = false; // 백트래킹
                }
            }
        }
    }

}