import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int [][]map;
	
	static void rotaion(int i1, int i2, int j1, int j2) {
		
		int a = map[i1][j1];
		int b = map[i1][j2];
		int c = map[i2][j1];
		int d = map[i2][j2];
		// i1 = 0 /j1 = 0 /i2 = 4 /j2 = 3
		for(int i = j1; i < j2; i++) {
			map[i1][i] = map[i1][i + 1];
		}
		
		for(int i = i1; i < i2; i++) {
			map[i][j2] = map[i + 1][j2];
		}
		
		for(int i = j2; i > j1; i--) {
			map[i2][i] = map[i2][i - 1];
		}
		
		for(int i = i2; i > i1; i--) {
			map[i][j1] = map[i - 1][j1];
		}
		map[i1+1][j1] = a;
	
	}
	
	public static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<K;i++) {
			int MIN = Math.min(N, M);
			for(int k = 0 ;k < MIN/2 ; k++ ) {
				rotaion(0+k,N-1-k,0+k,M-1-k);
			}
		}
		print();

	}

}