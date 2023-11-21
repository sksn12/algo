
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] number;
    static String[] operator;
    static boolean[] v;
    static String[] sel;
    static int max=Integer.MIN_VALUE;
    static int min=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        number=new int[N];

        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            number[i]=Integer.parseInt(st.nextToken());
        }

        operator=new String[N-1];

        st=new StringTokenizer(br.readLine());
        int Loc=0;
        for (int i = 0; i < 4; i++) {
            int tmp=Integer.parseInt(st.nextToken());
            if(tmp>0){
                for (int j = 0; j < tmp; j++) {
                    if(i==0)operator[Loc]="+";
                    else if(i==1)operator[Loc]="-";
                    else if(i==2)operator[Loc]="X";
                    else if(i==3)operator[Loc]="%";

                    Loc+=1;
                }
            }
        }

        v=new boolean[N-1];
        sel=new String[N-1];

        recursive(0);
        System.out.println(max);
        System.out.println(min);
    }

    private static void recursive(int level) {
        if(level==N-1){
            int sum=number[0];
            int OL=0;
            for (int i = 1; i <number.length ; i++) {
                if(sel[OL].equals("+")){
                    sum+=number[i];
                }else if(sel[OL].equals("-")){
                    sum-=number[i];
                }else if(sel[OL].equals("X")){
                    sum*=number[i];
                }else if(sel[OL].equals("%")) {
                    double d = Math.floor(sum / number[i]);
                    sum = (int) d;
                }

                OL+=1;
            }

            max=Math.max(max,sum);
            min=Math.min(min,sum);

            return;
        }

        for (int i = 0; i < N-1; i++) {
            if (!v[i]) {
                v[i]=true;
                sel[level] = operator[i];
                recursive(level + 1);
                v[i]=false;
            }
        }

    }
}
