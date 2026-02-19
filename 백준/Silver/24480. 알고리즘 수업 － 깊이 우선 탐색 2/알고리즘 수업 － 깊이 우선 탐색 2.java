
import java.io.*;
import java.util.*;

public class Main {
    static int N,M,R;
    static List[] graph;
    static boolean[] v;
    static int[] answer;
    static int number=1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());

        graph=new List[N+1];
        v=new boolean[N+1];
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
        answer[R]=number++;
        dfs(R);

        for (int i = 1; i <=N ; i++) {
            System.out.println(answer[i]);
        }
    }

    public static void dfs(int r){
        for (Object obj: graph[r]) {
            int next=(int)obj;

            if(!v[next]){
                v[next]=true;
                answer[next]=number++;
                dfs(next);
            }
        }
    }
}
