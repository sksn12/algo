import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int P=Integer.parseInt(st.nextToken());

        int[] arr=new int[N];

        st=new StringTokenizer(br.readLine());

        boolean val=false;
        for (int i = 0; i <N ; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
            if(arr[i]!=0)val=true; // 0 아닌 값이 하나라도 오면 sad아님
        }

        if(val){
            int sum=0;
            int cnt=1;
            int max=0;

            for (int j=0; j <P; j++) {
                sum+=arr[j];
            }

            max=sum;

            for (int i = 0; i < N-P; i++) {
                int p1=i;
                int p2=P+i;

                sum-=arr[p1];
                sum+=arr[p2];

                if(sum==max)cnt+=1;
                else if(sum>max){
                    max=sum;
                    cnt=1;
                }
            }

            System.out.println(max);
            System.out.println(cnt);
        }else{
            System.out.println("SAD");
        }

    }
}
