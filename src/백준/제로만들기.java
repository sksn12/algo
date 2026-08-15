package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class 제로만들기 {
    static int N;
    static String[] cal={"+","-"," "}; // " "는 뒤에 값과 앞의 값을 먼저 합쳐주는거
    static String[] sel;
    static List<String> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N=Integer.parseInt(br.readLine());

            sel=new String[N-1];

            answer=new LinkedList<>();

            recursive(0);

            Collections.sort(answer);
            for (int j = 0; j < answer.size(); j++) {
                System.out.println(answer.get(j));
            }
            System.out.println();
        }


    }

    private static void recursive(int level) {
        if(level==sel.length){
            List<String> str=new LinkedList<>();
            List<String> str2=new LinkedList<>();
            str2.add("1");

            for (int i = 2; i <=N ; i++) {
                str2.add(sel[i-2]);
                str2.add(Integer.toString(i));
            }

            for (int i = 0; i < str2.size(); i++) {
                if(str2.get(i).equals(" "))continue;
                str.add(str2.get(i));
            }

            int sum=0;
            String s="";
            int i=0;

            // 처음 숫자 구하기
            while (true){
                if(i>=str.size())return;
                if(str.get(i)=="+" || str.get(i)=="-")break;
                s+=str.get(i);
                i+=1;
            }

            sum+=Integer.parseInt(s);
            s="";  // s초기화 && 부호 넣기
            s=str.get(i);

            for (int j = i+1; j < str.size(); j++) {
                if(str.get(j)=="+" || str.get(j)=="-"){
                    sum+=Integer.parseInt(s);
                    s="";
                    s=str.get(j);
                }else{
                    s+=str.get(j);
                }
            }

            // 마지막 값 sum에 추가
            sum+=Integer.parseInt(s);

            // 마지막에 0을 달성하면 ""부분을 띄어쓰기 하기
            if(sum==0){
                String ans="";
                for (int j = 0; j < str2.size(); j++) {
                    ans+=str2.get(j);
                }
                answer.add(ans);
            }
            return;
        }

        for (int i = 0; i < cal.length; i++) {
            sel[level]=cal[i];
            recursive(level+1);
        }
    }
}
