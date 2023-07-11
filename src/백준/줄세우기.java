package 백준;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(new FileInputStream("줄세우기.txt")));
        StringTokenizer st=new StringTokenizer(bf.readLine());

        int P=Integer.parseInt(st.nextToken());

        for (int i = 0; i < P; i++) {
            st=new StringTokenizer(bf.readLine()," ");

            int T=Integer.parseInt(st.nextToken());
            int[] map=new int[20];
            int[] sortMap=new int[20];

            for (int j = 0; j < 20; j++) {
                int tmp=Integer.parseInt(st.nextToken());
                map[j]=tmp;
                sortMap[j]=tmp;
            }

            Arrays.sort(sortMap);

            int sum=0;
            for (int j = 0; j < map.length; j++) {
                for (int k = 0; k <map.length ; k++) {
                    if(map[j]==sortMap[k] && j!=k){
                        sum+=Math.abs(k-j);
                    }
                }
            }

            System.out.print(T+" "+sum);
            System.out.println();
        }
    }
}
