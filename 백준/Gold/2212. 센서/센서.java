
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
        st=new StringTokenizer(br.readLine());
        int K=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());

        int[] map=new int[N];
        for (int i = 0; i < N; i++) {
            map[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(map);

        int[] d=new int[N-1];

        for (int i = 0; i < d.length; i++) {
            int dis=map[i+1]-map[i];

            d[i]=dis;
        }

        Arrays.sort(d);

        int sum=0;
        for (int i = 0; i < N-K; i++) {
            sum+=d[i];
        }

        System.out.println(sum);
    }
}
