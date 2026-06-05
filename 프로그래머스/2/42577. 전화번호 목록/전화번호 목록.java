import java.util.HashMap;

class Solution {
    public boolean solution(String[] phone_book) {
        // HashMap에 모든 번호를 넣기
        HashMap<String, Boolean> map = new HashMap<>();
        for (String number : phone_book) {
            map.put(number, true);
        }

        // 각 번호의 접두어가 HashMap에 있는지 확인
        for (String number : phone_book) {
            for (int i = 1; i < number.length(); i++) {
                String prefix = number.substring(0, i);
                if (map.containsKey(prefix)) {
                    return false;
                }
            }
        }

        return true;
    }
}
