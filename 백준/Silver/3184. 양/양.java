
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dy={1,-1,0,0};
    static int[] dx={0,0,1,-1};
    static int Y,X;
    static String[][] map;
    static boolean[][] v;
    static Queue<int[]> q=new LinkedList<>();
    static int W,S;
    static int O,V;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new String[Y][X];
        v=new boolean[Y][X];

        for (int i = 0; i < Y; i++) {
            String[] str=br.readLine().split("");
            for (int j = 0; j < X; j++) {
                map[i][j]=str[j];
            }
        }


        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if(!v[i][j] && !map[i][j].equals("#")){
                    O=0;
                    V=0;
                    q.offer(new int[] {i,j});
                    v[i][j]=true;
                    if (map[i][j].equals("o"))O+=1;
                    else if(map[i][j].equals("v"))V+=1;
                    BFS();

                    if(O>V)S+=O;
                    else W+=V;
                }
            }
        }

        System.out.println(S+" "+W);
    }

    private static void BFS() {
        while (!q.isEmpty()){
            int[] yx=q.poll();
            int y=yx[0];
            int x=yx[1];

            for (int d = 0; d < 4; d++) {
                int ny=dy[d]+y;
                int nx=dx[d]+x;

                if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx] && !map[ny][nx].equals("#")){
                    if (map[ny][nx].equals("o"))O+=1;
                    else if(map[ny][nx].equals("v"))V+=1;

                    q.offer(new int[] {ny,nx});
                    v[ny][nx]=true;
                }
            }
        }
    }
}
