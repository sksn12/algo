package 백준;

import java.io.*;
import java.util.*;

public class 스도쿠 {
    static int[] arr={1,2,3,4,5,6,7,8,9};
    static int[] path=new int[9];
    static boolean[] v=new boolean[9];
    static int answer=0;
    static int[][] map=new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<9;i++){
            String[] str=br.readLine().split("");

            for(int j=0;j<9;j++){
                map[i][j]=Integer.parseInt(str[j]);
            }
        }

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(map[i][j]!=0){

                }
            }
        }

        recursive(0);
        System.out.println(answer);
    }

    public static void recursive(int level){
        if(level==9){
            answer+=1;
            return;
        }

        for(int i=0;i<arr.length;i++){
            if(!v[i]){
                v[i]=true;
                path[level]=arr[i];
                recursive(level+1);
                v[i]=false;
            }

        }
    }
}