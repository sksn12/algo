package 백준;

import java.io.*;
import java.util.*;

public class flyme {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T=Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st=new StringTokenizer(br.readLine());

            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());

            int answer=0;
            int pre_move=0;
            int refuse_block=y-x;

            while (true){
                if(refuse_block==0){
                    System.out.println(answer);
                    break;
                }

                answer+=1;

                int over_need_block_total=0;

                for (int i = 1; i <= pre_move+1; i++) {
                    over_need_block_total+=i;
                }

                if(over_need_block_total<=refuse_block){
                    pre_move+=1;
                }else{
                    int same_need_block_total=0;

                    for (int i = 1; i <= pre_move; i++) {
                        same_need_block_total+=i;
                    }

                    if(same_need_block_total>refuse_block){
                        pre_move-=1;
                    }
                }
                refuse_block-=pre_move;
            }
        }
    }
}
