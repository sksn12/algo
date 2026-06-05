package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 가희와키워드 {
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        HashMap<String,Integer> hashmap=new HashMap<>();

        for (int i = 0; i < N; i++) {
            hashmap.put(br.readLine(),1);
        }

        for (int i = 0; i < M; i++) {
            String[] str= br.readLine().split(",");


            for (int j = 0; j < str.length; j++) {
                int tmp = hashmap.getOrDefault(str[j],0);
                if(tmp==1){
                    hashmap.remove(str[j]);
                }
            }
            System.out.println(hashmap.size());

        }



    }
}
