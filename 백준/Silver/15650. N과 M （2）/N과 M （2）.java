
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static boolean[] sel;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		sel=new boolean[N];
		
		recursive(0,0);
	}
	
	private static void recursive(int idx,int k) {
		if(idx==N) {
			if(k==M) {
				StringBuilder sb=new StringBuilder();
				for (int i = 0; i < sel.length; i++) {
					if(sel[i]) {
						sb.append((i+1)+" ");
					}
				}
				System.out.println(sb);
			}
			return;
		}
		
		sel[idx]=true;
		recursive(idx+1, k+1);
		sel[idx]=false;
		recursive(idx+1, k);
	}
}
