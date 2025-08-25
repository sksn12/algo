
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dy={0,1,1};
    static int[] dx={1,0,1};
    static int answer=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        // 현재 파이프 끝점의 y,x , 모양
        DFS(0,1,"vertical");

        System.out.println(answer);
    }

    public static void DFS(int Y,int X,String shape){
        if(Y==N-1 && X==N-1){
            answer+=1;
            return;
        }

        if(shape.equals("vertical")){
            vertical_val(Y,X);
            cross_line_val(Y,X);
        }else if(shape.equals("hor")){
            hor_val(Y,X);
            cross_line_val(Y,X);
        }else if(shape.equals("crossLine")){
            vertical_val(Y,X);
            hor_val(Y,X);
            cross_line_val(Y,X);
        }
    }

    public static void vertical_val(int Y,int X){
        int ny=dy[0]+Y;
        int nx=dx[0]+X;

        if(0<=ny && 0<=nx && ny<N && nx<N && map[ny][nx]==0){
            DFS(ny,nx,"vertical");
        }
    }

    public static void hor_val(int Y,int X){
        int ny=dy[1]+Y;
        int nx=dx[1]+X;

        if(0<=ny && 0<=nx && ny<N && nx<N && map[ny][nx]==0){
            DFS(ny,nx,"hor");
        }
    }

    public static void cross_line_val(int Y,int X){
        boolean check=false;
        for(int d=0;d<3;d++){
            int ny=dy[d]+Y;
            int nx=dx[d]+X;

            if(0<=ny && 0<=nx && ny<N && nx<N && map[ny][nx]==0){
            }else{
                check=true;
            }
        }

        if(!check){
            DFS(Y+dy[2],X+dx[2],"crossLine");
        }
    }
}
