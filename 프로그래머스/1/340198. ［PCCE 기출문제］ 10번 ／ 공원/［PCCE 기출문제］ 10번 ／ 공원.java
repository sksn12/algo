import java.util.*;

class Solution {

    static int[][] map;
    static int Y, X;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static Queue<Node> q;

    static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public int solution(int[] mats, String[][] park) {
        Y = park.length;
        X = park[0].length;

        map = new int[Y][X];

        // 1이면 돗자리 설치 가능, 0이면 설치 불가능
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if (park[y][x].equals("-1")) {
                    map[y][x] = 1;
                }
            }
        }

        int answer = -1;

        // 각 빈칸을 돗자리의 왼쪽 위 시작점으로 판단
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {

                if (map[y][x] == 0) {
                    continue;
                }

                boolean[][] visited = new boolean[Y][X];

                q = new ArrayDeque<>();
                q.offer(new Node(y, x));
                visited[y][x] = true;

                confirm(visited);

                for (int mat : mats) {
                    if (mat <= answer) {
                        continue;
                    }

                    // 현재 위치에서 mat × mat 정사각형이 가능한지 확인
                    if (isSquare(visited, y, x, mat)) {
                        answer = mat;
                    }
                }
            }
        }

        return answer;
    }

    // 시작점과 연결된 모든 빈칸 방문
    private static void confirm(boolean[][] visited) {
        while (!q.isEmpty()) {
            Node current = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = current.y + dy[d];
                int nx = current.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= Y || nx >= X) {
                    continue;
                }

                if (map[ny][nx] == 0) {
                    continue;
                }

                if (visited[ny][nx]) {
                    continue;
                }

                visited[ny][nx] = true;
                q.offer(new Node(ny, nx));
            }
        }
    }

    // (startY, startX)를 왼쪽 위로 하는 size × size 검사
    private static boolean isSquare(
            boolean[][] visited,
            int startY,
            int startX,
            int size
    ) {
        // 정사각형이 공원 범위를 벗어나는 경우
        if (startY + size > Y || startX + size > X) {
            return false;
        }

        for (int y = startY; y < startY + size; y++) {
            for (int x = startX; x < startX + size; x++) {
                if (!visited[y][x]) {
                    return false;
                }
            }
        }

        return true;
    }
}