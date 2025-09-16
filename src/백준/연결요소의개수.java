package 백준;

import java.io.*;
import java.util.*;

public class 연결요소의개수 {
    static int N,M;
    static List<Integer>[] graph;
    static boolean[] v;
    static int answer=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        graph=new List[N+1];
        v=new boolean[N+1];

        for (int i = 0; i <=N ; i++) {
            graph[i]=new ArrayList();
        }

        for(int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());

            graph[n].add(m);
            graph[m].add(n);
        }

        for (int i = 1; i <=N ; i++) {
            if(!v[i]){
                v[i]=true;
                BFS(i);
                answer+=1;
            }
        }
        System.out.println(answer);
    }

    public static void BFS(int node){
        Queue<Integer> q=new ArrayDeque<>();
        q.offer(node);

        while (!q.isEmpty()){
            int n=q.poll();

            for (int i = 0; i < graph[n].size(); i++) {
               if(!v[graph[n].get(i)]){
                    v[graph[n].get(i)]=true;
                    q.offer(graph[n].get(i));
               }
            }
        }
    }
}