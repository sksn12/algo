import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static List<int[]> chicken=new ArrayList<>();
    static List<int[]> home=new ArrayList<>();
    static int[] path;
    static int answer=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int k=Integer.parseInt(st.nextToken());

                if(k==1) home.add(new int[] {i,j});
                else if (k==2) chicken.add(new int[] {i,j});
            }
        }

        path=new int[M];
        recursive(0,0);

        System.out.println(answer);
    }

    public static void recursive(int start,int level){
        if(level==M){
            int sum=0;

            for(int i=0;i<home.size();i++){
                // 현재 집에서 가장 가까운 치킨 거리 찾기
                int nowD=Integer.MAX_VALUE;
                int hy=home.get(i)[0];
                int hx=home.get(i)[1];

                for(int j=0;j<path.length;j++){
                    int cy=chicken.get(path[j])[0];
                    int cx=chicken.get(path[j])[1];

                    int innerD=Math.abs(hy-cy)+Math.abs(hx-cx);
                    nowD=Math.min(nowD,innerD);
                }

                sum+=nowD;
            }

            answer=Math.min(answer,sum);
            return;
        }

        for(int i=start;i<chicken.size();i++){
            // 치킨집의 인덱스 번호 넣기
            path[level]=i;
            recursive(i+1,level+1);
        }
    }
}
