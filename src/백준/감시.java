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

                if(map[i][j]=='1')list.add(new int[] {1,i,j,1,0,0,0}); // 1번 cctv번호,2번 y,3번 x, 4번 부터는 방향 위 아 왼 오
                else if(map[i][j]=='2')list.add(new int[] {2,i,j,1,0});
                else if(map[i][j]=='3')list.add(new int[] {3,i,j,1,0,0,0});
                else if(map[i][j]=='4')list.add(new int[] {4,i,j,1,0,0,0});
                else if(map[i][j]=='5')list.add(new int[] {5,i,j});
            }
        }
        boolean[] sel=new boolean[list.size()];


        // cctv로 만들수 있는 조합의 갯수 선정
        // 하나의 cctv당 4가지 방향을 갖을 수 있음으로 모든 조합의 갯수는 4*cctv의 갯수 (cctv의 최대 갯수는 8)
        // 그러면 최대 2의 32제곱이 발생해 시간초과, 하지만, 5번 cctv는 4가지 방향을 다 고려하지 않아도됨(4방향 모두 같기 때문)
        // 2의 28정도도 시간초과가 발생하지만 1제곱에서 2제곱정도는 없어질거같음(2번 cctv도 2방향만 고려하면 되기때문) 하지만 8개의 cctv가 모두 1번이면 안될거같음
        // 정리 : cctv의 갯수를 리스트에 담아 해당 리스트의 크기만큼 방문배열을 만들어 조합을 짜 최소 크기를 구한다.

        // list size가 2고 1번 리스트에 1번 cctv가 있고 2번 리스트에 2번 cctv가 있는 경우 조합의 가짓수는 4*2=8(8개의 경우의 수 중 2개를 고르는 조합)
        recursive(0,0,list,sel);

    }

    private static void recursive(int idx, int k, List<int[]> list,boolean[] sel) {
        if(idx==list.size()){

            return;
        }

        sel[idx]=true;
        recursive(idx+1,k+1,list,sel);
        sel[idx]=false;
        recursive(idx+1,k,list,sel);
    }
}
