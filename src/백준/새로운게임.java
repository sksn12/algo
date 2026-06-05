package 백준;

import java.io.*;
import java.util.*;

public class 새로운게임 {
    static int N, K;
    static int[][] map;
    static Piece[] pieces;
    static Deque<Integer>[][] board;
    static int[] dy = {0, 0, 0, -1, 1};
    static int[] dx = {0, 1, -1, 0, 0};

    static class Piece {
        int y, x, d;
        public Piece(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        board = new ArrayDeque[N][N];
        pieces = new Piece[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            pieces[i] = new Piece(y, x, d);
            board[y][x].add(i);
        }

        int turn = 0;
        while (++turn <= 1000) {
            for (int i = 0; i < K; i++) {
                Piece p = pieces[i];
                int y = p.y, x = p.x;

                if (!board[y][x].peekFirst().equals(i)) continue;

                List<Integer> moving = new ArrayList<>();
                while (!board[y][x].isEmpty()) {
                    int num = board[y][x].pollFirst();
                    moving.add(num);
                    if (num == i) break;
                }
                while (!board[y][x].isEmpty()) {
                    moving.add(board[y][x].pollFirst());
                }

                int ny = y + dy[p.d];
                int nx = x + dx[p.d];

                if (!inRange(ny, nx) || map[ny][nx] == 2) {
                    p.d = reverse(p.d);
                    ny = y + dy[p.d];
                    nx = x + dx[p.d];
                    if (!inRange(ny, nx) || map[ny][nx] == 2) {
                        for (int m : moving) board[y][x].addLast(m);
                        continue;
                    }
                }

                if (map[ny][nx] == 1) Collections.reverse(moving);

                for (int m : moving) {
                    board[ny][nx].addLast(m);
                    pieces[m].y = ny;
                    pieces[m].x = nx;
                }

                if (board[ny][nx].size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    static boolean inRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static int reverse(int d) {
        if (d == 1) return 2;
        if (d == 2) return 1;
        if (d == 3) return 4;
        return 3;
    }
}