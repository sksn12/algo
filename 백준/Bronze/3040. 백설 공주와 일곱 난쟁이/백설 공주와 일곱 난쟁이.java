import java.util.*;
import java.io.*;

public class Main {
    static int[] path=new int[7];
    static int[] arr=new int[9];

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<9;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        recursive(0,0);
    }

    public static void recursive(int start,int level){
        if(level==path.length){

            int sum=0;
            for(int i=0;i<path.length;i++){
                sum+=path[i];
            }

            if(sum==100){
                for(int i=0;i<path.length;i++){
                    System.out.println(path[i]);
                }
            }

            return;
        }

        for(int i=start;i<arr.length;i++){
            path[level]=arr[i];
            recursive(i+1,level+1);
        }
    }
}