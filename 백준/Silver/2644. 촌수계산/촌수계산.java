import java.util.*;
import java.io.*;

public class Main{
    static int N,F1,F2;
    static boolean[][] map;
    static boolean[] v;
    static int answer=-1;
    static int cnt=0;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        F1=Integer.parseInt(st.nextToken());
        F2=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        int Z=Integer.parseInt(st.nextToken());

        map=new boolean[N][N];
        v=new boolean[N];

        for(int z=0;z<Z;z++){
            st=new StringTokenizer(br.readLine());

            int x1=Integer.parseInt(st.nextToken());
            int x2=Integer.parseInt(st.nextToken());

            map[x1-1][x2-1]=true;
            map[x2-1][x1-1]=true;

        }

        dfs(F1-1);

        System.out.println(answer);
    }

    public static void dfs(int node){
        if(answer!=-1)return;

        if(node==F2-1){
            answer=cnt;
            return;
        }

        v[node]=true;

        for(int i=0;i<N;i++){
            if(!v[i] && map[node][i]){
                cnt+=1;
                dfs(i);
                cnt-=1;
                
            }
        }
    }
}
