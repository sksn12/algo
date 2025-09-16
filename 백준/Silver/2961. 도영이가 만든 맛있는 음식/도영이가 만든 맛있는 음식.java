import java.util.*;
import java.io.*;


public class Main {
    static int N;
    static List<int[]> taste=new ArrayList<>();
    static boolean[] select;
    static int answer=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        for(int n=0;n<N;n++){
            st=new StringTokenizer(br.readLine());

            // 신맛, 쓴맛
            int sour=Integer.parseInt(st.nextToken());
            int bitter=Integer.parseInt(st.nextToken());

            taste.add(new int[] {sour,bitter});
        }

        select=new boolean[N];
        recursive(0);

        System.out.println(answer);
    }

    public static void recursive(int level){
        if(level==N){
            int sourSum=1;
            int bitterSum=0;
            boolean val=false; // 공집합 제외하기 위해 사용

            for(int i=0;i<N;i++) {
                if (select[i]) {
                    int s = taste.get(i)[0];
                    int b = taste.get(i)[1];

                    sourSum *= s;
                    bitterSum += b;
                    val=true;
                }
            }

            if(val)answer=Math.min(Math.abs(sourSum-bitterSum),answer);

            return;
        }

        select[level]=true;
        recursive(level+1);
        select[level]=false;
        recursive(level+1);

    }
}

