import java.io.*;
import java.util.*;

public class Main {
    static int[] number;
    static int[] operator;
    static boolean[] v;
    static int[] sel;
    static int N;
    static int max=Integer.MIN_VALUE;
    static int min=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());

        number=new int[N];
        operator=new int[N-1];
        v=new boolean[N-1];
        sel=new int[N-1];

        for(int i=0;i<N;i++){
            number[i]=Integer.parseInt(st.nextToken());
        }

        st=new StringTokenizer(br.readLine());

        int index=0;
        for(int i=0;i<4;i++){
            int cnt=Integer.parseInt(st.nextToken());

            for(int c=0;c<cnt;c++){
                if(i==0)operator[index++]=1; // +
                else if(i==1)operator[index++]=2; // -
                else if(i==2)operator[index++]=3; // X
                else if(i==3)operator[index++]=4; // %
            }
        }

        recursive(0);

        System.out.println(max);
        System.out.println(min);
    }

    public static void recursive(int level){
        if(level==N-1){
            cal();
            return;
        }

        for(int i=0;i<N-1;i++){
            if(!v[i]){
                v[i]=true;
                sel[level]=operator[i];
                recursive(level+1);
                v[i]=false;
            }
        }
    }

    public static void cal(){
        int sum=number[0];

        for(int i=0;i<N-1;i++){
            if(sel[i]==1)sum+=number[i+1];
            else if(sel[i]==2)sum-=number[i+1];
            else if(sel[i]==3)sum*=number[i+1];
            else{
                if (sum < 0)sum = -(-sum / number[i + 1]);
                else sum = sum / number[i + 1];
            }
        }

        max=Math.max(sum,max);
        min=Math.min(sum,min);
    }
}

