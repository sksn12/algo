import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(bf.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int R=Integer.parseInt(st.nextToken());
		
		int[][] arr=new int[N][M];
		// 오 아 왼 위
		int[] dy= {0,1,0,-1};
		int[] dx= {1,0,-1,0};
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		for (int t = 0; t < R; t++) {
			int loop=Math.min(N/2, M/2);
			for (int i = 0; i < loop; i++) {
				int sy=i;
				int sx=i;
				int frist=arr[sy][sx];
				int d=0;
				
				while (d<4) {
					int ny=sy+dy[d];
					int nx=sx+dx[d];
					
					if(ny<N-i && nx<M -i&& i<=nx && i<=ny) {
						arr[sy][sx]=arr[ny][nx];
						sy=ny;
						sx=nx;
					}else {
						d+=1;
					}
				}
				arr[i+1][i]=frist;
				
			}
		}
		print(arr);
	}

	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			StringBuilder st=new StringBuilder();
			for (int j = 0; j < arr[i].length; j++) {
				st.append(arr[i][j]+" ");
			}
			System.out.println(st);
		}
	}

}
