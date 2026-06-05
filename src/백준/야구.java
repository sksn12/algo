package 백준;

import java.io.*;
import java.util.*;

public class 야구 {
    static int N,answer=0;
    static int[][] map;
    static int[] arr={0,1,2,3,4,5,6,7};
    static int[] path=new int[8];
    static boolean[] v=new boolean[8];
    static int[][] score;
    static List<Integer> seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map=new int[N][9];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            for(int j=0;j<9;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }

            int tmp=map[i][3];
            map[i][3]=map[i][0];
            map[i][0]=tmp;
        }

        // 1. 4번을 제외한 모든 경우의 수를 만듬(순열로)
        recursive(0);

        System.out.println(answer);
    }

    public static void recursive(int level){
        if(level==path.length){
            seq=new ArrayList<>();
            score=new int[N][9];

            // 1-1. 4번타자 후 부턴 +1을 해서 타자 순서를 맞춰줌
            for(int i=0;i<path.length;i++){
                if(i==3)seq.add(3);

                if(path[i]>=3)seq.add(path[i]+1);
                else seq.add(path[i]);
            }

            // 1-2. 각 타자가 치는 점수 저장
            for(int i=0;i<N;i++){
                for(int j=0;j<9;j++){
                    score[i][j]=map[i][seq.get(j)];
                }
            }

            start();
            return;
        }

        for(int i=0;i<arr.length;i++){
            if(!v[i]){
                v[i]=true;
                path[level]=arr[i];
                recursive(level+1);
                v[i]=false;
            }
        }
    }

    // 2. 게임 시작
    public static void start(){
        int scoreSum = 0;
        int hitterIdx = 0;

        for (int inning = 0; inning < N; inning++) {
            int outCount = 0;
            boolean[] base = new boolean[3]; // 1루, 2루, 3루

            while (outCount < 3) {
                int result = score[inning][hitterIdx];

                switch (result) {
                    case 0:
                        outCount++;
                        break;
                    case 1:
                        if (base[2]) scoreSum++;
                        base[2] = base[1];
                        base[1] = base[0];
                        base[0] = true;
                        break;
                    case 2:
                        if (base[2]) scoreSum++;
                        if (base[1]) scoreSum++;
                        base[2] = base[0];
                        base[1] = true;
                        base[0] = false;
                        break;
                    case 3:
                        for (int i = 0; i < 3; i++) {
                            if (base[i]) scoreSum++;
                            base[i] = false;
                        }
                        base[2] = true;
                        break;
                    case 4:
                        for (int i = 0; i < 3; i++) {
                            if (base[i]) scoreSum++;
                            base[i] = false;
                        }
                        scoreSum++; // 본인 점수
                        break;
                }

                hitterIdx = (hitterIdx + 1) % 9;
            }
        }

        answer = Math.max(answer, scoreSum);
    }

}
