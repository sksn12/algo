import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer=0;
    static int[] countFix;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        int S=Integer.parseInt(st.nextToken());
        int P=Integer.parseInt(st.nextToken());

        String str=br.readLine();
        // A C G T 순서
        countFix =new int[4];
        int[] count =new int[4];
        st=new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < 4; i++) {
            countFix[i]=Integer.parseInt(st.nextToken());
        }


        // 첫번째 문자열에서 갯수 카운팅
        for (int i = 0; i < P; i++) {
            if(str.charAt(i)=='A')count[0]+=1;
            else if(str.charAt(i)=='C')count[1]+=1;
            else if(str.charAt(i)=='G')count[2]+=1;
            else if(str.charAt(i)=='T')count[3]+=1;
        }
        Conting(count);
        

        int p1=0;
        int p2=P;
        for (int i = 0; i < S-P; i++) {
            if(str.charAt(p1)=='A')count[0]-=1;
            else if(str.charAt(p1)=='C')count[1]-=1;
            else if(str.charAt(p1)=='G')count[2]-=1;
            else if(str.charAt(p1)=='T')count[3]-=1;

            if(str.charAt(p2)=='A')count[0]+=1;
            else if(str.charAt(p2)=='C')count[1]+=1;
            else if(str.charAt(p2)=='G')count[2]+=1;
            else if(str.charAt(p2)=='T')count[3]+=1;

            Conting(count);
            p1+=1;
            p2+=1;
        }

        System.out.println(answer);
    }

    private static void Conting(int[] count) {
        for (int i = 0; i < 4; i++) {
           if(count[i]<countFix[i])return;
        }
        answer+=1;
    }
}
