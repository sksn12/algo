
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());

        PriorityQueue<Integer> q=new PriorityQueue<>((o1,o2)->{
            int num1=Math.abs(o1);
            int num2=Math.abs(o2);

            if(num1==num2){
                return o1-o2;
            }
            return num1-num2;
        });

        for (int i = 0; i < N; i++) {
            int tmp=Integer.parseInt(br.readLine());
            if(tmp==0){
                if(q.isEmpty()){
                    System.out.println(0);
                }else{
                    System.out.println(q.poll());
                }
            }else{
                q.add(tmp);
            }

        }



    }
}
