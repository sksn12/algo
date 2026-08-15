import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{
            int y=Math.abs(a);
            int x=Math.abs(b);

            if(y==x){
                return Integer.compare(a,b);
            }else{
                return Integer.compare(y,x);
            }
        });

        for(int n=0;n<N;n++){
            int v=Integer.parseInt(br.readLine());

            if(v==0){
                if(pq.size()==0)System.out.println(0);
                else System.out.println(pq.poll());
            }else{
                pq.offer(v);
            }
        }
    }
}
