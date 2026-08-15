import java.util.*;
import java.io.*;

public class Main {
    static int N,cnt;
    static List<Integer> list=new ArrayList<>();
    static int[][] map;
    static boolean[][] v;
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};
    static Queue<int[]> q;


    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        map=new int[N][N];
        v=new boolean[N][N];

        for(int i=0;i<N;i++){
            String[] str=br.readLine().split("");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(str[j]);
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==1 && !v[i][j]){
                    q=new LinkedList<>();
                    cnt=1;
                    v[i][j]=true;
                    q.offer(new int[]{i,j});
                    BFS();

                    list.add(cnt);
                }
            }
        }

        Collections.sort(list);

        System.out.println(list.size());

        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }

    public static void BFS(){
        while(!q.isEmpty()){
            int[] yx=q.poll();
            int y=yx[0];
            int x=yx[1];

            for(int d=0;d<4;d++){
                int ny=dy[d]+y;
                int nx=dx[d]+x;

                if(0<=ny && 0<=nx && ny<N && nx<N && !v[ny][nx] && map[ny][nx]==1){
                    v[ny][nx]=true;
                    cnt+=1;
                    q.offer(new int[] {ny,nx});
                }
            }
        }
    }
}
