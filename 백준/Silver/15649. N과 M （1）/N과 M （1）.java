import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		String[] str=bf.readLine().split(" ");
		N=Integer.parseInt(str[0]);
		int M=Integer.parseInt(str[1]);
		
		int[] sel=new int[M];
		boolean[] visited=new boolean[N];
		
		recursive(sel,visited,0);
	}

	private static void recursive( int[] sel,boolean[] visited ,int idx) {
		if(idx==sel.length) {
			StringBuilder st=new StringBuilder();
			for (int z = 0; z < sel.length; z++) {
				st.append(sel[z]+" ");
			}
			System.out.println(st);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i]=true;
				sel[idx]=i+1;
				recursive(sel, visited, idx+1);
				visited[i]=false;
			}
		}
	}

	
	
}
