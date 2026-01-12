package 백준;


import java.io.*;
import java.util.*;

public class 결혼식 {
    static int N,M;
    static class Node {
        int n,depth;

        Node(int n,int depth){
            this.n=n;
            this.depth=depth;
        }
    }

    static List<Integer>[] graph;
    static boolean[] v;
    static int answer=0;
    static Queue<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(br.readLine());

        graph=new ArrayList[N+1];
        v=new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            graph[i]=new ArrayList<>();
        }

        for (int i = 0; i <M ; i++) {
            st=new StringTokenizer(br.readLine());

            int m=Integer.parseInt(st.nextToken());
            int m2=Integer.parseInt(st.nextToken());

            graph[m].add(m2);
            graph[m2].add(m);
        }

        q=new ArrayDeque<>();

        if(graph[1].size()>0){
            v[1]=true;
            q.offer(new Node(1,0));
            BFS();
        }

        System.out.println(answer);
    }

    public static void BFS(){
        while (!q.isEmpty()){
            Node n=q.poll();

            if(n.depth<2){
                for(int i=0;i<graph[n.n].size();i++){
                    if(!v[graph[n.n].get(i)]){
                        v[graph[n.n].get(i)]=true;
                        q.offer(new Node(graph[n.n].get(i),n.depth+1));
                        answer+=1;
                    }
                }
            }
        }
    }
}
