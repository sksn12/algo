import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(bf.readLine());

        int N=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(bf.readLine());
        int[] nArr=new int[N];

        for (int i = 0; i < N; i++) {
            nArr[i]=Integer.parseInt(st.nextToken());
        }

        st=new StringTokenizer(bf.readLine());
        int M=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(bf.readLine());
        int[] mArr=new int[M];

        for (int i = 0; i < M; i++) {
            mArr[i]=Integer.parseInt(st.nextToken());
        }

        boolean[] valArr=new boolean[M];

        Arrays.sort(nArr);

        for (int i = 0; i < M; i++) {
            int mid=0;
            int low=0;
            int high=nArr.length-1;
            boolean val=false;

            while (low<=high){
                mid=(low+high)/2;

                if(nArr[mid]==mArr[i]){
                    System.out.println(1);
                    val=true;
                    break;
                }else if(nArr[mid]>mArr[i]){
                    high=mid-1;
                }else if(nArr[mid]<mArr[i]){
                    low=mid+1;
                }
            }

            if(!val)System.out.println(0);

        }

    }
}
