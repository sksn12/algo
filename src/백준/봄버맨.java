package 백준;

import java.io.*;
import java.util.*;

public class 봄버맨 {
    static int Y,X,CNT;
    static String[][] map;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static Queue<int[]> q=new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        CNT = Integer.parseInt(st.nextToken());

        map = new String[Y][X];

        for (int i = 0; i < Y; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < X; j++) {
                map[i][j] = str[j];

                if (map[i][j].equals("O")) q.offer(new int[]{i, j});
            }
        }

        if(CNT==1){
            Print();
            return;
        }

        int cnt = 1;
        while (true){
            Hide();
            cnt+=1;
            if(cnt==CNT)break;
            Boom();
            cnt+=1;
            if(cnt==CNT)break;
        }

        Print();
    }

    public static void Boom(){
        while(!q.isEmpty()){
            int[] yx=q.poll();
            map[yx[0]][yx[1]]=".";

            for(int d=0;d<4;d++){
                int ny=yx[0]+dy[d];
                int nx=yx[1]+dx[d];

                if(0<=ny && 0<=nx && ny<Y && nx<X){
                    map[ny][nx]=".";
                }
            }
        }

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if (map[i][j].equals("O")) q.offer(new int[]{i, j});
            }
        }
    }

    public static void Hide(){
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                map[i][j]="O";
            }
        }
    }

    public static void Print(){
        for (int i = 0; i < Y; i++) {
            StringBuilder sb=new StringBuilder();
            for (int j = 0; j < X; j++) {
                sb.append(map[i][j]);
            }
            System.out.println(sb);
        }
    }
}
