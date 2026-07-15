import java.util.*;
import java.io.*;

public class Main {
    static boolean[] v;
    static int[] path;
    static int[][] map;
    static int Y,X;
    static  int[] dy={0,1,0,-1};
    static int[] dx={1,0,-1,0};
    static int answer=Integer.MAX_VALUE;
    static List<int[]> list=new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());
        int R=Integer.parseInt(st.nextToken());

        map=new int[Y][X];

        for(int i=0;i<Y;i++){
            st=new StringTokenizer(br.readLine());

            for(int j=0;j<X;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        // 순열 (중복제거)
        v=new boolean[R];
        path=new int[R];

        for(int ir=0;ir<R;ir++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            list.add(new int[] {r,c,s});
        }

        recursive(0);
        System.out.println(answer);

    }

    public static void recursive(int level){
        if(level==list.size()){

            int[][] tmpMap=new int[Y][X];
            for(int i=0;i<Y;i++){
                for(int j=0;j<X;j++){
                    tmpMap[i][j]=map[i][j];
                }
            }
            rotate(path,tmpMap);
            return;
        }

        for(int i=0;i<list.size();i++){
            if(!v[i]){
                v[i]=true;
                path[level]=i;
                recursive(level+1);
                v[i]=false;
            }
        }
    }

    public static void rotate(int[] path,int[][] tmpMap){
        for(int ir=0;ir<path.length;ir++){
            int r=list.get(path[ir])[0];
            int c=list.get(path[ir])[1];
            int s=list.get(path[ir])[2];

            // 왼쪽 위, 오른쪽 아래
            int[] lt={r-s,c-s};
            int[] rb={r+s,c+s};

            int ys=lt[0]-1;
            int xs=lt[1]-1;
            int ye=rb[0];
            int xe=rb[1];

            // 시계 방향 회전 횟수
            int rr=Math.min((ye-ys),(xe-xs))/2;

            int[][] arr=new int[Y][X];
            for(int z=0;z<rr;z++){

                int y=ys+z;
                int x=xs+z;
                for(int d=0;d<4;d++){
                    while(true){
                        int ny=dy[d]+y;
                        int nx=dx[d]+x;

                        if(ys<=ny && xs<=nx && ny<ye && nx<xe && arr[ny][nx]==0){
                            arr[ny][nx]=tmpMap[y][x];
                            y=ny;
                            x=nx;
                        }else{
                            break;
                        }
                    }
                }
            }

            for(int i=0;i<Y;i++){
                for(int j=0;j<X;j++){
                    if(arr[i][j]!=0)tmpMap[i][j]=arr[i][j];
                }
            }
        }


        for(int i=0;i<Y;i++){
            int sum=0;
            for(int j=0;j<X;j++){
                sum+=tmpMap[i][j];
            }
            answer=Math.min(sum,answer);
        }
    }
}
