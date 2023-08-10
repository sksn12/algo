
package 백준;

import java.io.IOException;
import java.util.Arrays;


public class 조합 {

    static int path[];

    private static void dfs(int level, int start) {

        if (level == 4) {
            System.out.println(Arrays.toString(path));
            return;
        }

        for (int i = start; i < 5; i++) {
            path[level] = i;
            dfs(level + 1, i + 1);
            path[level] = 0;
        }

    }

    public static void main(String[] args) throws IOException {
        path = new int[4];
        dfs(0, 0);

    }
}