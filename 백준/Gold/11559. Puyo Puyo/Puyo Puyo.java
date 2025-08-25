
import java.io.*;
import java.util.*;

public class Main {
    static String[][] map=new String[12][6];
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static int answer=0;
    static boolean boom;
    static boolean[][] v;
    static Queue<Node> q;
    static List<Node> list;

    static List<Node> boom_list;
    static class Node{
        int y,x;

        Node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<12;i++){
            String[] str=br.readLine().split("");
            for (int j = 0; j < 6; j++) {
                map[i][j]=str[j];
            }
        }

        while (true) {
            boom=false;

            v=new boolean[12][6];
            q=new ArrayDeque<>();
            boom_list=new ArrayList<>();

            // 1. 모든 맵에서 사방으로 자신과 같은색상이 4개 이상인 값을 찾음
            for(int i=0;i<12;i++){
                for (int j = 0; j < 6; j++) {
                    if(!map[i][j].equals(".") && !v[i][j]){
                        list = new ArrayList<>();

                        v[i][j]=true;
                        q.offer(new Node(i,j));
                        list.add(new Node(i,j));

                        BFS(map[i][j]);
                    }
                }
            }

            if(boom_list.size()!=0){
                BOOM(); // 2. 색상이 4개 이상 사방으로 이어져있는 모든 그룹들 터트리기
                gravity(); // 3. 빈공간이 있다면 중력에 의해 내려감
                boom=true;
            }

            // 어떤 그룹도 터지지 않은 경우 종료
            if(!boom)break;
            else answer+=1; // 어떤 그룹이라도 터졌으면 연쇄 +1
        }

        System.out.println(answer);
    }


    public static void BFS(String color){
        while(!q.isEmpty()){
            Node n=q.poll();

            for (int d = 0; d < 4; d++) {
                int ny=dy[d]+n.y;
                int nx=dx[d]+n.x;

                if(0<=ny && 0<=nx && ny<12 && nx<6 && !v[ny][nx] && map[ny][nx].equals(color)){
                    q.offer(new Node(ny,nx));
                    v[ny][nx]=true;
                    list.add(new Node(ny,nx));
                }
            }
        }

        if(list.size()>=4){
            list.forEach((Node n)->{
                boom_list.add(new Node(n.y,n.x));
            });
        }
    }

    public static void BOOM(){
        boom_list.forEach((Node n)->{
            map[n.y][n.x]=".";
        });
    }

    public static void gravity(){
        for (int i = 11; 0<=i; i--) {
            for (int j = 0; j < 6; j++) {
                if(!map[i][j].equals(".")){
                    int ny=i;

                    while (true){
                        ny+=1;
                        if(ny<12 && map[ny][j].equals(".")){
                            map[ny][j]=map[ny-1][j];
                            map[ny-1][j]=".";
                        }else break;
                    }
                }
            }
        }

       

    }
}
