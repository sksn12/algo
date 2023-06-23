import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static boolean[] val=new boolean[100001];
    static Queue<int[]> q=new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        // 현재 위치와 시간
        q.offer(new int[] {N,0});
        BFS();
    }

    private static void BFS() {
        while (!q.isEmpty()){
            int[] LT=q.poll();
            int L=LT[0];
            int T=LT[1];

            if(L>100000)continue;
             // bfs는 너비 우선 탐색이기 때문에 먼저 방문한 값이 최단시간임으로 뒤에 오는 경로를 무시해도 된다.
            if(val[L])continue;
            val[L]=true;

            if(L==K){
                System.out.println(T);
                break;
            }

            if(L-1>=0)q.offer(new int[] {L-1,T+1});
            q.offer(new int[] {L+1,T+1});
            q.offer(new int[] {L*2,T+1});
        }
    }
}
