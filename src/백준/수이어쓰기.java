package 백준;

import java.io.*;
import java.util.*;

public class 수이어쓰기 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String target = sc.next();  // 우리가 찾아야 할 문자열
        int idx = 0;                // target의 인덱스
        int num = 1;                // 현재 이어붙이는 숫자

        while (idx < target.length()) {
            String current = String.valueOf(num);
            for (int i = 0; i < current.length(); i++) {
                if (idx < target.length() && current.charAt(i) == target.charAt(idx)) {
                    idx++;  // 매칭되면 다음 문자로 이동
                }
            }

            System.out.println(num);
            num++;  // 다음 숫자로
        }

        System.out.println(num - 1);  // 마지막으로 추가된 숫자가 정답
    }
}

