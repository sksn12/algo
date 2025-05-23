
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int Y,X,answer=0;
    static int[][] map;
    static List<int[]> list;
    static int[] dy={1,-1,0,0};
    static int[] dx={0,0,-1,1};
    static boolean[][] v;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new int[Y][X];

        for (int i = 0; i < Y; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        while (true){
            list=new ArrayList<>();
            v=new boolean[Y][X];
            q=new LinkedList<>();
            
            v[0][0]=true;
            q.offer(new int[] {0,0});
            BFS();

            Search();

            Delete();

            if(Confirm()){
                System.out.println(answer+1);
                System.out.println(list.size());
                break;
            }

            answer+=1;

        }
    }

    private static void BFS() {
        while (!q.isEmpty()){
            int[] yx=q.poll();

            for (int d = 0; d <4; d++) {
                int ny=dy[d]+yx[0];
                int nx=dx[d]+yx[1];
                
                if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx] && map[ny][nx]==0){
                    v[ny][nx]=true;
                    q.offer(new int[] {ny,nx});
                }
            }
        }
    }

    private static boolean Confirm() {
        for (int i = 1; i < Y-1; i++) {
            for (int j = 1; j <X-1 ; j++) {
                if(map[i][j]==1)return false;
            }
        }
        return true;
    }

    private static void Delete() {
        for (int[] yx:list) {
            int y=yx[0];
            int x=yx[1];

            map[y][x]=0;
        }
    }

    private static void Search() {
        for (int i = 1; i < Y-1; i++) {
            for (int j = 1; j < X-1; j++) {
                // 치즈면 사방탐색 후 0을 한번이라도 만나면 list넣기
                if(map[i][j]==1){
                    boolean val=false;
                    for (int d = 0; d < 4; d++) {
                        int ny=i+dy[d];
                        int nx=j+dx[d];

                        if(0<=ny && 0<=nx && ny<Y && nx<X && v[ny][nx]){
                            if(map[ny][nx]==0)val=true;
                        }
                    }

                    // 공기층을 만날때
                    if(val){
                        list.add(new int[] {i,j});
                    }
                }
            }
        }

    }
}
