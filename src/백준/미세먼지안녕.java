package 백준;

import java.io.*;
import java.util.*;

public class 미세먼지안녕 {
    static int Y,X,T,TFY,BFY;
    static int[][] map;
    static int[][] copyMap;
    static int[] dy={0,-1,0,1};
    static int[] dx={1,0,-1,0};

    static int[] dy2={0,1,0,-1};
    static int[] dx2={1,0,-1,0};
    static Queue<int[]> q;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());
        T=Integer.parseInt(st.nextToken());

        map=new int[Y][X];

        boolean findAirFilter=false;

        for(int i=0;i<Y;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<X;j++){
                int num=Integer.parseInt(st.nextToken());

                // 공기 청정기 위치 저장
                if(num==-1 && !findAirFilter){
                    findAirFilter=true;
                    TFY=i;
                    BFY=i+1;
                }

                map[i][j]=num;
            }
        }

        for(int t=0;t<T;t++){
            // 1. 먼지 확산
            copyMap=new int[Y][X];
            move();

            // 2. 공기청정기 윗쪽 시계방향으로 돌리기
            q=new LinkedList<>();
            v=new boolean[Y][X];
            q.offer(new int[] {TFY,0});
            start_air_filter(true);

            // 2. 공기청정기 아래쪽 반시계방향으로 돌리기
            q=new LinkedList<>();
            v=new boolean[Y][X];
            q.offer(new int[] {BFY,0});
            start_air_filter(false);
        }
        print();
    }

    public static void start_air_filter(boolean top_or_bottom_val){
        for(int d=0;d<4;d++){
            int ny=0;
            int nx=0;

            while(!q.isEmpty()){
                int[] yx=q.poll();

                if(top_or_bottom_val){
                    ny=yx[0]+dy[d];
                    nx=yx[1]+dx[d];
                }else{
                    ny=yx[0]+dy2[d];
                    nx=yx[1]+dx2[d];
                }

                if(0<=ny && 0<=nx && ny<Y && nx<X && !v[ny][nx]){
                    if(map[ny][nx]==-1){
                        break;
                    }

                    // 이전값이 공기청정기 위치면 다음 위치에 0
                    if(map[yx[0]][yx[1]]==-1)copyMap[ny][nx]=0;
                    else{
                        copyMap[ny][nx]=map[yx[0]][yx[1]];
                    }

                    q.offer(new int[] {ny,nx});
                    v[ny][nx]=true;
                }
            }

            // 범위를 벗어나기 직전 위치에서 시작
            if(top_or_bottom_val){
                q.offer(new int[] {ny-dy[d],nx-dx[d]});
            }else{
                q.offer(new int[] {ny-dy2[d],nx-dx2[d]});
            }
        }

        copy();
    }

    public static void print(){
        int answer=0;
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++) {
                if(map[i][j]>0)answer+=map[i][j];
            }

        }
        System.out.println(answer);
    }

    public static void move(){
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                int cnt=0;

                if(map[i][j]>=5){
                    for(int d=0;d<4;d++){
                        int ny=i+dy[d];
                        int nx=j+dx[d];

                        if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx]!=-1){
                            copyMap[ny][nx]+=map[i][j]/5;
                            cnt+=1;
                        }
                    }

                    copyMap[i][j]+=map[i][j]-(map[i][j]/5)*cnt;
                }else{
                    copyMap[i][j]+=map[i][j];
                }
            }
        }

        copy();

        map[TFY][0]=-1;
        map[BFY][0]=-1;
    }

    public static void copy(){
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++) {
                map[i][j]=copyMap[i][j];
            }
        }
    }
}