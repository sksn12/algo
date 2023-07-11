package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도영이가만든맛있는음식 {
    static int N;
    static int answer=Integer.MAX_VALUE;
    static int[] sida,sseda;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        sida=new int[N];
        sseda=new int[N];
        boolean[] sel=new boolean[N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine()," ");

            sida[i]=Integer.parseInt(st.nextToken());
            sseda[i]=Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            recursive(0,0,sel,i);
        }

        System.out.println(answer);

    }

    private static void recursive(int idx, int k, boolean[] sel,int cnt) {
        if(idx==sel.length){
            if(k==cnt){
                int sum1=1;
                int sum2=0;
                for (int i = 0; i < sel.length; i++) {
                    if(sel[i]){
                        sum1*=sida[i];
                        sum2+=sseda[i];
                    }
                }

                answer=Math.min(Math.abs(sum1-sum2),answer);
            }
            return;
        }

        sel[idx]=true;
        recursive(idx+1,k+1,sel,cnt);
        sel[idx]=false;
        recursive(idx+1,k,sel,cnt);
    }
}
