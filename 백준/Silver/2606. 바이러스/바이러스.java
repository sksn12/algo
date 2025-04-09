import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static boolean[][] map;
    static boolean[] v;
    static int answer=0;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        int Z=Integer.parseInt(st.nextToken());

        map=new boolean[N+1][N+1];
        v=new boolean[N+1];

        for(int z=0;z<Z;z++){
            st=new StringTokenizer(br.readLine());

            int x1=Integer.parseInt(st.nextToken());
            int x2=Integer.parseInt(st.nextToken());

            map[x1][x2]=true;
            map[x2][x1]=true;

        }

        dfs(1);

        System.out.println(answer);
    }

    public static void dfs(int node){
        v[node]=true;

        for(int i=1;i<=N;i++){
            if(!v[i] && map[node][i]){
                answer+=1;
                dfs(i);
            }
        }
    }
}
