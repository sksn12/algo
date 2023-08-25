
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int next;
        int w;

        Node(int next,int w){
            this.next=next;
            this.w=w;
        }

        @Override
        public int compareTo(Node n){
            return this.w-n.w;
        }
    }
    static int N,M,answer=0;
    static List<Node>[] map;
    static Queue<Node> pq=new PriorityQueue<>();
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(br.readLine());

        map=new List[N+1];
        v=new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            map[i]=new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());

            int n1=Integer.parseInt(st.nextToken());
            int n2=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());

            map[n1].add(new Node(n2,w));
            map[n2].add(new Node(n1,w));
        }

        v[1]=true;
        for (Node n:map[1]) {
            pq.offer(new Node(n.next,n.w));
        }

        Prim();

        System.out.println(answer);
    }

    private static void Prim() {
        while (!pq.isEmpty()){
            Node n=pq.poll();

            if(v[n.next])continue;

            v[n.next]=true;
            answer+=n.w;

            for (Node nn:map[n.next]) {
                if(!v[nn.next]){
                    pq.add(new Node(nn.next,nn.w));
                }
            }
        }

    }
}
