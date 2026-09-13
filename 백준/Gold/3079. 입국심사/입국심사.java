import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        int[] map=new int[N];

        int max=0;
        for (int i = 0; i < N; i++) {
            map[i]=Integer.parseInt(br.readLine());
            max=Math.max(map[i],max);
        }

        long left=0L;
        long right=(max)*1000000000L;

        Long answer=0L;
        while (left<=right){
            long mid=(left+right)/2;

            Long cnt=0L;
            for (Integer i:map) {
                cnt+=mid/i;
                if(cnt>=M)break;
            }

            if(cnt>=M){
                answer=mid;
                right=mid-1;
            }else if(cnt<M){
                left=mid+1;
            }
        }

        System.out.println(answer);
    }

}
