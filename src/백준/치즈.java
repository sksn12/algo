package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 치즈 {
    static int Y,X;
    static int[][] map;
    static boolean[][] v;
    static Queue<int[]> q;
    static int[] dy={0,0,-1,1};
    static int[] dx={1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new int[Y][X];

        for(int i=0;i<Y;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<X;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int pre=counting();
        int T=0;

        // 0. 치즈가 아예 없을 경우 예외처리
        if(pre==0){
            System.out.println(T);
            System.out.println(pre);
            return;
        }

        while(true){
                T+=1;

                // 1. 초기화
                q=new LinkedList<>();
                v=new boolean[Y][X];
                q.offer(new int[] {0,0});
                v[0][0]=true;

                // 2. 공기층과 맞닿은 치즈 녹이기
                melting();

                // 3. 남은 치즈 갯수 카운팅
                int cnt=counting();

                if(cnt==0)break;

                pre=cnt;
        }

        System.out.println(T);
        System.out.println(pre);
    }

    public static int counting(){
        int cnt=0;

        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                if(map[i][j]==1)cnt+=1;
            }
        }

        return cnt;
    }

    public static void melting(){
        while(!q.isEmpty()){
            int[] yx=q.poll();

            for(int d=0;d<4;d++){
                int ny=dy[d]+yx[0];
                int nx=dx[d]+yx[1];

                if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx]){
                    v[ny][nx]=true;

                    // 전진하는 값이 0이면 치즈가 없다는 뜻임으로 q에 넣음, 치즈면 공기층과 만나서 녹아 q에 넣지 않음
                    // q에는 이전에 공기층이였던 값의 위치만 들어가니깐 해당 조건 성립
                    if(map[ny][nx]==0)q.offer(new int[] {ny,nx});
                    else if(map[ny][nx]==1)map[ny][nx]=0;
                }
            }
        }

        return;
    }
}
