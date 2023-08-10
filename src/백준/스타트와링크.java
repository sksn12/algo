package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스타트와링크 {
    static int N;
    static int answer=Integer.MAX_VALUE;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map=new int[N][N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        boolean[] sel=new boolean[N];
        recursive(0,0,sel);

        System.out.println(answer);
    }

    private static void recursive(int idx,int K,boolean[] sel) {
        // N개 중 N/2만큼 선택 후 sel배열에 true인 값이랑 false값으로 스타트팀,링크팀을 나눔
        if(idx==N){
           if(K==N/2){
               int[] start=new int[K];
               int[] Link=new int[K];

               boolean[] sel2=new boolean[K];
               int loc=0;
               int loc2=0;

               for (int i = 0; i <N; i++) {
                   if(sel[i])start[loc++]=i;
                   else Link[loc2++]=i;
               }

               int num1=recursive2(0,0,start,Link,sel2,0);
               int num2=recursive2(0,0,start,Link,sel2,1);

               answer=Math.min(answer,Math.abs(num1-num2));
           }
            return;
        }

        sel[idx]=true;
        recursive(idx+1,K+1,sel);
        sel[idx]=false;
        recursive(idx+1,K,sel);
    }

    private static int recursive2(int k, int idx, int[] start, int[] link, boolean[] sel2,int val) {
//        if(idx==sel2.length){
//            if(k==2){
//                int[] yx=new int[2];
//                int loc=0;
//
//                for (int i = 0; i < sel2.length; i++) {
//                    if(sel2[i]){
//                        // val==0이면 start 1이면 link
//                        if(val==0){
//                            yx[loc++]=start[];
//                        }else if (val==1){
//                            yx[loc++]=i;
//                        }
//                    }
//                }
//
//                int sum=map[yx[0]][yx[1]]+map[yx[1]][yx[0]];
//
//                return sum;
//            }
//            return Integer.MAX_VALUE;
//        }
//
//        sel2[idx]=true;
//        recursive2(k+1,idx+1,start,link,sel2,val);
//        sel2[idx]=false;
//        recursive2(k,idx+1,start,link,sel2,val);
//        return 0;
//    }
        return 0;
    }

}
