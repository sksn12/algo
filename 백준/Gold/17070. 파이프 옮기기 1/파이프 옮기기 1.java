
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int y,x,state;

        Node(int y,int x,int state){
            this.y=y;
            this.x=x;
            this.state=state;
        }
    }
    static int N,answer=0;
    static int[][] map;
    static Queue<Node> q=new LinkedList<>();
    static int[] dy={0,1,1};
    static int[] dx={1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map=new int[N][N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        if(map[N-1][N-1]==1){
            System.out.println(answer);
            return;
        }
        // 가로는 0 세로는 1 대각은 2 (현재 파이프의 상태), 파이프의 앞 쪽의 위치만 기억
        q.offer(new Node(0,1,0));

        BFS();

        System.out.println(answer);
    }

    private static void BFS() {
        while (!q.isEmpty()){
            Node n=q.poll();

            boolean val=false;
            for (int d = 0; d <3; d++) {
                // 우 아래를 검사해봤을때 하나라도 갈 수 없었던 값이 있었다면 대각은 갈 수 없음
                if(d==2 && val)continue;

                int ny=n.y+dy[d];
                int nx=n.x+dx[d];

                if(0<=ny && 0<=nx && ny<N && nx<N && map[ny][nx]==0){
                    // 밑에 로직을 이 조건문 안에 넣은 이유는 현재 파이프가 가로 상태일때 아래로는 갈 수 없지만 대각선을 위해 검사하고 pass
                    if(n.state==0 && d==1)continue;
                    else if (n.state==1 && d==0)continue;

                    if(ny==N-1 && nx==N-1)answer+=1;
                    else q.offer(new Node(ny,nx,d));
                }else val=true;
            }
        }
    }
}
