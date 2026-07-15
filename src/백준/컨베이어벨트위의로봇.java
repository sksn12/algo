package 백준;

import java.io.*;
import java.util.*;

public class 컨베이어벨트위의로봇 {
    static int N,K;
    static Node[][] container;
    static class Node{
        int life;
        boolean robot;

        Node(int life,boolean robot){
            this.life=life;
            this.robot=robot;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        container=new Node[2][N];

        st=new StringTokenizer(br.readLine());

        for (int i = 0; i < N*2; i++) {
            int n=Integer.parseInt(st.nextToken());

            if(i>=N)container[1][N*2-i-1]=new Node(n,false);
            else container[0][i]=new Node(n,false);
        }

        int answer=0;
        while (true){
            answer+=1;

            // 벨트 회전
            rotate();

            // 로봇이 있다면 로봇 움직이기
            move_robot();

            // 올리는 칸에 내구도가 아니면 로봇 올리기
            if(container[0][0].life>0){
                container[0][0].robot=true;
                container[0][0].life-=1;
            }

//            print2();

            // 전체를 확인해서 내구도가 K개 이상이면 종료
            boolean val=print();
            if(val)break;


        }
        System.out.println(answer);
    }

    public static void move_robot(){
        for (int i = N-1; 0<=i; i--) {
            // 첫번째 마지막 벨트에 로봇이 있다면 해당로봇은 내리니깐 false로
            if(i==N-1 && container[0][i].robot){
                container[0][i].robot=false;
            }else{
                if(container[0][i].robot && container[0][i+1].life>0){
                    if(!container[0][i+1].robot){
                        container[0][i].robot=false;
                        container[0][i+1].robot=true;
                        container[0][i+1].life-=1;
                    }
                }
            }
        }
    }

    public static void rotate(){
        Node[][] tmp=new Node[2][N];

        // -> 이 방향 먼저 옮기기
        for (int i = 0; i < N; i++) {
            if(i==N-1)tmp[1][i]=new Node(container[0][i].life,false);
            else tmp[0][i+1]=new Node(container[0][i].life,container[0][i].robot);
        }

        // 두번째 줄인 <- 방향 옮기기
        for (int i = N-1; 0<=i  ; i--) {
            if(i==0)tmp[0][0]=new Node(container[1][i].life,false);
            else tmp[1][i-1]=new Node(container[1][i].life,container[1][i].robot);
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                container[i][j].life=tmp[i][j].life;
                container[i][j].robot=tmp[i][j].robot;
            }
        }
    }

    public static boolean print(){
        int cnt=0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                if(container[i][j].life==0)cnt+=1;
            }
        }
        if(cnt>=K)return true;
        return false;
    }

    public static void print2(){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf(container[i][j].life+" ");
            }
            System.out.println();
        }
        System.out.println("================");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf(container[i][j].robot+" ");
            }
            System.out.println();
        }
        System.out.println("한번끝================");
    }
}
