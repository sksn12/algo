import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;

    static int[] dy = {-1, 1, 0, 0}; // 위 아래 왼 오
    static int[] dx = {0, 0, -1, 1};

    static class Node {
        int ry, rx, by, bx, cnt;

        Node(int ry, int rx, int by, int bx, int cnt) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.cnt = cnt;
        }
    }

    static class MoveResult {
        int y, x, dist;
        boolean inHole;

        MoveResult(int y, int x, int dist, boolean inHole) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.inHole = inHole;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        int sry = 0, srx = 0, sby = 0, sbx = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'R') {
                    sry = i;
                    srx = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    sby = i;
                    sbx = j;
                    map[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(sry, srx, sby, sbx));
    }

    static int bfs(int sry, int srx, int sby, int sbx) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(sry, srx, sby, sbx, 0));
        visited[sry][srx][sby][sbx] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.cnt >= 10) continue;

            for (int d = 0; d < 4; d++) {

                MoveResult red = move(cur.ry, cur.rx, d);
                MoveResult blue = move(cur.by, cur.bx, d);

                // 파란 공이 구멍에 빠지면 실패
                if (blue.inHole) continue;

                // 빨간 공만 빠지면 성공
                if (red.inHole) return 1;

                // 둘이 겹쳤다면
                if (red.y == blue.y && red.x == blue.x) {
                    if (red.dist > blue.dist) {
                        red.y -= dy[d];
                        red.x -= dx[d];
                    } else {
                        blue.y -= dy[d];
                        blue.x -= dx[d];
                    }
                }

                if (!visited[red.y][red.x][blue.y][blue.x]) {
                    visited[red.y][red.x][blue.y][blue.x] = true;
                    q.offer(new Node(red.y, red.x, blue.y, blue.x, cur.cnt + 1));
                }
            }
        }

        return 0;
    }

    static MoveResult move(int y, int x, int d) {
        int dist = 0;

        while (true) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (map[ny][nx] == '#') break;

            y = ny;
            x = nx;
            dist++;

            if (map[y][x] == 'O') {
                return new MoveResult(y, x, dist, true);
            }
        }

        return new MoveResult(y, x, dist, false);
    }
}