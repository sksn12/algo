package 알고리즘;


import java.util.*;

public class 순조부 {

    // 트리 구조로 생각
    // 내가 고르려는 갯수(level) 까지만 고르고 출력

    static int arr[] = { 7,9,1,4 };
    static int node[];
    static boolean[] v=new boolean[4];
    static boolean[] select = new boolean[arr.length];
    static int num=0;

    // 순열
    static void dfs1(int level) {
        if(level==4){
            System.out.println(Arrays.toString(node));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            node[level]=arr[i];
            dfs1(level+1);
        }

    }

    // 순열(중복제거)
    static void dfs4(int level) {
        if(level==4){
            System.out.println(Arrays.toString(node));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(!v[i]){
                v[i]=true;
                node[level]=arr[i];
                dfs4(level+1);
                v[i]=false;
            }
        }

    }

    // 조합
    static void dfs2(int start, int level) {
        if(level==node.length){
            System.out.println(Arrays.toString(node));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            node[level]=arr[i];
            dfs2(i+1,level+1);
        }

    }

    // 부분 집합
    static void dfs3(int level) {

        if (level == arr.length) {
            System.out.print("지금 만들어진 부분집합 : { ");
            for (int i = 0; i < arr.length; i++) {
                if (select[i])
                    System.out.print(arr[i] + " ");
            }
            System.out.println("}");
            return;
        }
        select[level] = true;
        dfs3(level + 1);
        select[level] = false;
        dfs3(level + 1);

    }

    // 순열, 조합, 중복제거 순열은 다 한끗차이임
    public static void main(String[] args) {

        node = new int[4];

//         순열
        dfs1(0);
//        dfs2(0,0);

//        dfs3(0);
//        dfs4(0);
//        System.out.println(num);
    }

}