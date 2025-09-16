package 백준;

import java.io.*;
import java.util.*;

public class 숫자카드2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());

        HashMap<Integer,Integer> map=new HashMap<>();

        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int m=Integer.parseInt(st.nextToken());

            map.put(m,map.getOrDefault(m,0)+1);
        }

        int M=Integer.parseInt(br.readLine());

        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int m=Integer.parseInt(st.nextToken());

            System.out.printf(map.getOrDefault(m,0)+" ");
        }
    }
}
