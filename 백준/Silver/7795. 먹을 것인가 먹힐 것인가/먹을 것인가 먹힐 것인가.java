
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T=Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st=new StringTokenizer(br.readLine());

            int N=Integer.parseInt(st.nextToken());
            int M=Integer.parseInt(st.nextToken());

            int[] A=new int[N];
            int[] B=new int[M];

            st=new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i]=Integer.parseInt(st.nextToken());
            }

            st=new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i]=Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A);
            Arrays.sort(B);

            int answer=0;
            L:for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(A[i]<=B[j])continue L;

                    answer+=1;
                }
            }

            System.out.println(answer);
        }
    }
}
