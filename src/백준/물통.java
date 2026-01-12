package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 물통 {
    static int A,B,C;
    static Set<Integer> answer=new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        // C에서 A,B , A에서 B,C , B에서 A,C 6가지를 모두 보내봄
        // A가 비어있을때 C상태 값을 HashSet에 넣음(마지막에 오름차순 정렬)
        // 재귀 종료 조건은 방문배열 6개를 모두 방문했을시

        A=Integer.parseInt(st.nextToken());
        B=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        boolean[] v=new boolean[6];
        recursive(0,0,C,v);

    }

    private static void recursive(int a, int b, int c,boolean[] v) {
        boolean val=false;
        for (int i = 0; i < 6; i++) {
            if(!v[i])val=true;
        }

        // 모든 경우를 다 돌아 봤으면 재귀 종료
        if(!val){
            answer.add(c);
            return;
        }

        // C에서 A
        if(C>0){
            int AMove=c-A; // A에 들어갈 값
            
        }


    }
}
