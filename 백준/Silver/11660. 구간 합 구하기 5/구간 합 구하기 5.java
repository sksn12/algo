
import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st=new StringTokenizer(br.readLine());
            int pre=0;
            for (int j = 1; j <= N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken())+pre;
                pre=map[i][j];
            }
        }

        for (int t = 0; t < M; t++) {
            st=new StringTokenizer(br.readLine());

            int x1=Integer.parseInt(st.nextToken());
            int y1=Integer.parseInt(st.nextToken());
            int x2=Integer.parseInt(st.nextToken());
            int y2=Integer.parseInt(st.nextToken());

            print(x1,y1,x2,y2);
        }
    }

    public static void print(int x1,int y1,int x2,int y2){
        int sum=0;
        for (int x = x1; x <= x2; x++) {
                sum+=map[x][y2]-map[x][y1-1];
        }

        System.out.println(sum);
    }
}
