
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N,K,M,result;
	static int[][] map;
	
	static Deque<changeD> dq=new LinkedList<>();
	static Deque<snake> snakeDq=new LinkedList<>();
	
	static int[] dx= {1,0,-1,0};
	static int[] dy= {0,1,0,-1};
	
	static class changeD{
		int time;
		String dir;

		public changeD(int time, String dir) {
			this.time = time;
			this.dir = dir;
		}
	}
	
	static class snake{
		int y;
		int x;
		int d;

		public snake(int y, int x,int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		
		N=Integer.parseInt(st.nextToken());
		
		map=new int[N][N];
		
		st=new StringTokenizer(bf.readLine());
		
		K=Integer.parseInt(st.nextToken());
		
		// 사과가 있는 위치는 2로 표기
		for (int i = 0; i < K; i++) {
			st=new StringTokenizer(bf.readLine());
			int ky=Integer.parseInt(st.nextToken());
			int kx=Integer.parseInt(st.nextToken());
			
			map[ky-1][kx-1]=2;
		}
		
		st=new StringTokenizer(bf.readLine());
		
		M=Integer.parseInt(st.nextToken());
		
		// dq에 위치 넣기
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(bf.readLine());
			int MT=Integer.parseInt(st.nextToken());
			String MD=st.nextToken();
			
			dq.add(new changeD(MT,MD));
		}
		
		// 뱀이 있는 위치에는 1로 표기
		map[0][0]=1;
		// 맨처음 뱀의 위치 
		snakeDq.add(new snake(0,0,0));

		
		while(true) {
			result+=1;
			snake snakeTmp=snakeDq.getFirst();
			
			int snakeD=snakeTmp.d;
			int ny=snakeTmp.y+dy[snakeD];
			int nx=snakeTmp.x+dx[snakeD];
			
			// 뱀이 움직이기전에 움직이는것이 가능한지 먼저 확인
			if(ny<0 || nx<0 || N<=ny || N<=nx || map[ny][nx]==1) {
				break;
			}
			
			// 가려는 방향에 사과가 있다면 꼬리를 그대로 유지 , 없다면 꼬리 삭제
			if(map[ny][nx]!=2 && snakeDq.size()>0) {
				snake snakeTmp2=snakeDq.pollLast();
				map[snakeTmp2.y][snakeTmp2.x]=0;
			}
			map[ny][nx]=1;
			
			
			if(dq.size()>0) {
				int Time=dq.getFirst().time;
				
				if(Time==result) {
					String Dir=dq.pollFirst().dir;
					if(Dir.equals("D")) {
						if(snakeD==3)snakeD=0;
						else snakeD+=1;
					}else if(Dir.equals("L")){
						if(snakeD==0)snakeD=3;
						else snakeD-=1;
					}
				}
			}
		
			
			snakeDq.addFirst(new snake(ny, nx, snakeD));
		
		}
		
		
		System.out.println(result);
	}
}
