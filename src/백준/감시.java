package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 감시 {
    static int Y,X;
    static char[][] map;
    static ArrayList<Integer> cctv=new ArrayList<>();
    static int[] dy={-1,1,0,0}; // 위아왼오
    static int[] dx={0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new char[Y][X];
        List<int[]> list=new ArrayList<>();

        for (int i = 0; i < Y; i++) {
            String str=br.readLine();
            for (int j = 0; j < X; j++) {
                map[i][j]=str.charAt(j);


            }
        }

    }


    // cctv의 번호는 배열로
    // 각 cctv의 방향은 배멸 + list로
    // 2번에서의 list를 갖고 조합!!
    // 모든 경우 찾기!

}
