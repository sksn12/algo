
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int L,C;
    static boolean[] sel;
    static List<String> map;
    static String[] mo={"a","e","i","o","u"};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        L=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        map=new ArrayList<>();
        sel=new boolean[C];

        String[] str=br.readLine().split(" ");
        for (int i = 0; i < C; i++) {
            map.add(str[i]);
        }

        Collections.sort(map);

        recursive(0,0);
    }

    private static void recursive(int idx, int k) {
        if(idx==C){
            if(k==L){
                List<String> sb=new ArrayList<>();
                int cnt1=0; // 모음갯수
                int cnt2=0; // 자음 갯수

                for (int i = 0; i < C; i++) {
                    if(sel[i]){
                        // 자음 인지 모음인지 검사
                        boolean val=false;
                        for (int j = 0; j <mo.length ; j++) {
                            if(map.get(i).equals(mo[j])){
                                cnt1+=1;
                                val=true;
                                break;
                            }
                        }

                        // 자음 이라면
                        if(!val)cnt2+=1;

                        // 일단 문자열에 담에 두기
                        sb.add(map.get(i));
                    }
                }


                // 모음 자음 갯수 체크 후 문자열 순서확인
                if(cnt1>=1 && cnt2>=2){

                    for (int i = 0; i < sb.size(); i++) {
                        System.out.print(sb.get(i));
                    }
                    System.out.println();
                }
            }
            return;
        }


        sel[idx]=true;
        recursive(idx+1,k+1);
        sel[idx]=false;
        recursive(idx+1,k);
    }
}
