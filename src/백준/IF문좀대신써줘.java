package 백준;

import java.io.*;
import java.util.*;

public class IF문좀대신써줘 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        String[] name=new String[N];
        int[] attack_level=new int[N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            name[i]=st.nextToken();
            attack_level[i]=Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            int m=Integer.parseInt(br.readLine());
            int Loc=binarySearch(attack_level,m);
            System.out.println(name[Loc]);
        }
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int Loc=0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (target <= arr[mid]) {
                Loc=mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return Loc;
    }
}
