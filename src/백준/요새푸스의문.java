package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 요새푸스의문 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        List<Integer> list=new ArrayList<>();
        Queue<Integer> q=new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        int cnt=0;
        while (!q.isEmpty()){
            int tmp=q.poll();
            cnt+=1;

            if(cnt==K){
                list.add(tmp);
                cnt=0;
            }
            else q.offer(tmp);
        }

        System.out.printf("<");
        for (int i=0;i<list.size();i++){
            if(i!=list.size()-1)System.out.printf(list.get(i)+", ");
            else System.out.printf(list.get(i)+"");
        }
        System.out.printf(">");

    }
}
