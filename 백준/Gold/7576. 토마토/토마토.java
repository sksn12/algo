import java.util.*;
import java.io.*;

public class Main {
    static int X,Y;
    static int[][] map;
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};
    static Queue<int[]> q=new LinkedList<>();;


    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        X=Integer.parseInt(st.nextToken());
        Y=Integer.parseInt(st.nextToken());

        map=new int[Y][X];

        for(int i=0;i<Y;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<X;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        boolean t=false;
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                if(map[i][j]==0)t=true;
            }
        }

        if(!t){
            System.out.println(0);
            return;
        }

        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                if(map[i][j]==1)q.offer(new int[] {i,j,0});
            }
        }

        BFS();

        int answer=Integer.MIN_VALUE;


        boolean val=false;
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                if(val)continue;

                if(map[i][j]==0){
                    answer=-1;
                    val=true;
                    break;
                }else{
                    answer=Math.max(map[i][j],answer);
                }
            }
        }

        System.out.println(answer);
    }

    public static void BFS(){
        while(!q.isEmpty()){
            int[] yxc=q.poll();
            int y=yxc[0];
            int x=yxc[1];
            int cnt=yxc[2];

            for(int d=0;d<4;d++){
                int ny=dy[d]+y;
                int nx=dx[d]+x;

                if(0<=ny && 0<=nx && ny<Y && nx<X &&  map[ny][nx]==0){
                    map[ny][nx]=cnt+1;
                    q.offer(new int[] {ny,nx,cnt+1});
                }
            }
        }
    }
}
