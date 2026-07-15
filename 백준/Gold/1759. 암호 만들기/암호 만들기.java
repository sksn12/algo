import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static String[] sel;
    static String[] arr;


    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        arr=new String[M];
        sel=new String[N];

        st=new StringTokenizer(br.readLine());
        for(int n=0;n<M;n++){
            arr[n]=st.nextToken();
        }
        Arrays.sort(arr);
        recursive(0,0);
    }

    public static void recursive(int level,int start){
        if(level==N){
            String[] tmp=new String[sel.length];

            for(int i=0;i<sel.length;i++){
                tmp[i]=sel[i];
            }

            Arrays.sort(tmp);

            for(int i=0;i<sel.length;i++){
                if(!tmp[i].equals(sel[i]))return;
            }

            String[] mo={"a", "e", "i", "o", "u"};

            int cntMo=0;
            int cntJa=0;

            for(int i=0;i<sel.length;i++){
                boolean val=false;

                for(int j=0;j<mo.length;j++){
                    if(mo[j].equals(sel[i])){
                        cntMo+=1;
                        val=true;
                    }
                }

                if(!val)cntJa+=1;
            }

            if(cntMo>0 && cntJa>1){
                for (String s : sel) {
                    System.out.print(s);
                }
                System.out.println();
            }
            return;
        }

        for(int i=start;i<M;i++){
            sel[level]=arr[i];
            recursive(level+1,i+1);
        }
    }
}
