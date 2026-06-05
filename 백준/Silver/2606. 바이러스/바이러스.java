
import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static Queue<Integer> q;
    static boolean[] v;
    static List[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(br.readLine());

        v=new boolean[N+1];
        q=new ArrayDeque<>();
        graph=new ArrayList[N+1];

        for (int i = 1; i <=N; i++) {
            graph[i]=new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());

            graph[n].add(b);
            graph[b].add(n);
        }

        q.add(1);
        v[1]=true;
        BFS();

        int answer=0;
        for (int i = 2; i <= N; i++) {
            if(v[i])answer+=1;
        }

        System.out.println(answer);
    }

    public static void BFS(){
        while (!q.isEmpty()){
            int node=q.poll();

            for (int i = 0; i < graph[node].size(); i++) {
                int next=(int)graph[node].get(i);
                if(!v[next]){
                    v[next]=true;
                    q.add(next);
                }
            }
        }
    }
}
