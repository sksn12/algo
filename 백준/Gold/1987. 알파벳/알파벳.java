
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static Character[][] map;
    static int answer=0;
    static int Y,X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new Character[Y][X];

        for (int i = 0; i < Y; i++) {
            String str = br.readLine();
            for (int j = 0; j < X; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        List<Character> list = new ArrayList<>();
        list.add(map[0][0]);

        dfs(1,list,0,0);
        System.out.println(answer);
    }

    private static void dfs(int cnt, List<Character> list, int y, int x) {
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(0<=ny && 0<=nx && ny<Y && nx<X){
                boolean check = false;
                for (Character c : list) {
                    if (map[ny][nx].equals(c)) {
                        check = true;
                        break;
                    }
                }

                // list에 같은 값을 지니고 있어 해당 방향으로 이동 불가
                if (check) {
                    answer = Math.max(answer, cnt);
                } else {
                    list.add(map[ny][nx]);
                    dfs(cnt + 1, list, ny, nx);
                    list.remove(list.size()-1);
                }
            }

        }

    }

}
