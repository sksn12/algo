
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        // pos와 height 저장을 위한 배열. 문제 제약을 0..1000으로 가정 (안전하게 0..1000)
        final int MAX_POS = 1000;
        int[] h = new int[MAX_POS + 1];

        int minPos = MAX_POS;
        int maxPos = 0;
        int maxHeight = 0;
        int maxIndex = 0; // 최댓높이의 x 위치 (왼쪽 우선)

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            h[pos] = Math.max(h[pos], height); // 같은 pos가 여러 번 올 수 있으므로 max
            if (pos < minPos) minPos = pos;
            if (pos > maxPos) maxPos = pos;

            if (h[pos] > maxHeight) {
                maxHeight = h[pos];
                maxIndex = pos;
            } else if (h[pos] == maxHeight) {
                // 같은 최대 높이가 여러 개일 때 왼쪽 것을 유지하려면 아무것도 안 해도 됨.
                // (현재는 첫 등장 시 maxIndex가 설정되므로 유지됨)
            }
        }

        int area = 0;

        // 왼쪽에서 maxIndex까지
        int cur = 0;
        for (int x = minPos; x <= maxIndex; x++) {
            if (h[x] > cur) cur = h[x];
            area += cur;
        }

        // 오른쪽에서 maxIndex+1부터 maxPos까지 (내려오면서)
        cur = 0;
        for (int x = maxPos; x > maxIndex; x--) {
            if (h[x] > cur) cur = h[x];
            area += cur;
        }

        System.out.println(area);
        br.close();
    }
}
