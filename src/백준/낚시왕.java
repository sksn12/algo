package 백준;

import java.io.*;
import java.util.*;

public class 낚시왕 {
    static int Y,X,shark_cnt;
    static class shark{
        int y,x,s,d,size; // 위치,속력, 방향, 크기

        shark(int y,int x,int s,int d,int size){
            this.y=y;
            this.x=x;
            this.s=s;
            this.d=d;
            this.size=size;
        }
    }

    static shark[][] map;

    static class fishing_king{
        int y,x;

        fishing_king(int y,int x){
            this.y=y;
            this.x=x;
        }
    }

    static fishing_king fk=new fishing_king(0,-1);
    static int answer=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());
        shark_cnt=Integer.parseInt(st.nextToken());

        map=new shark[Y][X];

        // map 초기화
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                map[i][j]=new shark(i,j,0,0,0);
            }
        }

        // 0. map에 상어 정보 넣기
        for(int i=0;i<shark_cnt;i++){
            st=new StringTokenizer(br.readLine());

            int y=Integer.parseInt(st.nextToken());
            int x=Integer.parseInt(st.nextToken());
            int s=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());
            int size=Integer.parseInt(st.nextToken());

            map[y-1][x-1]=new shark(y-1,x-1,s, d,size);
        }

        // 사이클 반복
        for(int t=0;t<X;t++){
            // 1. 낚시왕 위치 이동
            fk=new fishing_king(fk.y,fk.x+1);

            // 2. 낚시왕 위치의 열 중 가장 가까운 상어 낚시
            for(int c=0;c<Y;c++){
                // 2.1 낚시왕 위치에서 상어 잡으면 해당 상어 잡기
                if(map[fk.y+c][fk.x].size!=0){
                    answer+=map[fk.y+c][fk.x].size;
                    map[fk.y+c][fk.x]=new shark(fk.y+c,fk.x,0,0,0);
                    break;
                }
            }
            // 3. 상어 이동
            move();

            System.out.println(t+" "+answer);

        }


    }


    public static void move(){
        shark[][] copyMap=new shark[Y][X];

        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                copyMap[i][j]=new shark(i,j,0,0,0);
            }
        }


        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                shark now_shark=map[i][j];

                if(now_shark.size!=0){
                    int ny=now_shark.y;
                    int nx=now_shark.x;
                    int first_d=now_shark.d;

                    // 상어의 속도만큼 상어 이동 위치를 구함
                    for(int c=0;c<now_shark.s;c++){
                        if(first_d==1){
                            ny-=1;

                            if(0<=ny){
                                continue;
                            }else{
                                first_d=2;
                                ny=0;
                                c-=1;
                            }

                        }else if(first_d==2){
                            ny+=1;

                            if(ny<Y){
                                continue;
                            }else{
                                first_d=1;
                                ny=Y-1;
                                c-=1;
                            }

                        }else if(first_d==3){
                            nx+=1;

                            if(nx<X){
                                continue;
                            }else{
                                first_d=4;
                                nx=X-1;
                                c-=1;
                            }

                        }else if(first_d==4){
                            nx-=1;

                            if(0<=nx){
                                continue;
                            }else{
                                first_d=3;
                                nx=0;
                                c-=1;
                            }
                        }
                    }

                    // 임시 배열에 상어가 있는경우 상어의 사이즈를 비교해서 큰값을 임시 배열에 넣음
                    if(copyMap[ny][nx].size!=0){
                        if(copyMap[ny][nx].size<now_shark.size){
                            copyMap[ny][nx]=new shark(ny,nx,now_shark.s,first_d,now_shark.size);
                        }
                    }else{
                        copyMap[ny][nx]=new shark(ny,nx,now_shark.s,first_d,now_shark.size);
                    }
                }
            }
        }

        // map을 copyMap으로 교체
        map=copyMap;
    }
}