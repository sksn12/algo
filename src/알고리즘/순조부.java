package 알고리즘;


import java.util.*;

public class 순조부 {

    static int arr[] = { 1, 2, 3, 4 };
    static int path[];
    static boolean[] v=new boolean[4];
    static boolean[] select = new boolean[arr.length];
    static int num=0;

    // 순열
    static void dfs1(int level) {
        if(level==4){
            System.out.println(Arrays.toString(path));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            path[level]=arr[i];
            dfs1(level+1);
        }

    }

    // 순열(중복제거)
    static void dfs4(int level) {
        if(level==3){
            System.out.println(Arrays.toString(path));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(!v[i]){
                v[i]=true;
                path[level]=arr[i];
                dfs4(level+1);
                v[i]=false;
            }
        }

    }

    // 조합
    static void dfs2(int start, int level) {
        if(level==path.length){
            System.out.println(Arrays.toString(path));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            path[level]=arr[i];
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

    public static void main(String[] args) {

        path = new int[3];

//         순열
//        dfs1(0);
        dfs2(0,0);
//        dfs3(0);
//        dfs4(0);
        System.out.println(num);
    }

}