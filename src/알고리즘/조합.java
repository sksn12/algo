package 알고리즘;

public class 조합 {
    static int[] arr={1,3,5};

    public static void main(String[] args) {
            boolean[] sel=new boolean[3];
//            recursive(0,0,sel);

            System.out.println(Integer.parseInt("1100",2));
    }

    private static void recursive(int idx,int k ,boolean[] sel) {
        if(idx==arr.length){
            if(k==2){
                for (int i = 0; i < arr.length; i++) {
                    if(sel[i]) System.out.print(i+1);
                }
                System.out.println();
            }
            return;
        }

        sel[idx]=true;
        recursive(idx+1,k+1,sel);
        sel[idx]=false;
        recursive(idx+1,k,sel);
    }
}
