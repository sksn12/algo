package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백설공주와일곱난쟁이 {
    static int[] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        map=new int[9];
        boolean[] sel=new boolean[9];

        for (int i = 0; i < 9; i++) {
            map[i]=Integer.parseInt(br.readLine());
        }

        recursive(0,0,sel);

    }

    private static void recursive(int k, int idx, boolean[] sel) {
        if(idx==map.length){
            if(k==7){
                int sum=0;
                for (int i = 0; i < map.length; i++) {
                    if(sel[i]){
                        sum+=map[i];
                    }
                }

                if(sum==100){
                    for (int i = 0; i < map.length; i++) {
                        if(sel[i]){
                            System.out.println(map[i]);
                        }
                    }
                }
            }
            return;
        }

        sel[idx]=true;
        recursive(k+1,idx+1,sel);
        sel[idx]=false;
        recursive(k,idx+1,sel);
    }
}
