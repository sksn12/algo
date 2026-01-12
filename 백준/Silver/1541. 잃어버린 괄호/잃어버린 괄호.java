import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] str=br.readLine().split("-");

        int f=0;
        int n=0;

        for(int i=0;i<str.length;i++){
            String[] Str=str[i].split("\\+");
            
            if(i==0){
               for(int j=0;j<Str.length;j++){
                   f+=Integer.parseInt(Str[j]);
               }
            }else{
               for(int j=0;j<Str.length;j++){
                   n+=Integer.parseInt(Str[j]);
               }
            }
        }

        System.out.println(f-n);
    }
}
