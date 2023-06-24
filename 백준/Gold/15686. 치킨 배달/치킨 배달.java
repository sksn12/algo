

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 치킨집의 조합을 구해서 각 조합마다 모든 시뮬레이션을 돌려보고 치킨거리가 가장 적게 나오는 값을 출력
public class Main {
	static ArrayList<int[]> homeList=new ArrayList<>();
	static ArrayList<int[]> chickenList=new ArrayList<>();
	static int N;
	static int M;
	static int result=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				int tmp=Integer.parseInt(st.nextToken());
				if(tmp==1) {
					homeList.add(new int[] {i,j});
				}else if(tmp==2) {
					chickenList.add(new int[] {i,j});
				}
			}
		}

		recursive(new boolean[chickenList.size()],0,0);
		System.out.println(result);
		
	}

	private static void recursive(boolean[] sel, int idx, int k) {
		if(idx==sel.length) {
			if(k==M) {
				ArrayList<int []> combineList=new ArrayList<>();
				for (int i = 0; i < sel.length; i++) {
					if(sel[i]) {
						combineList.add(new int[] {chickenList.get(i)[0],chickenList.get(i)[1]});
					}
				}
				result=Math.min(distance(combineList), result);
			}
			return;
		}
	
		sel[idx]=true;
		recursive(sel, idx+1, k+1);
		sel[idx]=false;
		recursive(sel, idx+1, k);
	}

	private static int distance(ArrayList<int[]> combineList) {
		int sum=0;
		for (int i = 0; i < homeList.size(); i++) {
			int SD=Integer.MAX_VALUE;
			int[] tmp=homeList.get(i);
			for (int j = 0; j < combineList.size(); j++) {
				int[] tmp2=combineList.get(j);
				int D=Math.abs(tmp[0]-tmp2[0])+Math.abs(tmp[1]-tmp2[1]);
				SD=Math.min(SD, D);
			}
			sum+=SD;
		}
		return sum;
	}
}
