package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산 {
    static int Y,X,answer=0;
    static int[][] map,cntMap;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static Queue<int[]> q;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new int[Y][X];

        for(int i=0;i<Y;i++){
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        while (true){
            q=new LinkedList<>();
            v=new boolean[Y][X];

            int area=0;

            // BFS로 배열을 탐색하면서 영역이 2개이상으로 나눠져 있는지 확인
            for (int i = 0; i < Y; i++) {
                for (int j = 0; j < X; j++) {
                    if(map[i][j]!=0 && !v[i][j]){
                        area+=1;
                        q.offer(new int[] {i,j});
                        ConfrimnBFS();
                    }
                }
            }

            // area가 1보다 크다는건 영역이 2개이상으로 분리되었다는 뜻
            if(area>1){
                System.out.println(answer);
                break;
            }

            // 빙산이 다녹을때까지 분리되지 않으면 0출력
            boolean val=false;
            for (int i = 0; i < Y; i++) {
                for (int j = 0; j < X; j++) {
                    if(map[i][j]!=0)val=true;
                }
            }

            if(!val){
                System.out.println(0);
                break;
            }

            cntMap=new int[Y][X];

            for (int i = 0; i < Y; i++) {
                for (int j = 0; j < X; j++) {
                    int cnt=0;

                    if (map[i][j]!=0){
                        for (int d = 0; d < 4; d++) {
                            int ny=i+dy[d];
                            int nx=j+dx[d];

                            if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx]==0){
                                cnt+=1;
                            }
                        }
                    }

                    cntMap[i][j]=cnt;
                }
            }

            for (int i = 0; i < Y; i++) {
                for (int j = 0; j < X; j++) {
                    if(cntMap[i][j]!=0){
                        if(map[i][j]-cntMap[i][j]<0)map[i][j]=0;
                        else map[i][j]-=cntMap[i][j];
                    }
                }
            }

//            for (int i = 0; i < Y; i++) {
//                for (int j = 0; j < X; j++) {
//                    System.out.printf(map[i][j]+" ");
//                }
//                System.out.println();
//            }
//
//            System.out.println("----------------");

            answer+=1;

        }

        // 배열을 돌면서 0이 아닌 값들을 사방 탐색하고 사방탐색한 값들이 0이아니면 +1
        // 사방 탐색 후, 해당 자리에 사방 탐색이 가능했던 값들을 넣어줌

    }

    static public void ConfrimnBFS(){
        while (!q.isEmpty()){
            int[] yx=q.poll();
            int y=yx[0];
            int x=yx[1];

            v[y][x]=true;

            for (int d = 0; d < 4; d++) {
                int ny=y+dy[d];
                int nx=x+dx[d];

                if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx] && map[ny][nx]!=0){
                    v[ny][nx]=true;
                    q.offer(new int[] {ny,nx});
                }
            }
        }
    }
}
