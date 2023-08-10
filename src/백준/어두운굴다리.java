package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 어두운굴다리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        int M=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());

        int[] Loc=new int[M];
        for (int i = 0; i < M; i++) {
            Loc[i]=Integer.parseInt(st.nextToken());
        }

        int H=1;
        boolean[] check;
        while (true){
            check=new boolean[N+1];

            for (int i = 0; i <M ; i++) {

                // 현재 위치에서 높이만큼 왼쪽
                for (int j = 0; j <H ; j++) {
                    int leftLoc=Loc[i]-H<0?0:Loc[i]-H;
                    check[leftLoc]=true;
                }


                // 현재 위치에서 높이만큼 오른쪽
                for (int j = 0; j <H ; j++) {
                    int rightLoc=Loc[i]+H>=N?N:Loc[i]+H;
                    check[rightLoc]=true;
                }

            }

            boolean val=false;
            for (int i = 0; i < check.length; i++) {
                if(!check[i]){
                    val=true;
                    break;
                }
            }

            if(!val){
                System.out.println(H);
                break;
            }
            H+=1;
        }

    }
}
