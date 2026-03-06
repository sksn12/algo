
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());

            int z=Integer.parseInt(st.nextToken());
            int z2=Integer.parseInt(st.nextToken());
            
            // 두 값의 차이가 양수면 해당 입찰은 낙찰 확정임으로 K를 빼줌
            if( (z-z2) >= 0 )K-=1;
            else{
                list.add(Math.abs(z-z2));
            }
        }

        // 두값의 차이를 오름차순으로 정렬해서, K만큼 반복하면 최대 필요한 입찰가격이 나옴 (정렬을 해놓기 때문에)
        Collections.sort(list);

        int answer=0;
        for (int k = 0; k < K; k++) {
            answer=list.get(k);
        }

        System.out.println(answer);
    }
}
