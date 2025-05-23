import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static List<List<Integer>> map;
    static boolean[] v;
    static int answer=0;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        int Z=Integer.parseInt(st.nextToken());

        map=new ArrayList<>();
        v=new boolean[N+1];

        for(int i=0;i<=N;i++){
            map.add(new ArrayList<>());
        }

        for(int z=0;z<Z;z++){
            st=new StringTokenizer(br.readLine());

            int x1=Integer.parseInt(st.nextToken());
            int x2=Integer.parseInt(st.nextToken());

            map.get(x1).add(x2);
            map.get(x2).add(x1);
            
        }

        dfs(1);

        System.out.println(answer);
    }

    public static void dfs(int node){
        v[node]=true;

        for(int j=0;j<map.get(node).size();j++){
            if(!v[map.get(node).get(j)]){
                answer += 1;
                dfs(map.get(node).get(j));
            }
        }
    }
}
