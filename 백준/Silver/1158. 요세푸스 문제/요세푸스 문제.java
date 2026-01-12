import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        int[] map=new int[N+1];
        boolean[] v=new boolean[N+1];
        List<Integer> list=new ArrayList<>();

        for(int i=1;i<=N;i++)map[i]=i;

        int cnt=0;
        int L=1;
        while(true){

            // 모든 값이 들어갔으면 종료
            if(list.size()==N){
                break;
            }

            // 아직 현재 위치의 값이 나가지 않았다면 해당값은 카운트 or 정답에 넣거나
            if(!v[L]){
                cnt+=1;
                if(cnt==K){
                    cnt=0;
                    v[L]=true;
                    list.add(map[L]);
                }
            }

            if(L==N)L=1;
            else L+=1;

        }

        StringBuilder sb=new StringBuilder();
        sb.append("<");

        for(int i=0;i<list.size();i++){
            if(i==list.size()-1){
                sb.append(list.get(i)+">");
            }else{
                sb.append(list.get(i)+", ");
            }
        }

        System.out.println(sb);
    }
}
