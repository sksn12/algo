
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());

        int[] arr=new int[N];

        for (int i = 0; i < N; i++) {
            arr[i]=Integer.parseInt(br.readLine());
        }

        int answer=0;
        for (int i = N-1; 0<i ; i--) {
            if(arr[i]<=arr[i-1]){
                int down_level=arr[i-1]-arr[i]+1;
                arr[i-1]-=down_level;
                answer+=down_level;
            }
        }

        System.out.println(answer);
    }
}
