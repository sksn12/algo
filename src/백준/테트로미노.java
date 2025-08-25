package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 테트로미노 {
    static int answer=0;
    static int Y,X;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new int[Y][X];

        for (int i = 0; i < Y; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                // 1. ㅡ, ㅣ
                check(new int[][]{{i,j},{i,j+1},{i,j+2},{i,j+3}});
                check(new int[][]{{i,j},{i+1,j},{i+2,j},{i+3,j}});

                // 2. ㅁ
                check(new int[][]{{i,j},{i+1,j},{i,j+1},{i+1,j+1}});

                // 3~11. L자 회전 및 대칭 (8가지)
                check(new int[][]{{i,j},{i+1,j},{i+2,j},{i+2,j+1}});
                check(new int[][]{{i,j},{i,j+1},{i,j+2},{i+1,j}});
                check(new int[][]{{i,j},{i,j+1},{i+1,j+1},{i+2,j+1}});
                check(new int[][]{{i+1,j},{i+1,j+1},{i+1,j+2},{i,j+2}});
                check(new int[][]{{i,j+1},{i+1,j+1},{i+2,j+1},{i+2,j}});
                check(new int[][]{{i,j},{i+1,j},{i+1,j+1},{i+1,j+2}});
                check(new int[][]{{i,j},{i,j+1},{i+1,j},{i+2,j}});
                check(new int[][]{{i,j},{i,j+1},{i,j+2},{i+1,j+2}});

                // 12~15. S자 회전 및 대칭 (4가지)
                check(new int[][]{{i,j},{i,j+1},{i+1,j+1},{i+1,j+2}});
                check(new int[][]{{i,j+1},{i+1,j+1},{i+1,j},{i+2,j}});
                check(new int[][]{{i,j+1},{i,j+2},{i+1,j},{i+1,j+1}});
                check(new int[][]{{i,j},{i+1,j},{i+1,j+1},{i+2,j+1}});

                // 16~19. ㅗ자 4방향
                check(new int[][]{{i,j},{i,j+1},{i,j+2},{i+1,j+1}});
                check(new int[][]{{i,j+1},{i+1,j+1},{i+2,j+1},{i+1,j}});
                check(new int[][]{{i+1,j},{i+1,j+1},{i+1,j+2},{i,j+1}});
                check(new int[][]{{i,j},{i+1,j},{i+2,j},{i+1,j+1}});
            }
        }
        System.out.println(answer);
    }

    public static void check(int[][] arr){
        // 하나라도 범위를 벗어나면 탈락
        for (int i = 0; i < 4; i++) {
            if(arr[i][0] >= Y)return;
            else if (arr[i][1] >= X)return;
        }

        int sum=0;
        for (int i = 0; i < 4; i++) {
            int y=arr[i][0];
            int x=arr[i][1];

            sum+=map[y][x];
        }

        answer=Math.max(answer,sum);
    }

}
