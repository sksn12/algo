package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 타노스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] str=br.readLine().split("");

        if(str.length==2){
            if(str[0].equals(str[1])){
                System.out.println(str[0]);
                return;
            }else{
                System.out.println(str[0]+str[1]);
                return;
            }
        }

        int zero=0;
        int one=0;

        for (int i = 0; i < str.length; i++) {
            if(str[i].equals("1"))one+=1;
            else zero+=1;
        }

        int total=(zero/2)+(one/2);



        int tmp=Integer.MAX_VALUE;
        String answer="";

        L:for (int i = 0; i < str.length; i++) {
            String s=str[i];
            int one1=0;
            int zero1=0;
            if(str[i].equals("1"))one1+=1;
            else zero1+=1;

            for (int j = i+1; j < str.length; j++) {
                s+=str[j];

                if(str[j].equals("1"))one1+=1;
                else zero1+=1;

                if(s.length()>=total){
                    if(one1==one/2 && zero1==zero/2){
                        if(Integer.parseInt(s)<tmp){
                            tmp=Integer.parseInt(s);
                            answer=s;
                        }
                    }
                    continue L;
                }
            }
        }

        System.out.println(answer);
    }
}


// 모든 경우를 다 탐색해야함
// 문자열 S를 탐색하고 0의 갯수와 1의 갯수를 일단 세어줌
// 2중 for문으로 시작점을 정함
// 1의 갯수와 0의 갯수를 만족하면 stop, 총합을 넘어가면 pass
// 문자열을 정수로 변환하고 낮은값을 정답으로