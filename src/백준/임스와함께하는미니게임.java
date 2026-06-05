package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 임스와함께하는미니게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        String s=st.nextToken();
        int cnt=4;
        HashMap<String,Integer> map=new HashMap<>();

        if(s.equals("Y"))cnt=2;
        else if(s.equals("F"))cnt=3;

        for (int i = 0; i < N; i++) {
            String S=br.readLine();

            map.put(S,map.getOrDefault(S,0)+1);
        }

        int answer=0;
        int nowCnt=1;
        for (Map.Entry<String,Integer> m:map.entrySet()) {
            nowCnt+=1;
            if(nowCnt==cnt){
                answer+=1;
                nowCnt=1;
            }
        }
        System.out.println(answer);

    }
}
