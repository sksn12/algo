import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static String[][] map;
	static Queue<int[]> jq=new LinkedList<int[]>();
	static int[][] jv;
	static Queue<int[]> fq=new LinkedList<int[]>();
	static int[][] fv;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new String[N][M];
		jv=new int[N][M];
		fv=new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(bf.readLine());
			String[] str=st.nextToken().split("");
			for (int j = 0; j < M; j++) {
				map[i][j]=str[j];
				jv[i][j]=-1;
				fv[i][j]=-1;
				if(str[j].equals("J")) {
					jq.offer(new int[] {i,j});
					jv[i][j]=0;
				}else if(str[j].equals("F")) {
					fq.offer(new int[] {i,j});
					fv[i][j]=0;
				}
			}
		}
		
		FBFS();
		JBFS();
		
		serach();
	}
	
	private static void serach() {
		int result=Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if((i==0 || j==0 || i==N-1 || j== M-1) && jv[i][j]!=-1 ) {
					result=Math.min(result, jv[i][j]);
				}
			}
		}
		
		if(result!=Integer.MAX_VALUE)System.out.println(result+1);
		else System.out.println("IMPOSSIBLE");
	}

	private static void JBFS() {
		int[] dy= {-1,0,1,0};
		int[] dx= {0,1,0,-1};
		
		while(!jq.isEmpty()) {
			int[] yx=jq.poll();
			int y=yx[0];
			int x=yx[1];
			
			for (int d = 0; d < 4; d++) {
				int ny=dy[d]+y;
				int nx=dx[d]+x;
				
				if(0<=ny && 0<=nx && ny<N && nx < M && !map[ny][nx].equals("#") && jv[ny][nx]==-1 && (jv[y][x]+1<fv[ny][nx]|| fv[ny][nx]==-1)) {
					jv[ny][nx]=jv[y][x]+1;
					jq.offer(new int[] {ny,nx});
				}
			}
		}
		
	}

	private static void FBFS() {
		int[] dy= {-1,0,1,0};
		int[] dx= {0,1,0,-1};
		
		while(!fq.isEmpty()) {
			int[] yx=fq.poll();
			int y=yx[0];
			int x=yx[1];
			
			for (int d = 0; d < 4; d++) {
				int ny=dy[d]+y;
				int nx=dx[d]+x;
				
				if(0<=ny && 0<=nx && ny<N && nx < M && !map[ny][nx].equals("#") && fv[ny][nx]==-1) {
					fv[ny][nx]=fv[y][x]+1;
					fq.offer(new int[] {ny,nx});
				}
			}
		}
	}


}
