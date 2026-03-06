import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int y,x;

        Node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }

    static Queue<Node> fire;
    static Queue<Node> person;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};

    static int Y,X;
    static String[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T=Integer.parseInt(st.nextToken());

        for (int t = 0; t <T; t++) {
            st=new StringTokenizer(br.readLine());

            X=Integer.parseInt(st.nextToken());
            Y=Integer.parseInt(st.nextToken());

            map=new String[Y][X];

            fire=new ArrayDeque<>();
            person=new ArrayDeque<>();

            for (int i = 0; i < Y; i++) {
                String[] str=br.readLine().split("");

                for (int j = 0; j < X; j++) {
                    map[i][j]=str[j];
                    if(map[i][j].equals("*"))fire.add(new Node(i,j));
                    else  if(map[i][j].equals("@"))person.add(new Node(i,j));
                }
            }

            int cnt=0;
            boolean val=false;

            while (!person.isEmpty()){
                cnt+=1;

                // 불 먼저 확산
                Queue<Node> fire_tmp=new ArrayDeque<>();
                while (!fire.isEmpty()){
                    Node f=fire.poll();

                    for (int d = 0; d < 4; d++) {
                        int ny=dy[d]+f.y;
                        int nx=dx[d]+f.x;

                        if(0<=ny && 0<=nx && ny<Y && nx<X && (map[ny][nx].equals(".") || map[ny][nx].equals("@"))){
                            map[ny][nx]="*";
                            fire_tmp.add(new Node(ny,nx));
                        }
                    }
                }

                // 임시로 번진 불의 위치를 넣어둔 큐를 순회하면서 원래 불을 저장하는 큐에 다시 담기
                while (!fire_tmp.isEmpty()){
                    Node n=fire_tmp.poll();
                    fire.offer(new Node(n.y,n.x));
                }


                // 사람 이동
                Queue<Node> person_tmp=new ArrayDeque<>();
                while (!person.isEmpty()){
                    Node p=person.poll();

                    for (int d = 0; d < 4; d++) {
                        int ny=dy[d]+p.y;
                        int nx=dx[d]+p.x;

                        if(0<=ny && 0<=nx && ny<Y && nx<X && map[ny][nx].equals(".")){
                            map[ny][nx]="@";
                            person_tmp.add(new Node(ny,nx));
                        }
                        // 사람이 빌딩을 탈출한 케이스
                        else if(ny<0 || nx<0 || ny>=Y || nx>= X){
                            System.out.println(cnt);
                            val=true;

                            person=new ArrayDeque<>();
                            person_tmp=new ArrayDeque<>();
                            break;
                        }
                    }
                }

                while (!person_tmp.isEmpty()){
                    Node n=person_tmp.poll();
                    person.offer(new Node(n.y,n.x));
                }
            }

            if(!val) System.out.println("IMPOSSIBLE");
        }
    }
}


