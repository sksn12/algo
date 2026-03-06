
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq=new PriorityQueue<>();

        int answer=0;
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        while (!pq.isEmpty()){
            if(pq.size()==1)break;

            int n1=pq.poll();
            int n2=pq.poll();

            answer+=n1+n2;

            pq.offer(n1+n2);
        }
        System.out.println(answer);
    }
}
