import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        String lower = s.toLowerCase(); // 전체 소문자로 먼저 처리

        boolean capitalize = true; // 단어의 첫 글자인지 여부

        for (char c : lower.toCharArray()) {
            if (capitalize && Character.isLetter(c)) {
                sb.append(Character.toUpperCase(c));
                capitalize = false;
            } else {
                sb.append(c);
                capitalize = (c == ' '); // 공백을 만나면 다음 문자는 단어의 첫 글자
            }
        }

        return sb.toString();
    }
}
