package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        if(N==1){
            System.out.println(0);
            return;
        }

        List<Integer> answer=new LinkedList<>();
        Stack<Integer> stackA=new Stack<>();
        Stack<Integer> stackB=new Stack<>();

        st=new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stackA.push(Integer.parseInt(st.nextToken()));
        }
        for (int i = N-1; 1<=i ; i--) {
            int now=stackA.pop();
            int pre=stackA.pop();

            if(pre<now){
                stackA.push(now);
                stackB.push(pre);
            }
            else{
                answer.add(i);
                stackA.push(pre);

                int size=stackB.size();
                for (int j = 0; j < size; j++) {
                    stackA.push(stackB.pop());
                    i+=1;
                }
            }

        }

        answer.add(0);
        int size=stackB.size();
        for (int j = 0; j < size; j++) {
            answer.add(0);
        }

        Collections.reverse(answer);

        StringBuilder sb=new StringBuilder();
        for (int x:answer) {
           sb.append(x+" ");
        }

        System.out.println(sb);
    }
}
