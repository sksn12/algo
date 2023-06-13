
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int NP=Integer.parseInt(st.nextToken());
        int P=Integer.parseInt(st.nextToken());

        if(N==0){
            System.out.println(1);
            return;
        }

        ArrayList<Integer> list=new ArrayList<>();

        st=new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.add(NP);
        Collections.sort(list,Collections.reverseOrder());

        int rank=0;
        int cnt=0;
        boolean found=false;

        for (int i = 0; i < list.size(); i++) {
            if(NP>list.get(i))break;
            cnt+=1;

            if(NP==list.get(i)){
                if (found)continue;
                else rank+=1;
                found=true;
            }else{
                rank+=1;
            }
        }

        if(cnt<=P){
            System.out.println(rank);
        }else{
            System.out.println(-1);
        }


    }
}
