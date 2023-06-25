
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int Y,X,Z;
    static int answer=0;
    static int[][][] map;
    static boolean[][][] v;

    static int[] dy={-1,1,0,0,0,0};
    static int[] dx={0,0,1,-1,0,0};
    static int[] dz={0,0,0,0,1,-1};

    static Queue<Node> q=new LinkedList<>();
    static class Node{
        int x,y,z,day;

        Node(int z,int y,int x,int day){
            this.z=z;
            this.y=y;
            this.x=x;
            this.day=day;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        X=Integer.parseInt(st.nextToken());
        Y=Integer.parseInt(st.nextToken());
        Z=Integer.parseInt(st.nextToken());

        map=new int[Z][Y][X];
        v=new boolean[Z][Y][X];

        for (int i = 0; i < Z; i++) {
            for (int j = 0; j < Y;j++) {
                st=new StringTokenizer(br.readLine()," ");
                for (int z = 0; z < X; z++) {
                    map[i][j][z]=Integer.parseInt(st.nextToken());
                    if(map[i][j][z]==1)q.offer(new Node(i,j,z,0));
                }
            }
        }

        BFS();

        // 토마토가 다 안익었으면 -1
        for (int i = 0; i < Z; i++) {
            for (int j = 0; j < Y;j++) {
                for (int z = 0; z < X; z++) {
                    if(map[i][j][z]==1 || v[i][j][z] || map[i][j][z]==-1){
                        continue;
                    }else if(!v[i][j][z]){
                        answer=-1;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static void BFS() {
        while (!q.isEmpty()){
            Node node=q.poll();
            v[node.z][node.y][node.x]=true;

            for (int d = 0; d < 6; d++) {
                int ny=node.y+dy[d];
                int nx=node.x+dx[d];
                int nz=node.z+dz[d];

                if(0<=ny && 0<=nx && 0<=nz && ny<Y && nx<X && nz<Z && map[nz][ny][nx]==0 &&
                        !v[nz][ny][nx]){
                    q.offer(new Node(nz,ny,nx,node.day+1));
                    v[nz][ny][nx]=true;

                    answer=Math.max(node.day+1,answer);
                }
            }
        }

    }
}
