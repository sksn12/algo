
import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int m,s,d;

        Node(int m,int s,int d){
            this.m=m;
            this.s=s;
            this.d=d;
        }
    }

    static Queue<Node>[][] map;
    static int N,M,K;
    static int[] dy={-1,-1,0,1,1,1,0,-1};
    static int[] dx={0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        map=new Queue[N][N];
        Queue<Node>[][] tmp=new Queue[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j]=new ArrayDeque<>();
                tmp[i][j]=new ArrayDeque<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());
            int y=Integer.parseInt(st.nextToken());
            int x=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int s=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());

            map[y-1][x-1].add(new Node(m,s,d));
        }

        for (int i = 0; i < K; i++) {

            // 모든 파이어볼 임시배열로 이동
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    while (!map[j][k].isEmpty()){
                        Node n=map[j][k].poll();
                        int ny=j;
                        int nx=k;

                        for (int l = 0; l < n.s; l++) {
                            ny+=dy[n.d];
                            nx+=dx[n.d];

                            if(ny<0)ny=N-1;
                            else if(ny>=N)ny=0;

                            if(nx<0)nx=N-1;
                            else if(nx>=N)nx=0;
                        }

                        tmp[ny][nx].add(new Node(n.m,n.s,n.d));
                    }
                }
            }

            // 이동한 파이어볼 나누기
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {

                    if(tmp[j][k].size() >= 2){   // 2개 이상일 때만 분할

                        int total_m = 0;
                        int total_s = 0;
                        int total_cnt = tmp[j][k].size();

                        boolean allEven = true;
                        boolean allOdd = true;

                        while (!tmp[j][k].isEmpty()){
                            Node n = tmp[j][k].poll();

                            total_m += n.m;
                            total_s += n.s;

                            if(n.d % 2 == 0) allOdd = false;
                            else allEven = false;
                        }

                        int new_m = total_m / 5;
                        if(new_m == 0) continue;

                        int new_s = total_s / total_cnt;

                        if(allEven || allOdd){
                            for (int d = 0; d < 8; d += 2) {
                                map[j][k].add(new Node(new_m, new_s, d));
                            }
                        } else {
                            for (int d = 1; d < 8; d += 2) {
                                map[j][k].add(new Node(new_m, new_s, d));
                            }
                        }
                    }

                    //  1개만 있는 경우 그대로 유지
                    else if(tmp[j][k].size() == 1){
                        map[j][k].add(tmp[j][k].poll());
                    }

                }
            }
        }

        int answer=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                while (!map[i][j].isEmpty()){
                    answer+=map[i][j].poll().m;
                }
            }
        }

        System.out.println(answer);
    }
}
