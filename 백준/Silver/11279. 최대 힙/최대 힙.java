
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node>{
        int x;

        Node(int x){
            this.x=x;
        }

        @Override
        public int compareTo(Node n){
            return n.x-this.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq=new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int z=Integer.parseInt(br.readLine());

            if(z==0){
                if(pq.size()==0) System.out.println(0);
                else System.out.println(pq.poll().x);
            }else{
                pq.offer(new Node(z));
            }
        }
    }

}
