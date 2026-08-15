
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] map;
    static int[] population;
    static boolean[] selected;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        population = new int[N + 1];
        selected = new boolean[N + 1];
        map = new List[N + 1];

        for (int i = 1; i <= N; i++) map[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) population[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int to = Integer.parseInt(st.nextToken());
                map[i].add(to);
            }
        }

        // 모든 가능한 조합에 대해 완전 탐색
        for (int i = 1; i <= N / 2; i++) {
            combine(1, 0, i);
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    // 조합으로 A 선거구 선택
    static void combine(int start, int depth, int size) {
        if (depth == size) {
            List<Integer> A = new ArrayList<>();
            List<Integer> B = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                if (selected[i]) A.add(i);
                else B.add(i);
            }

            if (isConnected(A) && isConnected(B)) {
                int sumA = 0, sumB = 0;
                for (int i : A) sumA += population[i];
                for (int i : B) sumB += population[i];
                answer = Math.min(answer, Math.abs(sumA - sumB));
            }

            return;
        }

        for (int i = start; i <= N; i++) {
            selected[i] = true;
            combine(i + 1, depth + 1, size);
            selected[i] = false;
        }
    }

    // 연결되어 있는지 확인하는 BFS
    static boolean isConnected(List<Integer> group) {
        if (group.isEmpty()) return false;

        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(group.get(0));
        visited[group.get(0)] = true;
        int count = 1;

        while (!q.isEmpty()) {
            int current = q.poll();
            for (int neighbor : map[current]) {
                if (!visited[neighbor] && group.contains(neighbor)) {
                    visited[neighbor] = true;
                    q.offer(neighbor);
                    count++;
                }
            }
        }

        return count == group.size();
    }
}
