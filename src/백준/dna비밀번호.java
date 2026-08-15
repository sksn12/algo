package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dna비밀번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int answer=0;

        String[] str=br.readLine().split("");

        st=new StringTokenizer(br.readLine());
        int[] totalCnt=new int[4];
        totalCnt[0]=Integer.parseInt(st.nextToken());
        totalCnt[1]=Integer.parseInt(st.nextToken());
        totalCnt[2]=Integer.parseInt(st.nextToken());
        totalCnt[3]=Integer.parseInt(st.nextToken());


        int p1=0;
        int p2=P-1;

        int[] cnt=new int[4];
        for (int i = 0; i < P; i++) {
            if(str[i].equals("A"))cnt[0]+=1;
            else if (str[i].equals("C"))cnt[1]+=1;
            else if(str[i].equals("G"))cnt[2]+=1;
            else if(str[i].equals("T"))cnt[3]+=1;
        }

        boolean val=false;
        for (int j = 0; j < 4; j++) {
            if(cnt[j]<totalCnt[j])val=true;
        }

        if(!val)answer+=1;


        L:for (int i = 0; i < S-P; i++) {
            if(str[p1].equals("A"))cnt[0]-=1;
            else if (str[p1].equals("C"))cnt[1]-=1;
            else if(str[p1].equals("G"))cnt[2]-=1;
            else if(str[p1].equals("T"))cnt[3]-=1;
            p1+=1;

            p2+=1;
            if(str[p2].equals("A"))cnt[0]+=1;
            else if (str[p2].equals("C"))cnt[1]+=1;
            else if(str[p2].equals("G"))cnt[2]+=1;
            else if(str[p2].equals("T"))cnt[3]+=1;

            for (int j = 0; j < 4; j++) {
                if(cnt[j]<totalCnt[j])continue L;
            }

            answer+=1;
        }

        System.out.println(answer);

    }
}
