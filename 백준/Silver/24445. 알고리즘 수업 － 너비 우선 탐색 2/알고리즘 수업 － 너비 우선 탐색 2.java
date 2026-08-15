
import java.io.*;
import java.util.*;

public class Main {
    static boolean[] v;
    static List[] graph;
    static int N,M,R;
    static Queue<Integer> q=new ArrayDeque<>();
    static int number=1;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());

        v=new boolean[N+1];
        graph=new List[N+1];
        answer=new int[N+1];

        for (int i = 1; i <=N ; i++) {
            graph[i]=new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <=N ; i++) {
            Collections.sort(graph[i],Collections.reverseOrder());
        }

        v[R]=true;
        q.offer(new Integer(R));
        BFS();

        for (int i = 1; i <=N ; i++) {
            System.out.println(answer[i]);
        }
    }

    public static void BFS(){
        while(!q.isEmpty()){
            int n=q.poll();
            answer[n]=number++;

            for (int i = 0; i < graph[n].size(); i++) {
                int next=(int) graph[n].get(i);
                if(!v[next]) {
                    v[next]=true;
                    q.offer(new Integer(next));
                }
            }

        }
    }
}
