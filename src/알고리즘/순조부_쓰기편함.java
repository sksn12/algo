package 알고리즘;

import java.util.Arrays;

public class 순조부_쓰기편함 {
    static int[] arr={1,3,5,7};
    static int[] sel;
    static boolean[] v=new boolean[4];

    public static void main(String[] args) {
        sel=new int[3];

//        순열(0);

//        중복제거순열(0);

//        조합(0,0);

        부분집합(0);
    }

    public static void 부분집합(int level){
        if(level==arr.length){
            for (int i = 0; i < arr.length; i++) {
                if(v[i]){
                    System.out.printf(arr[i]+" ");
                }
            }
            System.out.println();
            return;
        }

        v[level]=true;
        부분집합(level+1);
        v[level]=false;
        부분집합(level+1);
    }

    public static void 조합(int start,int level){
        if(level== sel.length){
            System.out.println(Arrays.toString(sel));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            sel[level]=arr[i];
            조합(i+1,level+1);
        }
    }

    public static void 순열(int level){
        if(level==sel.length){
            System.out.println(Arrays.toString(sel));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            sel[level]=arr[i];
            순열(level+1);
        }
    }

    public static void 중복제거순열(int level){
        if(level==sel.length){
            System.out.println(Arrays.toString(sel));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(!v[i]){
                v[i]=true;
                sel[level]=arr[i];
                중복제거순열(level+1);
                v[i]=false;
            }
        }
    }
}
