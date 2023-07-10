
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int end,m,N;
    static int[][] map;
    static boolean[][] v;
    static boolean val=false;

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
         N=Integer.parseInt(br.readLine());

        StringTokenizer st=new StringTokenizer(br.readLine());

        int ey=Integer.parseInt(st.nextToken());
        end=Integer.parseInt(st.nextToken());

        m=Integer.parseInt(br.readLine());

        map=new int[N+1][N+1];
        v=new boolean[N+1][N+1];

        for (int i = 0; i < m; i++) {
            st=new StringTokenizer(br.readLine());

            int y=Integer.parseInt(st.nextToken());
            int x=Integer.parseInt(st.nextToken());

            map[y][x]=1;
            map[x][y]=1;
        }

        dfs(ey,0);

        if(!val) System.out.println(-1);
    }

    private static void dfs(int start,int cnt) {
        if(start==end){
            System.out.println(cnt);
            val=true;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(map[start][i]==1 && !v[start][i]){
                v[start][i]=true;
                v[i][start]=true;
                dfs(i,cnt+1);
            }
        }
    }
}
