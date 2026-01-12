package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 비밀번호발음하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Character[] mo={'a','e','i','o','u'};

        L:while (true){
            st=new StringTokenizer(br.readLine());
            String str=st.nextToken();
            if(str.equals("end")){
                return;
            }else{
                /*
                    1. 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
                    2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
                    3. 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
                 */

                boolean first=false;
                int moCnt=0;
                int jaCnt=0;

                for(int i=0;i<str.length();i++){
                    boolean nowMo=false;

                    // 3번 조건 실패
                    if(i>0){
                        Character preCharacter=str.charAt(i-1);
                        if(preCharacter==str.charAt(i) && (str.charAt(i)!='e' && str.charAt(i)!='o' )){
                            System.out.println("<"+str+">"+" is not acceptable.");
                            continue L;
                        }
                    }

                    for(int j=0;j<mo.length;j++){
                        if(str.charAt(i)==mo[j]){
                            first=true;
                            nowMo=true;
                            moCnt+=1;
                        }
                    }

                    if(nowMo){
                        jaCnt=0;
                    }else{
                        jaCnt+=1;
                        moCnt=0;
                    }

                    // 2번 조건 실패
                    if(jaCnt>=3 || moCnt>=3){
                        System.out.println("<"+str+">"+" is not acceptable.");
                        continue L;
                    }

                }

                if(!first){
                    System.out.println("<"+str+">"+" is not acceptable.");
                    continue L;
                }
                System.out.println("<"+str+">"+" is acceptable.");

            }
        }

    }
}
