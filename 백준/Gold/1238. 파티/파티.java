
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int d;
        int w;
        Node(int d,int w){
            this.d=d;
            this.w=w;
        }
        @Override
        public int compareTo(Node n){
            return this.w-n.w;
        }
    }
    static int N,M,X;
    static List<Node>[] map;
    static Queue<Node> q=new PriorityQueue<>();
    static int answer=Integer.MIN_VALUE;
    static int[] d,d2;


    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        map=new List[N+1];
        d=new int[N+1];
        d2=new int[N+1];

        for (int i = 0; i < map.length; i++) {
            map[i]=new LinkedList<>();
        }

        for (int i = 1; i <= M; i++) {
            st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());

            // 단방향
            map[n].add(new Node(d,w));
        }

        // 파티장에서 최단 거리 값들을 d2에 저장
        Arrays.fill(d,Integer.MAX_VALUE);
        q.offer(new Node(X,0));
        dijkstra();

        for (int i = 1; i <=N ; i++) {
            d2[i]=d[i];
        }

        q=new PriorityQueue<>();

        for (int i = 1; i <=N ; i++) {
            if(i==X)continue;

            Arrays.fill(d,Integer.MAX_VALUE);

            q.offer(new Node(i,0));
            dijkstra();

            answer=Math.max(d[X]+d2[i],answer);
        }

        System.out.println(answer);
    }

    private static void dijkstra() {
        while (!q.isEmpty()){
            Node now=q.poll();

            for (Node next:map[now.d]) {
                if(d[next.d]>(now.w+next.w)){
                    d[next.d]=now.w+next.w;
                    q.offer(new Node(next.d,d[next.d]));
                }
            }

        }
    }
}
