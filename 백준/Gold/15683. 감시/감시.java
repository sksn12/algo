import java.util.*;
import java.io.*;

public class Main {
    static int Y, X;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][][] cctvDirs = {
            {}, // 0번 없음
            {{0}, {1}, {2}, {3}},                     // 1번: 상, 우, 하, 좌
            {{0, 2}, {1, 3},{},{}},                         // 2번: 상하 / 좌우
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},         // 3번: ㄱ자 4가지
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // 4번: 3방향 감시
            {{0, 1, 2, 3},{},{},{}}                            // 5번: 4방향 고정
    };

    static int[] cctv;
    static int answer = Integer.MAX_VALUE;
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new int[Y][X];
        int cctvCnt = 0;

        for (int i = 0; i < Y; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                int next = Integer.parseInt(st.nextToken());
                map[i][j] = next;

                if (next != 6 && next != 0) {
                    list.add(new int[]{i, j});
                    cctvCnt += 1;
                }
            }
        }

        cctv = new int[cctvCnt];

        recursive(0);

        System.out.println(answer);
    }

    public static void recursive(int idx) {
        if (idx == cctv.length) {
            search();
            return;
        }

        for (int i = 0; i < 4; i++) {
            cctv[idx] = i;
            recursive(idx + 1);
        }
    }

    public static void search() {
        int[][] testMap = new int[Y][X];

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                testMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < list.size(); i++) {
            // cctv의 위치
            int[] yx = list.get(i);
            int y = yx[0];
            int x = yx[1];

            int type = map[y][x];
            // cctv의 방향
            int dir = cctv[i];

            for (int d : cctvDirs[type][dir]) {
                int ny = y;
                int nx = x;
                while (true) {
                    ny += dy[d];
                    nx += dx[d];
                    if (ny < 0 || ny >= Y || nx < 0 || nx >= X || testMap[ny][nx] == 6) break;
                    if (testMap[ny][nx] == 0) testMap[ny][nx] = -1;
                }
            }
        }

        int cnt = 0;
        for (int i2 = 0; i2 < Y; i2++) {
            for (int j = 0; j < X; j++) {
                if (testMap[i2][j] == -1 || testMap[i2][j] == 6) cnt += 1;
            }
        }

        answer = Math.min(answer, Y * X - cnt - cctv.length);
    }
}