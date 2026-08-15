package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class dfs와bfs {
    static List<Vertex>[] graph;
    static boolean[][] map;
    static StringBuilder sb=new StringBuilder();

    // 인접 행렬
    public static class Vertex implements Comparable<Vertex>{
        int Vertex;

        Vertex(int v){
            this.Vertex=v;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.Vertex,o.Vertex);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        // 4 5 1
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int V=Integer.parseInt(st.nextToken());

        // 정점의 갯수만큼 리스트의 크기 생성
        graph=new LinkedList[N+1];
        map=new boolean[N+1][N+1];

//         각 정점마다 List생성(각 정점마다의 간선의 갯수를 모르기 때문!)
        for (int i = 1; i <= N; i++) {
            graph[i]=new LinkedList<>();
        }

        for (int i = 1; i <= M; i++) {
            st=new StringTokenizer(br.readLine()," ");

            // 1 2
            int v1=Integer.parseInt(st.nextToken());
            int v2=Integer.parseInt(st.nextToken());

            graph[v1].add(new Vertex(v2));
            map[v1][v2]=true; // 양방향이기 때문에 대각선을 기준으로 값이 같다.
            map[v2][v1]=true;

        }

        boolean[] visited=new boolean[N+1];
        sb.append(V);
        visited[V]=true;
        DFS(V,visited);
        System.out.println(sb);

        sb=new StringBuilder();
        visited=new boolean[N+1];
        visited[V]=true;
        Queue<Integer> q=new LinkedList<>();
        q.add(V);
        sb.append(V);
        BFS(q,visited);
        System.out.println(sb);
    }

    private static void BFS(Queue<Integer> q,boolean[] visited) {
        while (!q.isEmpty()){
            int v=q.poll();

            for (int i = 1; i < visited.length; i++) {
                if(map[v][i] && !visited[i]){
                    visited[i]=true;
                    q.offer(i);
                    sb.append(" "+i);
                }
            }
        }
    }


    private static void DFS(int v,boolean[] visited) {
        // v는 현재 정점, i는 다음으로 갈 정점
        for (int i = 1; i < visited.length; i++) {
            if(map[v][i] && !visited[i]){
                sb.append(" "+i);
                visited[i]=true;
                DFS(i,visited);
            }
        }
    }


}
