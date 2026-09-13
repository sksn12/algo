package 싸피11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 암호문1 {
    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= 1; tc++) {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st=new StringTokenizer(br.readLine());

            int N=Integer.parseInt(st.nextToken());

            String[] strArr=br.readLine().split(" ");

            st=new StringTokenizer(br.readLine());
            int M=Integer.parseInt(st.nextToken());

            String[] strBox=br.readLine().split("I");
            System.out.println(strBox[0]);

            L:for (int i = 0; i < strBox.length; i++) {
                String[] set=strBox[i].split(" ");
                System.out.println(set[0]);
                int Loc=Integer.parseInt(set[1]);

                for (int j = 0; j < Integer.parseInt(set[2]); j++) {
                    if(Loc>=10)continue L;

                    strArr[Loc]=set[j];
                    Loc+=1;
                }
            }

            System.out.printf("#"+tc+" ");
            for (int i = 0; i <strArr.length ; i++) {
                System.out.printf(strArr[i]+" ");
            }
            System.out.println();
        }
    }
}
