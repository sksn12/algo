package 백준;

import java.io.*;
import java.util.*;

public class 불 {
    static int T;
    static int Y,X;
    static String[][] map;
    static class Node implements Comparable<Node>{
        int y;
        int x;
        int cnt;
        int val;

        Node(int y,int x,int cnt,int val){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
            this.val=val;
        }
        @Override
        public int compareTo(Node n){
            return this.val-n.val;
        }
    }
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static PriorityQueue<Node> pq;
    static Queue<Node> q;
    static boolean[][] v1;
    static boolean[][] v2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T=Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {
            st=new StringTokenizer(br.readLine());
            X=Integer.parseInt(st.nextToken());
            Y=Integer.parseInt(st.nextToken());

            map=new String[Y][X];
            v1=new boolean[Y][X];
            v2=new boolean[Y][X];
            pq=new PriorityQueue<>();

            for (int i = 0; i < Y; i++) {
                st=new StringTokenizer(br.readLine());
                String[] str=st.nextToken().split("");

                for (int j = 0; j < X; j++) {
                    map[i][j]=str[j];
                    if(map[i][j].equals("@")){
                        pq.offer(new Node(i,j,0,1));
                    }else if(map[i][j].equals("*")){
                        pq.offer(new Node(i,j,0,0));
                    }
                }
            }

            while (true){
                // 불이 먼저 번지게
                q=new ArrayDeque<>();
                boolean val=BFS();
                if(val)break;

                if(q.size()==0){
                    System.out.println("IMPOSSIBLE");
                    break;
                }
                // 이동한 불과 상근이의 정보를 pq에 다시 넣기
                while (!q.isEmpty()){
                    Node n=q.poll();
                    pq.offer(new Node(n.y,n.x,n.cnt,n.val));
                }

                // 종료조건 확인
                boolean test=false;
                for (int i = 0; i < Y; i++) {
                    for (int j = 0; j < X; j++) {
                        if(map[i][j].equals("@"))test=true;
                    }
                }

                if(!test){
                    System.out.println("IMPOSSIBLE");
                    break;
                }
            }

        }
    }
    public static boolean BFS(){
        while (!pq.isEmpty()){
            Node n=pq.poll();

            if(n.val==0){
                v1[n.y][n.x]=true;
            }else{
                v2[n.y][n.x]=true;
            }

            for (int d = 0; d < 4; d++) {
                int ny=dy[d]+n.y;
                int nx=dx[d]+n.x;

                if(0<=ny && 0<=nx && ny<Y && nx<X && !map[ny][nx].equals("#")){
                    // 불인경우
                    if(n.val==0){
                        if(!v1[ny][nx]){
                            map[ny][nx]="*";
                            q.offer(new Node(ny,nx,n.cnt+1,0));
                        }
                    }else{
                        if(!v2[ny][nx] && map[ny][nx].equals(".")){
                            if((ny==0 || nx==0) || (ny==Y-1 || nx==X-1)){
                                System.out.println(n.cnt+2);
                                return true;
                            }
                            map[ny][nx]="@";
                            q.offer(new Node(ny,nx,n.cnt+1,1));
                        }
                    }
                }
            }
        }
        return false;
    }
}


/* 1. pq를 선언한다(불이 먼저 나오게 셋팅)
   2. BFS()를 돌린다 pq에 값을 다꺼내서 불이 먼저나오니깐 벽을 제외하고 map전부를 불로! -> 이때, 불 전용 방문 배열필요!
   3. 상근이가 나올때는 불과 벽을 제외하고 사방으로 1칸 전진
   4. pq에서 꺼내서 새로운 위치값들을 전부  q에 담아준다
   5. 해당 q에 있는값들을 pq에 다시 옮긴다.
   6. [종료 조건] 상근이의 위치가 끝트머리로 오면 cnt값 출력, 상근이가 map에 없으면 종료
 */
