

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int result=0;
	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int Y=Integer.parseInt(st.nextToken());
		int X=Integer.parseInt(st.nextToken());
		
		recursive(Y,X,(int)Math.pow(2, N));
		
		System.out.println(result);
	}

	private static void recursive(int y, int x, int size) {
		if(size==1)return;
		
		int half=size/2;
		
		if(y<half && x <half) {
			recursive(y, x, half);
		}else if(y<half && x>=half) {
			result+=half*half;
			recursive(y, x-half, half);
		}else if(y>=half && x<half) {
			result+=half*half*2;
			recursive(y-half, x, half);
		}else if(y>=half && x>=half) {
			result+=half*half*3;
			recursive(y-half, x-half, half);
		}
	}
}
