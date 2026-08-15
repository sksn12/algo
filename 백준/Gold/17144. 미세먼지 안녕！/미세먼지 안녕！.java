
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	static int Y;
	static int X;
	static int Time;
	static int[][] map;
	static int[][] cmap;
	static int[][] c2map;
	static int[] dy={-1,0,1,0};
	static int[] dx={0,1,0,-1};
	static int[] upL=new int[2];
	static int[] downL=new int[2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		
		Y=Integer.parseInt(st.nextToken());
		X=Integer.parseInt(st.nextToken());
		Time=Integer.parseInt(st.nextToken());
		
		map=new int[Y][X];
		cmap=new int[Y][X];
		c2map=new int[Y][X];
		
		int mcnt=0;
		for (int i = 0; i < Y; i++) {
			st=new StringTokenizer(bf.readLine());
			for (int j = 0; j < X; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==-1 && mcnt==0) {
					upL[0]=i;
					upL[1]=j;
					mcnt+=1;
				}else if (map[i][j]==-1 && mcnt==1) {
					downL[0]=i;
					downL[1]=j;
				}

			}
		}
		
		for (int i = 0; i < Time; i++) {
			// 미세먼지 확산
			for (int y = 0; y < Y; y++) {
				for (int x = 0; x < X; x++) {
					int tmp=map[y][x];
					int cnt=0; // 확산되는 방향의 수 
					int val=tmp/5; // 확산되는 양 
					if(tmp==-1)cmap[y][x]=-1;
					if(tmp>0) {
						for (int d = 0; d < 4; d++) {
							int ny=dy[d]+y;
							int nx=dx[d]+x;
							
							if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx]!=-1) {
								cnt+=1;
								cmap[ny][nx]+=val;
							}
						}
						
						cmap[y][x]+=tmp-(val)*cnt;
					}
				}
			}
			
			copyArray();
			// 공기 청정기 가동
			UDFS(upL[0],upL[1]);
			DDFS(downL[0],downL[1]);
			clearArray();
		}
		
		print();
	}

	private static void clearArray() {
		for (int i = 0; i <Y; i++) {
			for (int j = 0; j < X; j++) {
				map[i][j]=c2map[i][j];
				cmap[i][j]=0;
			}
		}

	}

	private static void copyArray() {
		for (int i = 0; i <Y; i++) {
			for (int j = 0; j < X; j++) {
				c2map[i][j]=cmap[i][j];
			}
		}
	}

	private static void UDFS(int i, int j) {
		int[] udy= {0,-1,0,1};
		int[] udx= {1,0,-1,0};
		
		int y=i;
		int x=j;
		for (int d = 0; d < 4; d++) {
			while(true) {
				int ny=udy[d]+y;
				int nx=udx[d]+x;
				
				if(d==3 && cmap[ny][nx]==-1)break;
				
				if(ny<0 || nx< 0 || ny>=Y || nx >=X) {
					break;
				}else {
					if(cmap[y][x]==-1) {
						c2map[ny][nx]=0;
					}else {
						c2map[ny][nx]=cmap[y][x];
					}
					
					y=ny;
					x=nx;
				}
				
			}
					
		}
	}

	private static void DDFS(int i, int j) {
		int[] udy= {0,1,0,-1};
		int[] udx= {1,0,-1,0};
		
		int y=i;
		int x=j;
		for (int d = 0; d < 4; d++) {
			while(true) {
				int ny=udy[d]+y;
				int nx=udx[d]+x;
				
				if(d==3 && cmap[ny][nx]==-1)break;
				
				if(ny<0 || nx< 0 || ny>=Y || nx >=X) {
					break;
				}else {
					if(cmap[y][x]==-1) {
						c2map[ny][nx]=0;
					}else {
						c2map[ny][nx]=cmap[y][x];
					}
					
					y=ny;
					x=nx;
				}
				
			}
					
		}
	}
	
	private static void print() {
		int result=0;
		for (int i = 0; i < Y; i++) {
			for (int j = 0; j < X; j++) {
				result+=c2map[i][j];
			}
		}
		System.out.println(result+2);
	}
}
