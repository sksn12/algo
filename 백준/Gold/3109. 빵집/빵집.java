
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int Y,X,flag;
    static char[][] map;
    static boolean[][] v;
    static int[] dy={-1,0,1};
    static int[] dx={1,1,1};
    static int answer=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new char[Y][X];
        v=new boolean[Y][X];

        for (int i = 0; i < Y; i++) {
            String str=br.readLine();
            for (int j = 0; j < X; j++) {
                map[i][j]=str.charAt(j);
            }
        }


        // 시작열에서 갈 수 있는 모든 경우 탐색 방문배열은 공유
        // 오른쪽 위부터 오른쪽 오른쪽 아래 순으로 dfs를 타게되면 파이프라인이 가장 위부터 순서대로 만들어 짐으로
        // 가장 많은 경우의 수가 생성된다.
        for (int i = 0; i < Y; i++) {
            flag=0;
            dfs(i,0);
            
        }
        System.out.println(answer);


    }

    private static void dfs(int y, int x) {
        if(flag==1)return;
        for (int d = 0; d < 3; d++) {
            int ny=dy[d]+y;
            int nx=dx[d]+x;

            if(flag==1)return;

            if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx] && map[ny][nx]=='.'){
                v[ny][nx]=true;
                // 끝 열에 도착 할 수 있으면 +1
                if(nx==X-1){
                    answer+=1;
                    flag=1;
                    return;
                }
                dfs(ny,nx);
                // 순서대로 전진이 하나라도 가능하다면 해당 위치에서 dfs 가짓수를 없애야함
            }
        }

    }
}
