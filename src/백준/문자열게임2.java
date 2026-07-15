package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문자열게임2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String str=br.readLine();
            int K=Integer.parseInt(br.readLine());

            if(K==1){
                System.out.println("1 1");
                continue;
            }

            int[] charCnt=new int[26];
            for (int i = 0; i < str.length(); i++) {
                int target=(str.charAt(i)-'0')-49;

                charCnt[target]+=1;
            }

            int max=Integer.MIN_VALUE;
            int min=Integer.MAX_VALUE;
            L:for (int i = 0; i < str.length(); i++) {
                if(charCnt[(str.charAt(i)-'0')-49] <K)continue L;

                int cnt=1;
                for (int j = i+1; j < str.length(); j++) {
                    if(str.charAt(i)== str.charAt(j))cnt+=1;
                    if(cnt==K){
                        max=Math.max(max,j-i+1);
                        min=Math.min(min,j-i+1);
                        continue L;
                    }
                }
            }

            if(min==Integer.MAX_VALUE){
                System.out.println(-1);
            }else{
                System.out.println(min+" "+max);
            }

        }
    }
}
