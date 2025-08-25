package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 듣보잡 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        HashMap<String,Integer> hashMap=new HashMap<>();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            hashMap.put(st.nextToken(), 1);
        }

        List<String> answer=new ArrayList<>();

        for (int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());

            String str=st.nextToken();
            Integer cnt=hashMap.getOrDefault(str,0);

            if(cnt>0)answer.add(str);
        }

        Collections.sort(answer);

        System.out.println(answer.size());
        for(String s:answer){
            System.out.println(s);
        }
    }
}
