package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 경로찾기 {
    static int N;
    static List<Integer>[] map;
    static int[][] answer;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        map=new List[N];
        answer=new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i]=new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int preNode=Integer.parseInt(st.nextToken());
                if(preNode==1)map[i].add(j);
            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.printf(i+"번째 노드에서 가는 길 : ");
//            for (Integer node:map[i]) {
//                System.out.printf(node+" ");
//            }
//            System.out.println();
//        }


        for (int i = 0; i < N; i++) {
            v=new boolean[N];

            for (int j = 0; j < map[i].size(); j++) {
                DFS(map[i].get(j));
            }

            for (int j = 0; j <v.length ; j++) {
                if(v[j])answer[i][j]=1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf(answer[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void DFS(int preNode) {
        if(v[preNode])return;

        v[preNode]=true;

        for (int i = 0; i < map[preNode].size(); i++) {
            DFS(map[preNode].get(i));
        }
    }
}
