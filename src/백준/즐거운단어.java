package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 즐거운단어 {
    static String[] mo={"A","E","I","O","U"};
    static int[] sel;
    static int answer=0;
    static String[] str;
    // _의 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        str=br.readLine().split("");

        int len=0;
        for (int i=0;i<str.length;i++) {
            if(str[i].equals("_"))len+=1;
        }

        // _의 갯수
        sel=new int[len];

        recursive(0);
        System.out.println(answer);
    }

    private static void recursive(int level) {
        if(level==sel.length){
            int cnt=0;

            //원본 str하나 복사하면서 _자리에는 현재 순열의 알파벳 넣기
            String[] TmpStr=new String[str.length];
            for (int i = 0; i < str.length; i++) {
                TmpStr[i]=str[i];
                if(TmpStr[i].equals("_")){
                    TmpStr[i]=Character.toString((char)sel[cnt]);
                    cnt+=1;
                }
            }

            boolean val=false; // L포함하는지
            for (String s:TmpStr) {
                if(s.equals("L"))val=true;
            }

            if(!val)return;

            int moCnt=0;
            int jaCnt=0;

            L:for (int i = 0; i < TmpStr.length; i++) {

                for (String s:mo) {
                    if(TmpStr[i].equals(s)){
                        moCnt+=1;
                        jaCnt=0;
                        if(moCnt>=3)return;
                        continue L;
                    }
                }


                // 현재 모음이 아니면 여기로 옴 (자음일때)
                moCnt=0;
                jaCnt+=1;
                if (jaCnt>=3)return;
            }

            answer+=1;

            return;
        }

        for (int i = 65; i <=90 ; i++) {
            sel[level]=i;
            recursive(level+1);
        }
    }
}
