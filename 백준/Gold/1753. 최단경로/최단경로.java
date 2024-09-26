
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int n;
        int w;

        Node(int n,int w){
            this.n=n;
            this.w=w;
        }

        @Override
        public int compareTo(Node n){
            return this.w-n.w;
        }
    }
    static int N,M;
    static List<Node>[] map;
    static Queue<Node> q=new PriorityQueue<>();
    static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new List[N+1];
        d=new int[N+1];

        for (int i = 1; i <=N ; i++) {
            map[i]=new ArrayList<>();
        }

        Arrays.fill(d,Integer.MAX_VALUE);

        int s=Integer.parseInt(br.readLine());

        for (int i = 1; i <=M ; i++) {
            st=new StringTokenizer(br.readLine());

            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());

            map[n].add(new Node(m,w));
        }

        d[s]=0;
        q.offer(new Node(s,0));
        dijkstra();

        for (int i=1;i<d.length;i++) {
            if(d[i]==Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(d[i]);
        }
    }

    private static void dijkstra() {
        while (!q.isEmpty()){
            Node n=q.poll();

            for (Node now:map[n.n]) {
                if(d[now.n]>now.w+n.w){
                    d[now.n]=now.w+n.w;
                    q.offer(new Node(now.n,d[now.n]));
                }
            }
        }
    }
}
