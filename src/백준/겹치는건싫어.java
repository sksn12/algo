package 백준;

import java.io.*;
import java.util.*;

public class 겹치는건싫어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        int[] number=new int[N];

        st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            number[i]=Integer.parseInt(st.nextToken());
        }

        int p1=0;
        int answer=0;
        int[] number_count=new int[100001];

        for (int p2 = 0; p2 < N; p2++) {
            number_count[number[p2]] += 1;

            while (number_count[number[p2]] > K) {
                number_count[number[p1]] -= 1;
                p1++;
            }

            answer = Math.max(answer, p2 - p1 + 1);
        }


        System.out.println(answer);
    }
}
