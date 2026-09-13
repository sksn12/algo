package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경사로 {
    static int N,L,answer=0;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());

        map=new int[N][N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            // 1,0에서 1,1   1,2 이런식으로 가로축으로 갈거임
            DFS(i,0,false,1,map[i][0]);
            // 0,0에서 1,0   2,0 이런식으로 세로 축으로 갈거임
            DFS(0,i,true,1,map[0][i]);
        }

        System.out.println(answer);

    }

    // y,x, 세로(false)인지 가로(true)인지 ,현재 높이의 갯수, 현재 높이
    private static void DFS(int y,int x, boolean val, int cnt, int height) {
        // 가로축으로 전진 (y 고정)
        if(!val){
            if((x+1)>=N){ // 해당 가로축으로는 길이 놓아질 수 있음.
                answer+=1;
                System.out.println(map[y][x]+" "+y+" "+x);
                return;
            }

            if(height!=map[y][x+1]){ // 경사로를 놔야함
                // 이어지는 값들의 갯수가 L보다 작거나 현재와 다음 높이가 2이상 차이나 경사로를 놓을 수 없음
                if(cnt<L || Math.abs(height-map[y][x+1])>=2 )return;
                else DFS(y,x+1,val,1,map[y][x+1]); // 경사로를 놓아서 전진
            }else{
                if(map[y][x]==map[y][x+1])DFS(y,x+1,val,cnt+1,height);
                else DFS(y,x+1,val,1,map[y][x+1]);
            }
        }else{
            if((y+1)>=N){
                answer+=1;
                System.out.println(map[y][x]+" "+y+" "+x);
                return;
            }

            if(height!=map[y+1][x]){
                if(cnt<L || Math.abs(height-map[y+1][x])>=2 )return;
                else DFS(y+1,x,val,1,map[y+1][x]);
            }else{
                if(map[y][x]==map[y+1][x])DFS(y+1,x,val,cnt+1,height);
                else DFS(y+1,x,val,1,map[y+1][x]);
            }
        }

    }
}
