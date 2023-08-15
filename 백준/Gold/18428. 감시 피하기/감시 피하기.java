
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static String[][] map;
    static List<int[]> list=new ArrayList<>();
    static int[] path;
    static boolean[] v;
    static int[] dy={-1,0,1,0};
    static int[] dx={0,-1,0,1};
    static String answer="NO";

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map=new String[N][N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]=st.nextToken();
                if(map[i][j].equals("T"))list.add(new int[] {i,j});
            }
        }


        path=new int[3];
        v=new boolean[N*N];

        recursive(0,0);

        System.out.println(answer);
    }

    private static void recursive(int level, int start) {
        if(level==3){

            String[][] map2=new String[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map2[i][j]=map[i][j];
                }
            }

            for (int i = 0; i < 3; i++) {
                map2[path[i]/N][path[i]%N]="O";
            }

            if(answer.equals("NO"))Confirm(map2);

            return;
        }

        for (int i = start; i < N*N; i++) {
            if(!v[i] && (!map[i/N][i%N].equals("T") && !map[i/N][i%N].equals("S") )){
                v[i]=true;
                path[level]=i;
                recursive(level+1,start+1);
                v[i]=false;
            }
        }

    }

    private static void Confirm(String[][] map2) {
        boolean val=false;

        for (int[] yx:list){
            for (int d = 0; d < 4; d++) {
                int ny=yx[0];
                int nx=yx[1];

                while (true) {
                    ny = dy[d] + ny;
                    nx = dx[d] + nx;

                    if (0 <= ny && 0 <= nx && ny < N && nx < N && !map2[ny][nx].equals("O") ) {
                        if (map2[ny][nx].equals("S")) {
                            val = true;
                            return; // 학생 한명이라도 발견하면 요번 조합에서는 NO
                        }
                    } else break;
                }
            }
        }

        // 학생을 못찾는 경우
        if(!val){
            answer="YES";
        }
    }


}
