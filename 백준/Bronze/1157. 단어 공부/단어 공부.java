import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.next().toUpperCase();

        HashMap<Character,Integer> hashMap=new HashMap<>();

        for (int i = 0; i <str.length() ; i++) {
            int num=hashMap.getOrDefault(str.charAt(i),0)+1;

            hashMap.put(str.charAt(i),num);
        }

        int max=0; // 0에는 값 1에는 max의 위치
        for (Character key:hashMap.keySet()){
           max= Math.max(max,hashMap.get(key));
        }

        Character Loc='?';
        for (Character key:hashMap.keySet()){
            if(hashMap.get(key)==max){
                if(Loc!='?'){
                    System.out.println('?');
                    return;
                }
                Loc=key;

            }
        }

        System.out.println(Loc);
    }
}
