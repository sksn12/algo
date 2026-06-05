
import java.io.*;
import java.util.*;

public class Main {
    static int[] map;
    static int[] sel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true){
            st= new StringTokenizer(br.readLine());

            int k=Integer.parseInt(st.nextToken());
            if(k==0)break;

            map=new int[k];
            sel=new int[6];
            for (int i = 0; i < k; i++) {
                map[i]=Integer.parseInt(st.nextToken());
            }

            recursive(0,0);
            System.out.println();
        }

    }

    public static void recursive(int level,int idx){
        if(level==6){
            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(sel[i]+" ");
            }
            System.out.println(sb.toString());
            return;
        }

        for (int i = idx; i < map.length; i++) {
            sel[level]=map[i];
            recursive(level+1,i+1);
        }
    }
}
