import java.util.*;
import java.io.*;

/*
        5 5
        .xx..
        ..x..
        .....
        ...x.
        ...x.
 */

public class Main {
    static int Y,X;
    static boolean val;
    static char[][] map;
    static boolean[][] v;
    static int[] dy={-1,0,1};
    static int[] dx={1,1,1};
    static int answer=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new char[Y][X];

        for (int i = 0; i < Y; i++) {
            String str=br.readLine();
            for (int j = 0; j < X; j++) {
                map[i][j]=str.charAt(j);
            }
        }

        v=new boolean[Y][X];

        for(int i=0;i<Y;i++){
            val=false;
            dfs(i,0);
        }
        System.out.println(answer);
    }

    // 방문 배열을 공유해야해서 참조형인 방문배열을 별도의 백트래킹으로 왔던길을 사용안함 처리를 하지 않음
    public static void dfs(int y,int x){
        if(val)return;

        for(int d=0;d<3;d++){
            int ny=y+dy[d];
            int nx=x+dx[d];

            if(val)return;

            if(0<=ny && ny<Y && 0<=nx && nx<X && !v[ny][nx] && map[ny][nx]!='x'){
                v[ny][nx]=true;

                if(nx==X-1){
                    answer+=1;
                    val=true;
                    return; // 여기에 return 넣는 이유는 3개로 갈 수 있는 경로에서 다음 경로로 가면 나머지 경로로 못가게
                }
                dfs(ny,nx);

            }
        }
    }
}
