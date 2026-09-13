
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int Y=Integer.parseInt(st.nextToken());
        int X=Integer.parseInt(st.nextToken());

        String[][] map=new String[Y][X];

        for(int i=0;i<Y;i++){
            String[] str=br.readLine().split("");
            for(int j=0;j<X;j++){
                map[i][j]=str[j];
            }
        }

        int[][] answer=new int[Y][X];
        Queue<int[]> q=new LinkedList<>();

        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                if(map[i][j].equals("c")){
                    answer[i][j]=0;
                }else{
                    answer[i][j]=-1;
                }
            }
        }

        // 동쪽에 가까운 구름이 먼저 이동하게 queue에 넣어줌
        for(int i=0;i<Y;i++){
            for(int j=X-1;0<=j;j--){
                if(map[i][j].equals("c")){
                    q.offer(new int[] {i,j,1});
                }
            }
        }

        while(!q.isEmpty()){
            int[] yxp=q.poll();
            int y=yxp[0];
            int x=yxp[1];
            int p=yxp[2];

            int nx=x+1;
            if(nx<X && answer[y][nx]==-1){
                answer[y][nx]=p;
                q.offer(new int[] {y,nx,p+1});
            }

        }


        for(int i=0;i<Y;i++){
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<X;j++){
                sb.append(answer[i][j]+" ");
            }
            System.out.println(sb);
        }

    }
}
