import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 단어 개수 입력
        int N = sc.nextInt();
        
        // 해시맵 및 단어 배열 초기화
        HashMap<String, Integer> wordIndex = new HashMap<>();
        String[] words = new String[N];
        
        // 단어 입력 및 해시맵에 단어 및 인덱스 저장
        for (int i = 0; i < N; i++) {
            String word = sc.next();
            wordIndex.put(word, i);
            words[i] = word;
        }
        
        // S와 T 초기화 및 최대 접두사 길이 초기화
        String S = "";
        String T = "";
        int maxPrefixLength = 0;
        
        // 모든 단어 쌍에 대한 접두사 비교
        for (int i = 0; i < N; i++) {
            String currentWord = words[i];
            for (int j = i + 1; j < N; j++) {
                String nextWord = words[j];
                int prefixLength = getPrefixLength(currentWord, nextWord);
                // 최대 접두사 길이 갱신 시 S와 T 업데이트
                if (prefixLength > maxPrefixLength) {
                    maxPrefixLength = prefixLength;
                    S = currentWord;
                    T = nextWord;
                }
            }
        }
        
        // 결과 출력
        System.out.println(S);
        System.out.println(T);
    }
    
    // 두 단어의 최대 접두사 길이 구하는 메소드
    private static int getPrefixLength(String word1, String word2) {
        int length = Math.min(word1.length(), word2.length());
        int prefixLength = 0;
        // 각 문자를 비교하며 접두사 길이 계산
        for (int i = 0; i < length; i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                prefixLength++;
            } else {
                break;
            }
        }
        return prefixLength;
    }
}
 
 