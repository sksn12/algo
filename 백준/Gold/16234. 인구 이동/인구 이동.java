import java.io.*;
import java.util.*;

public class Main {
  static int N,L,R,answer;
  static int[][] map;
  static int[] dy={-1,0,1,0};
  static int[] dx={0,1,0,-1};

  public static void main(String[] args) throws IOException {
    BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st=new StringTokenizer(bf.readLine()," ");

    N=Integer.parseInt(st.nextToken());
    L=Integer.parseInt(st.nextToken());
    R=Integer.parseInt(st.nextToken());

    map=new int[N][N];

    for (int i = 0; i < N; i++) {
      st=new StringTokenizer(bf.readLine()," ");
      for (int j = 0; j <N ; j++) {
        map[i][j]=Integer.parseInt(st.nextToken());
      }
    }

    boolean loop=true;
    // 풀이 방법 : 각 위치마다 BFS를 돌려 이동 가능 하면 해당 값을 sum, 되는 지역까지 탐색 후 해당 지역에 sum 평균을 저장
    while(loop){ // 나중에 변경로직 작성
      loop=false;

      Queue<int[]> q=new LinkedList<>();
      boolean[][] v=new boolean[N][N];
      ArrayList<int[]> list=new ArrayList<>();

      for (int i = 0; i < N; i++) {
        for (int j = 0; j <N ; j++) {
            if(!v[i][j]){
              q.offer(new int[] {i,j});
              BFS(q,v,list);

              // 인구이동이 가능한 리스트가 있다면
              if(list.size()>0){
                loop=true;

                int sum=map[i][j];
                for (int k = 0; k < list.size(); k++) {
                  sum+=map[list.get(k)[0]][list.get(k)[1]];
                }

                map[i][j]=Math.abs(sum/(list.size()+1));

                for (int k = 0; k < list.size(); k++) {
                  map[list.get(k)[0]][list.get(k)[1]]=Math.abs(sum/(list.size()+1));
                }

                list=new ArrayList<>(); // 리스트 초기화
              }
            }
        }
      }
      if(loop)answer+=1;

    }
    System.out.println(answer);
  }

  private static void BFS(Queue<int[]> q, boolean[][] v,ArrayList<int[]> list) {
    while (!q.isEmpty()){
      int[] yx=q.poll();
      int y=yx[0];
      int x=yx[1];
      v[y][x]=true;

      for (int d = 0; d < 4; d++) {
        int ny=dy[d]+y;
        int nx=dx[d]+x;

        if(0<=ny && 0<=nx && ny<N && nx< N && !v[ny][nx]){
          int tmp=Math.abs(map[y][x]-map[ny][nx]);

          if(tmp>=L && tmp<=R ){ // 문제에서 요구되는 제약 사항 추가
            v[ny][nx]=true;
            q.offer(new int[] {ny,nx});
            list.add(new int[] {ny,nx}); // 인구이동이 가능한 위치 추가
          }
        }
      }
    }

  }
}
