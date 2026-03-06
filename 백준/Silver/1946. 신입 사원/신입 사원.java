
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T=Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            int N=Integer.parseInt(br.readLine());
            int[][] arr=new int[N][2];
            int answer=1;

            for (int i = 0; i < N; i++) {
                st=new StringTokenizer(br.readLine());
                arr[i][0]=Integer.parseInt(st.nextToken());
                arr[i][1]=Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr, (a,b) -> a[0] - b[0]);
            int count=1;
            int minInterview = arr[0][1];

            for(int i=1;i<N;i++){
                if(arr[i][1] < minInterview){
                    count++;
                    minInterview = arr[i][1];
                }
            }
            System.out.println(count);
        }

    }
}
