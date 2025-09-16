package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 단어뒤집기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String[] str=br.readLine().split("");

        String answer="";

        boolean val=false;
        List<String> tmp=new ArrayList<>();

        for(int i=0;i<str.length;i++){
            if(str[i].equals("<"))val=true;
            else if (str[i].equals(">")){
                val=false;
                answer+=str[i];
                continue;
            }

            if(val){
                answer+=str[i];
                continue;
            }

            if(str[i].equals(" ") || i==str.length-1){
                for(int j=tmp.size()-1;0<=j;j--){
                    answer+=tmp.get(j);
                }
                answer+=" ";
                tmp=new ArrayList<>();
                continue;
            }

            tmp.add(str[i]);
        }


        System.out.println(answer);
    }
}
