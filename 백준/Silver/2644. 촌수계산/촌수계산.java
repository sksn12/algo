
import java.io.*;
import java.util.*;

public class Main {
    static int N,M,F1,F2;
    static List<Integer>[] map;
    static class Node{
        int cnt;
        int pre;

        Node(int cnt,int pre){
            this.cnt=cnt;
            this.pre=pre;
        }
    }

    static Queue<Node> pq=new LinkedList<>();
    static boolean[] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st=new StringTokenizer(br.readLine());

        F1=Integer.parseInt(st.nextToken());
        F2=Integer.parseInt(st.nextToken());

        M=Integer.parseInt(br.readLine());

        map=new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            map[i]=new ArrayList<>();
        }

        v=new boolean[N+1];

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());

            int P=Integer.parseInt(st.nextToken());
            int C=Integer.parseInt(st.nextToken());

            map[P].add(C);
            map[C].add(P);
        }

        v[F1]=true;
        pq.offer(new Node(0,F1));

        System.out.println(BFS());
    }

    public static int BFS(){
        while(!pq.isEmpty()){
            Node n=pq.poll();

            for(int i=0;i<map[n.pre].size();i++){
                int next=map[n.pre].get(i);

                if(!v[next]){
                    if(next==F2)return n.cnt+1;

                    v[next]=true;
                    pq.offer(new Node(n.cnt+1,next));
                }

            }
        }

        return -1;
    }
}
