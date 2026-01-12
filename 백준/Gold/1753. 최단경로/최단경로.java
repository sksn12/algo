
import java.io.*;
import java.util.*;

public class Main {
    static int V,E;

    static class Node implements Comparable<Node>{
        int next;
        int cost;

        Node(int next,int cost){
            this.next=next;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node n){
            return this.cost-n.cost;
        }
    }

    static Queue<Node> q=new PriorityQueue<>();
    static int[] answer;
    static List<Node>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        int S=Integer.parseInt(br.readLine());

        map=new List[V+1];

        for(int i=0;i<=V;i++){
            map[i]=new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine());

            int n1=Integer.parseInt(st.nextToken());
            int n2=Integer.parseInt(st.nextToken());
            int cost=Integer.parseInt(st.nextToken());

            map[n1].add(new Node(n2,cost));
        }

        answer=new int[V+1];
        Arrays.fill(answer,Integer.MAX_VALUE);
        q.offer(new Node(S,0));
        answer[S]=0;
        dij();

        for(int i=1;i<=V;i++){
            if(answer[i]==Integer.MAX_VALUE){
                System.out.println("INF");
            }else{
                System.out.println(answer[i]);
            }
        }
    }

    public static void dij(){
        while(!q.isEmpty()){
            Node preNode=q.poll();

            for(int i=0;i<map[preNode.next].size();i++){
                Node nextNode=map[preNode.next].get(i);

                if(preNode.cost+nextNode.cost<answer[nextNode.next]){
                    answer[nextNode.next]=preNode.cost+nextNode.cost;
                    q.offer(new Node(nextNode.next,preNode.cost+nextNode.cost));
                }
            }
        }
    }
}