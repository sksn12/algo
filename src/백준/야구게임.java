package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 야구게임 {
    static int N,answer=Integer.MIN_VALUE;
    static int[][] map;
    static int[] path;
    static int[] sel;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        map=new int[N][9];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        path=new int[8];
        v=new boolean[8];
        recursive(0);

        System.out.println(answer);
    }

    private static void recursive(int level) {
        if(level==8){
            sel=new int[9];

            for (int i = 0; i < sel.length; i++) {
                if(i<3)sel[i]=path[i];
                else if(i==3)sel[i]=0;
                else sel[i]=path[i-1];
            }

            gameStart();
            return;
        }

        for (int i = 0; i < 8; i++) {
            if(!v[i]){
                v[i]=true;
                path[level]=i+1;
                recursive(level+1);
                v[i]=false;
            }
        }
    }

    private static void gameStart() {
        int sL=0;
        int sum=0;

        for (int i = 0; i < N; i++) {
            Queue<Integer> q=new ArrayDeque<>();
            q.add(0);
            q.add(0);
            q.add(0);

            int outCount=0;

            while (outCount<3){
                if(map[i][sel[sL]]==0)outCount+=1;
                else if (map[i][sel[sL]]==4) { // 홈런일때
                    for (int j = 0; j <3 ; j++) {
                        int tmp=q.poll();
                        if(tmp==1)sum+=1;
                    }
                    sum+=1;
                    q.add(0);
                    q.add(0);
                    q.add(0);
                }else{ // 1,2,3 안타 쳤을때
                    for(int j=0;j<map[i][sel[sL]];j++){
                        int tmp=q.poll();
                        if(tmp==1)sum+=1;
                        if(j==0)q.add(1); // 맨처음만 1이 들어감
                        else q.add(0);
                    }
                }


                sL+=1;
                if(sL==9)sL=0;
            }
        }

        answer=Math.max(answer,sum);
    }


}
