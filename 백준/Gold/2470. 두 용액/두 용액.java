
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());

        int[] map=new int[N];

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            map[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(map);

        int left = 0;
        int right = N - 1;

        int bestSum = Integer.MAX_VALUE;
        int A = 0, B = 0;

        while (left < right) {
            int sum = map[left] + map[right];
            if (Math.abs(sum) < bestSum) {
                bestSum = Math.abs(sum);
                A = map[left];
                B = map[right];
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(A + " " + B);

    }
}
