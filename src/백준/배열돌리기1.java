package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int Y,X,R;

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());

        int[][] Arr=new int[Y][X];

        for (int y = 0; y < Y; y++) {
            st=new StringTokenizer(br.readLine());
            for (int x = 0; x < X; x++) {
                Arr[y][x]=Integer.parseInt(st.nextToken());
            }
        }



        for (int r = 0; r < R; r++) {
            int[][] newArr=new int[Y][X];
            boolean[][] v=new boolean[Y][X];

            for (int i = 0; i < Y; i++) {
                for (int j = 0; j < X; j++) {
                    newArr[i][j]=Arr[i][j];
                }
            }

            for (int s = 0; s < Math.round(Y/2); s++) {

                int y=s,x=s;
                int ny=y+1,nx=x+1;


                for (int cnt = 0; cnt < Y; cnt++) {
                    if(ny<Y && !v[ny][x]){
                        newArr[ny][x]=Arr[y][x];
                        v[ny][x]=true;
                        y=ny;
                        ny+=1;
                    }
                }


                for (int cnt = 0; cnt < X; cnt++) {
                    if(nx<X && !v[y][nx]){
                        newArr[y][nx]=Arr[y][x];
                        v[y][nx]=true;
                        x=nx;
                        nx+=1;
                    }
                }

                ny-=2;
                for (int cnt = 0; cnt < Y; cnt++) {
                    if(0<=ny && !v[ny][x]){
                        newArr[ny][x]=Arr[y][x];
                        v[ny][x]=true;
                        y=ny;
                        ny-=1;
                    }
                }

                nx-=2;
                for (int cnt = 0; cnt < X; cnt++) {
                    if(0<=nx && !v[y][nx]){
                        newArr[y][nx]=Arr[y][x];
                        v[y][nx]=true;
                        x=nx;
                        nx-=1;
                    }
                }
            }



            Arr=newArr;

        }

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                System.out.printf(Arr[i][j]+" ");
            }
            System.out.println();
        }

    }
}
