package 알고리즘;

public class 순열 {
    static int[] arr={1,2,3};

    public static void main(String[] args){
        recursive(new int[3],new boolean[arr.length],0);
    }

    private static void recursive(int[] sel, boolean[] visited, int idx) {
        if(idx==sel.length){
            for (int num:sel) {
                System.out.printf(num+" ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(!visited[i]){
                visited[i]=true;
                sel[idx]=arr[i];
                recursive(sel,visited,idx+1);
                visited[i]=false;
            }
        }

    }
}