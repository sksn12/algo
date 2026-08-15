
import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static class Node{
        int node;
        int depth;

        Node(int node,int depth){
            this.node=node;
            this.depth=depth;
        }
    }
    static List<Integer>[] map;
    static class Node2 implements Comparable<Node2>{
        int number;
        int total;

        Node2(int number,int total){
            this.number=number;
            this.total=total;
        }

        @Override
        public int compareTo(Node2 n){
            if(n.total==this.total)return this.number-n.number;

            return  this.total-n.total;
        }
    }

    static PriorityQueue<Node2> answer=new PriorityQueue<>();
    static boolean[] v;
    static Queue<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new List[N+1];

        for (int i = 0; i <= N; i++) {
            map[i]=new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());

            int n1=Integer.parseInt(st.nextToken());
            int n2=Integer.parseInt(st.nextToken());

            map[n1].add(n2);
            map[n2].add(n1);
        }

        for (int i = 1; i <=N ; i++) {
            v=new boolean[N+1];
            v[i]=true;
            q=new ArrayDeque<>();
            q.offer(new Node(i,0));

            BFS(i,0);
        }


        System.out.println(answer.poll().number);
    }

    public static void BFS(int number,int total){
        while (!q.isEmpty()){
            Node n=q.poll();

            for (int i = 0; i < map[n.node].size(); i++) {
                int next_node=map[n.node].get(i);

                if(!v[next_node]){
                    total+=n.depth+1;
                    v[next_node]=true;
                    q.offer(new Node(next_node,n.depth+1));
                }
            }
        }

        answer.offer(new Node2(number,total));
    }
}
