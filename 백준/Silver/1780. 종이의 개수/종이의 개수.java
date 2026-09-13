
import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int zero=0;
    static int minus=0;
    static int plus=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());

        map=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        recursive(0,N,0,N);

        System.out.println(minus);
        System.out.println(zero);
        System.out.println(plus);
    }

    public static void recursive(int sy,int ey,int sx,int ex){
        int first=map[sy][sx];
        boolean val=false;

        for (int i = sy; i < ey; i++) {
            for (int j = sx; j < ex; j++) {
                // 현재 탐색 구역 중, 하나라도 다른 값이 발견하면 9개의 구역으로 나눠줌
                if(first!=map[i][j]){
                    val=true;
                    break;
                }
            }
        }

        if(val){
            int size = ey - sy;
            int newSize = size / 3;

            // 9개의 영역으로 분할하여 재귀 호출
            // 행은 sy, sy + newSize, sy + 2*newSize 에서 시작
            // 열은 sx, sx + newSize, sx + 2*newSize 에서 시작

            // 1. 첫 번째 행 (행: sy ~ sy + newSize)
            recursive(sy, sy + newSize, sx, sx + newSize);                 // 좌상단
            recursive(sy, sy + newSize, sx + newSize, sx + 2 * newSize);   // 중상단
            recursive(sy, sy + newSize, sx + 2 * newSize, sx + 3 * newSize); // 우상단

            // 2. 두 번째 행 (행: sy + newSize ~ sy + 2*newSize)
            recursive(sy + newSize, sy + 2 * newSize, sx, sx + newSize);               // 좌중앙
            recursive(sy + newSize, sy + 2 * newSize, sx + newSize, sx + 2 * newSize);     // 정중앙
            recursive(sy + newSize, sy + 2 * newSize, sx + 2 * newSize, sx + 3 * newSize); // 우중앙

            // 3. 세 번째 행 (행: sy + 2*newSize ~ sy + 3*newSize)
            recursive(sy + 2 * newSize, sy + 3 * newSize, sx, sx + newSize);           // 좌하단
            recursive(sy + 2 * newSize, sy + 3 * newSize, sx + newSize, sx + 2 * newSize); // 중하단
            recursive(sy + 2 * newSize, sy + 3 * newSize, sx + 2 * newSize, sx + 3 * newSize); // 우하단

        }else{
            if(first==0)zero+=1;
            else if(first==-1)minus+=1;
            else if (first==1)plus+=1;
        }
    }
}
