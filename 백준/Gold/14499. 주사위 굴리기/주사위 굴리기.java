
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int Y,X,y,x,K;
    static int[][] dice=new int[4][3];
    static int[][] map;
    static int[] dy={0,0,-1,1}; //동 서 북 남
    static int[] dx={1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        y=Integer.parseInt(st.nextToken());
        x=Integer.parseInt(st.nextToken());

        K=Integer.parseInt(st.nextToken());

        map=new int[Y][X];

        for (int i = 0; i < Y; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        // 주사위 굴리기 시작
        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int L=Integer.parseInt(st.nextToken());

            // 바깥으로 이동 하려는 경우에는 pass
            int ny=y+dy[L-1];
            int nx=x+dx[L-1];

            if(0<=ny && 0<=nx && ny<Y && nx<X){
                if(L==1)moveR();
                else if(L==2)moveL();
                else if(L==3)moveU();
                else moveD();

                // 바깥으로 이동하지 않고 정상적으로 이동했을때만 y,x좌표 변환 // 아님 무시
                y=ny;
                x=nx;

                if(map[y][x]==0)map[y][x]=dice[1][1];
                else{
                    dice[1][1]=map[y][x];
                    map[y][x]=0;
                }

                System.out.println(dice[3][1]);
            }
        }

    }

    private static void moveL() {
        int pre=dice[3][1];
        int pre2=dice[1][0];
        int pre3=dice[1][2];
        int pre4=dice[1][1];

        dice[3][1]=pre3;
        dice[1][1]=pre2;
        dice[1][0]=pre;
        dice[1][2]=pre4;
    }

    private static void moveR() {
        int pre2=dice[1][1];
        int pre3=dice[1][0];
        int pre=dice[3][1];
        int pre4=dice[1][2];

        dice[1][2]=pre;
        dice[1][1]=pre4;
        dice[3][1]=pre3;
        dice[1][0]=pre2;
    }

    private static void moveD() {
        int pre1=dice[0][1];
        int pre2=dice[1][1];
        int pre3=dice[2][1];
        int pre4=dice[3][1];

        dice[3][1]=pre1;
        dice[0][1]=pre2;
        dice[1][1]=pre3;
        dice[2][1]=pre4;
    }

    private static void moveU() {
        int pre1=dice[1][1];
        int pre2=dice[2][1];
        int pre3=dice[0][1];
        int pre4=dice[3][1];

        dice[2][1]=pre1;
        dice[3][1]=pre2;
        dice[0][1]=pre4;
        dice[1][1]=pre3;
    }


}
