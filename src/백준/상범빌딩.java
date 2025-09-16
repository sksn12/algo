package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상범빌딩 {
    static int L,Y,X;
    static Character[][][] map;
    static boolean[][][] v;
    static Queue<Node> q;
    static int[] dy={0,1,0,-1,0,0};
    static int[] dx={1,0,-1,0,0,0};
    static int[] dl={0,0,0,0,1,-1};

    static class Node{
        int l,y,x,cnt;

        Node(int l,int y,int x,int cnt){
            this.l=l;
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        while (true){
            StringTokenizer st=new StringTokenizer(br.readLine());

            L=Integer.parseInt(st.nextToken());
            Y=Integer.parseInt(st.nextToken());
            X=Integer.parseInt(st.nextToken());

            if(L==0)break;

            map=new Character[L][Y][X];
            v=new boolean[L][Y][X];
            q=new LinkedList<>();

            for (int i = 0; i < L; i++) {
                if(i>=1)br.readLine();
                for (int j = 0; j < Y; j++) {
                    String[] str=br.readLine().split("");
                    for (int k = 0; k < X; k++) {
                        map[i][j][k]=str[k].charAt(0);
                        if(str[k].charAt(0)=='S'){
                            q.offer(new Node(i,j,k,0));
                            v[i][j][k]=true;
                        }
                    }
                }
            }

            int answer=BFS();
            if(answer==0){
                System.out.println("Trapped!");
            }else{
                System.out.println("Escaped in "+answer+" minute(s).");
            }


            br.readLine();
        }
    }

    private static int BFS() {
        while (!q.isEmpty()){
            Node n=q.poll();

            for (int d = 0; d < 6; d++) {
                int ny=n.y+dy[d];
                int nx=n.x+dx[d];
                int nl=n.l+dl[d];

                if(0<=ny && 0<= nx && 0<=nl && ny<Y && nx<X && nl<L && !v[nl][ny][nx] && (map[nl][ny][nx]=='.' || map[nl][ny][nx]=='E')){
                    if(map[nl][ny][nx]=='E')return n.cnt+1;
                    v[nl][ny][nx]=true;
                    q.offer(new Node(nl,ny,nx,n.cnt+1));
                }
            }

        }
        return 0;
    }
}
