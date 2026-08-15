import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());

        int answer=0;
        while (true) {
            if(N%5==0){
                answer+=N/5;
                break;
            }else if(N>0){
                N-=3;
                answer+=1;

                if(N==0)break;
            }else if(N<0){
                answer=-1;
                break;
            }
        }

        System.out.println(answer);
    }
}
