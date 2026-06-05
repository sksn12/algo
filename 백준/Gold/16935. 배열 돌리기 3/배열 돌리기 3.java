import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int Y,X;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());

		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		arr = new int[Y][X];

		for (int i = 0; i < Y; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < X; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(bf.readLine());
		int[] play = new int[st.countTokens()];
		int val = st.countTokens(); // nextToken으로 값을 가져오면 count의 값이 변해 val변수로 고정
		for (int i = 0; i < val; i++) {
			play[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < play.length; i++) {
			switch (play[i]) {
			case 1:
				fun01();
				break;
			case 2:
				fun02();
				break;
			case 3:
				fun03();
				break;
			case 4:
				fun04();
				break;
			case 5:
				fun05();
				break;
			case 6:
				fun06();
				break;
			default:
				break;
			}
		}

		print(arr);
	}

	private static void fun06() {
		int[][] tmpArr = new int[Y][X];
		int hafX = X / 2;
		int hafY = Y / 2;

		// 1번을 4번으로
		for (int i = 0; i < hafY; i++) {
			for (int j = 0; j < hafX; j++) {
				tmpArr[i+hafY][j] = arr[i][j];
			}
		}

		// 4번을 3번으로
		for (int i = hafY; i < Y; i++) {
			for (int j = 0; j < hafX; j++) {
				tmpArr[i][j+hafX] = arr[i][j];
			}
		}

		// 3번을 2번으로
		for (int i = hafY; i < Y; i++) {
			for (int j = hafX; j < X; j++) {
				tmpArr[i-hafY][j] = arr[i][j];
			}
		}

		// 2번을 1번으로
		for (int i = 0; i < hafY; i++) {
			for (int j = hafX; j < X; j++) {
				tmpArr[i][j-hafX] = arr[i][j];
			}
		}
		arr = tmpArr;
	}

	private static void fun05() {
		int[][] tmpArr = new int[Y][X];
		int hafX = X / 2;
		int hafY = Y / 2; 

		// 1번을 2번으로
		for (int i = 0; i < hafY; i++) {
			for (int j = 0; j < hafX; j++) {
				tmpArr[i][hafX + j] = arr[i][j];
			}
		}

		// 2번을 3번으로
		for (int i = 0; i < hafY; i++) {
			for (int j = hafX; j < X; j++) {
				tmpArr[hafY + i][j] = arr[i][j];
			}
		}

		// 3번을 4번으로
		for (int i = hafY; i < Y; i++) {
			for (int j = hafX; j < X; j++) {
				tmpArr[i][j - hafX] = arr[i][j];
			}
		}

		// 4번을 1번으로
		for (int i = hafY; i < Y; i++) {
			for (int j = 0; j < hafX; j++) {
				tmpArr[i - hafY][j] = arr[i][j];
			}
		}
		arr = tmpArr;

	}

	private static void fun04() {
		int[][] tmpArr = new int[X][Y];
		for (int i = X - 1; 0 <= i; i--) {
			for (int j = 0; j < Y; j++) {
				tmpArr[X - i - 1][j] = arr[j][i];
				
			}
		}
		int tmp=X;
		X=Y;
		Y=tmp;
		
		arr = tmpArr;
	}

	private static void fun03() {
		int[][] tmpArr = new int[X][Y];
		for (int i = 0; i < X; i++) {
			for (int j = Y - 1; 0 <= j; j--) {
				tmpArr[i][Y - j - 1] = arr[j][i];
			}
		}
		
		int tmp=X;
		X=Y;
		Y=tmp;
		
		arr = tmpArr;
	}

	private static void fun02() {
		int[][] tmpArr = new int[Y][X];
		for (int i = 0; i < Y; i++) {
			for (int j = 0; j < X; j++) {
				tmpArr[i][X - j - 1] = arr[i][j];
			}
		}
		arr = tmpArr;
	}

	private static void fun01() {
		int[][] tmpArr = new int[Y][X];
		for (int i = 0; i < Y; i++) {
			for (int j = 0; j < X; j++) {
				tmpArr[Y - i - 1][j] = arr[i][j];
			}
		}
		arr = tmpArr;
	}

	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			StringBuilder st = new StringBuilder();
			for (int j = 0; j < arr[i].length; j++) {
				st.append(arr[i][j] + " ");
			}
			System.out.println(st);
		}
	}

}
