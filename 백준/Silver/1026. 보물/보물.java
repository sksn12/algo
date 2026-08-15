
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());

        List<Integer> list=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();

        for (int j = 0; j < 2; j++) {
            st=new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                if(j==0)list.add(Integer.parseInt(st.nextToken()));
                else list2.add(Integer.parseInt(st.nextToken()));
            }
        }


        Collections.sort(list);
        Collections.sort(list2,Collections.reverseOrder());

        int answer=0;
        for (int i = 0; i < N; i++) {
            answer+=list.get(i)*list2.get(i);
        }

        System.out.println(answer);
    }
}
