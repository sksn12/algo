package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 요세푸스문제 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        StringBuilder sb=new StringBuilder();
        Queue<Integer> q=new LinkedList<>();

        for (int i = 0; i < N; i++)q.add(i+1);

        sb.append("<");
        while (1<q.size()){
            for (int i = 0; i < M-1; i++) {
                q.add(q.poll());
            }
            sb.append(q.poll()+", ");
        }

        sb.append(q.poll()+">");

        System.out.println(sb);
    }
}
