package 백준;

import java.io.*;

public class 틱택토 {
    static String[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            if (line.equals("end")) break;

            map = new String[3][3];
            String[] arr = line.split("");

            int xCount = 0, oCount = 0;
            for (int i = 0; i < 9; i++) {
                map[i / 3][i % 3] = arr[i];
                if (arr[i].equals("X")) xCount++;
                else if (arr[i].equals("O")) oCount++;
            }

            boolean xWin = isWin("X");
            boolean oWin = isWin("O");

            // 1. 말의 개수 체크
            if (oCount > xCount || xCount - oCount > 1) {
                System.out.println("invalid");
                continue;
            }

            // 2. 승자 둘 다 있는 경우는 invalid
            if (xWin && oWin) {
                System.out.println("invalid");
                continue;
            }

            // 3. X가 이겼으면 X가 한 수 더 많아야 함
            if (xWin) {
                if (xCount != oCount + 1) {
                    System.out.println("invalid");
                } else {
                    System.out.println("valid");
                }
                continue;
            }

            // 4. O가 이겼으면 X와 O 수가 같아야 함
            if (oWin) {
                if (xCount != oCount) {
                    System.out.println("invalid");
                } else {
                    System.out.println("valid");
                }
                continue;
            }

            // 5. 승자 없으면 → 꽉 찬 상태여야 valid
            if (xCount + oCount == 9) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }
    }

    public static boolean isWin(String mark) {
        // 가로 3줄
        for (int i = 0; i < 3; i++) {
            if (map[i][0].equals(mark) && map[i][1].equals(mark) && map[i][2].equals(mark))
                return true;
        }

        // 세로 3줄
        for (int i = 0; i < 3; i++) {
            if (map[0][i].equals(mark) && map[1][i].equals(mark) && map[2][i].equals(mark))
                return true;
        }

        // 대각선
        if (map[0][0].equals(mark) && map[1][1].equals(mark) && map[2][2].equals(mark))
            return true;
        if (map[0][2].equals(mark) && map[1][1].equals(mark) && map[2][0].equals(mark))
            return true;

        return false;
    }
}
