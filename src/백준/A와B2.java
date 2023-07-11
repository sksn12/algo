package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A와B2 {
    static String S;
    static boolean val=false;

    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        S=br.readLine();
        String T= br.readLine();

        recursive(T);

        if(val){
            System.out.println("1");
        }else{
            System.out.println("0");
        }
    }

    private static void recursive(String t) {
        if(S.length()==t.length()){
            if(S.equals(t))val=true;
            return;
        }
        System.out.println(t);
        if(t.charAt(0)=='B'){
            String reverseStr="";
            for (int i = t.length()-1; 0<i  ; i--) {
                reverseStr+=String.valueOf(t.charAt(i));
            }
            recursive(reverseStr);
        }

        if(t.charAt(t.length()-1)=='A'){
            String str="";
            for (int i = 0; i<t.length()-1; i++) {
                str+=String.valueOf(t.charAt(i));
            }
            recursive(str);
        }

    }
}
