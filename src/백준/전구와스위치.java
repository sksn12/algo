package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class 전구와스위치 {
    static int N;
    static String answer;
    static List<String> list=new LinkedList<>();
    static int cnt=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());

        String start=br.readLine();

        answer=br.readLine();

        list.add(start);
        dfs(start,0,0);
        System.out.println(cnt);
    }

    private static void dfs(String str, int level,int idx) {
        if(str.equals(answer)){
            cnt=Math.min(cnt,level);
            return;
        }

        // idx 위치에 따른 str변경
        String[] strArr=str.split("");

        // 현재 위치에서 왼쪽
        if(0<=(idx-1)){
            if(strArr[idx-1].equals("0"))strArr[idx-1]="1";
            else strArr[idx-1]="0";
        }

        // 현재 위치
        if(strArr[idx].equals("0"))strArr[idx]="1";
        else strArr[idx]="0";

        // 현재 위치에서 오른쪽
        if((idx+1)<N){
            if(strArr[idx+1].equals("0"))strArr[idx+1]="1";
            else strArr[idx+1]="0";
        }

        StringBuilder sb=new StringBuilder();
        for (String s:strArr) {
            sb.append(s);
        }

        // list에 현재 값이 있는지 확인하고 있으면 return 없으면 list에 추가
        for (String s:list) {
            if(s.equals(sb.toString()))return;
        }

        System.out.println(sb);
        list.add(sb.toString());

        for (int i = idx; 0<=i ; i--) {
            dfs(sb.toString(),level+1,i);
        }

        for (int i = idx+1; i<N ; i++) {
            dfs(sb.toString(),level+1,i);
        }
    }
}
