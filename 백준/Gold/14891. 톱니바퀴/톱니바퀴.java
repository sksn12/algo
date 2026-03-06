
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<int[]> list;
    static int[][] map=new int[5][8];


    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 4; i++) {
            String[] str=br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                map[i][j]=Integer.parseInt(str[j]);
            }
        }

        int K=Integer.parseInt(br.readLine());


        for (int i = 0; i < K; i++) {
            // 굴려야하는 톱니와 방향 추가
            list=new ArrayList<>();

            StringTokenizer st=new StringTokenizer(br.readLine());

            int N=Integer.parseInt(st.nextToken());
            int D=Integer.parseInt(st.nextToken());

            RightSearch(N,D);
            LeftSearch(N,D);

            // 현재 바퀴 먼저 굴리기
            if(D==1)TimeDirMove(N);
            else if(D==-1) OppTimeDirMove(N);


            for (int[] tmp:list) {
                if(tmp[1]==1)TimeDirMove(tmp[0]);
                else OppTimeDirMove(tmp[0]);
            }

        }

        int answer=0;
        if(map[1][0]==1)answer+=1;
        if(map[2][0]==1)answer+=2;
        if(map[3][0]==1)answer+=4;
        if(map[4][0]==1)answer+=8;
        System.out.println(answer);

    }

    private static void OppTimeDirMove(int n) {
        int tmp=map[n][0];
        for (int i = 1; i < 8; i++) {
            map[n][i-1]=map[n][i];
        }
        map[n][7]=tmp;
    }

    private static void TimeDirMove(int n) {
        int tmp=map[n][7];
        for (int i = 6; 0<=i ; i--) {
            map[n][i+1]=map[n][i];
        }
        map[n][0]=tmp;
    }

    private static void RightSearch(int n, int d) {
        while (n<4){
            if(map[n][2]==map[n+1][6])break;
            else{
                d=d==1?-1:1;
                list.add(new int[] {n+1,d});
                n=n+1;
            }
        }
    }

    private static void LeftSearch(int n, int d) {
        while (1<n){
            if(map[n][6]==map[n-1][2])break;
            else{
                d=d==1?-1:1;
                list.add(new int[] {n-1,d});
                n=n-1;
            }
        }
    }
}
