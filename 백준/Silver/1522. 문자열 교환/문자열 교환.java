
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine();

        int aCnt=0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)=='a')aCnt+=1;
        }

        int loop=-1;
        int Bcnt=0;
        int answer=0;

        // 맨처음 구간에서의 b의 갯수 카운팅
        for (int i = 0; i < aCnt; i++) {
            if(str.charAt(i)=='b'){
                Bcnt+=1;
                answer+=1;
            }
        }

        // 여기 for문은 슬라이딩 윈도우 구간 반복의 횟수(끝점까지 말고 이어져 있으니깐 돌아서 맨처음 값 전까지)
        for (int i = 1; i < str.length(); i++) {
            int p1=i;
            int p2=aCnt-1+i;

            // b가 나가면 -1, 들어오면 +1 , a는 카운트에 영향을 미치지 않음
            if (str.charAt(p1-1)=='b')Bcnt-=1;

            // 끝점이 문자열의 길이보다 커지면 맨 처음 값으로 가야함
            if(p2>=str.length()){
                loop+=1;
                p2=loop;
            }

            if (str.charAt(p2)=='b')Bcnt+=1;

            answer=Math.min(answer,Bcnt);
        }

        System.out.println(answer);
    }
}
