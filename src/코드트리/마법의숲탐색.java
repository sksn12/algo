package 코드트리;

import java.io.*;
import java.util.*;

public class 마법의숲탐색 {
    static int Y,X,K;
    static int[] dy={-1,0,1,0};
    static int[] dx={0,1,0,-1};
    static class Node{
        int seq;
        boolean exit;

        Node(int seq,boolean exit){
            this.seq=seq;
            this.exit=exit;
        }
    }

    static Node[][] map;
    static int D;
    static List<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        map=new Node[Y][X];

        int answer=0;
        for(int k=1;k<=K;k++){
            st=new StringTokenizer(br.readLine());
            int SX=Integer.parseInt(st.nextToken()); // 시작 x 위치

            // 시작 위치 초기화
            list=new ArrayList<>();
            list.add(new int[] {-3,SX-1}); // 북
            list.add(new int[] {-2,SX}); // 동
            list.add(new int[] {-1,SX-1}); // 남
            list.add(new int[] {-2,SX-2}); // 서

            D=Integer.parseInt(st.nextToken());

            while(true){
                // 1. 아랫 방향 확인
                boolean val1=down();
                if(val1)continue;

                // 2. 왼쪽 방향 확인
                boolean val2=left();
                if(val2)continue;

                // 3. 우측 방향 확인
                boolean val3=right();
                if(val3)continue;

                break;
            }

            // 다 이동하고 마지막에 그려야함
            draw(k);
            println();
        }
    }

    public static boolean down(){
        int size=list.size();

        // 현재 모든 위치가 아래로 이동할때 map에 있는 seq가 0이면 이동!
        L:for(int i=0;i<size;i++){
            int[] n=list.get(i);
            int ny=n[0]+1;
            int nx=n[1];

            if(ny<0)

                if(map[ny][nx].seq!=0 || ny>=Y){
                    return false;
                }
        }

        for(int i=0;i<size;i++){
            int[] n=list.get(i);
            int ny=n[0]+1;
            int nx=n[1];

            list.set(i,new int[] {ny,nx});
        }

        return true;
    }

    public static boolean left(){
        int size=list.size();

        // 왼쪽 검사
        for(int i=0;i<size;i++){
            int[] n=list.get(i);

            int ny=n[0];
            int nx=n[1]-1;
            if(map[ny][nx].seq!=0 || nx<0){
                return false;
            }
        }

        // 왼쪽,아래 검사
        for(int i=0;i<size;i++){
            int[] n=list.get(i);
            int ny=n[0]+1;
            int nx=n[1]-1;

            if(map[ny][nx].seq!=0 || ny>=Y){
                return false;
            }
        }

        for(int i=0;i<size;i++){
            int[] n=list.get(i);

            int ny=n[0]+1;
            int nx=n[1]-1;
            list.set(i,new int[] {ny,nx});
        }

        changeD("L");
        return true;
    }

    public static boolean right(){
        int size=list.size();

        // 오른쪽 검사
        for(int i=0;i<size;i++){
            int[] n=list.get(i);

            int ny=n[0];
            int nx=n[1]+1;
            if(map[ny][nx].seq!=0 || nx>=X){
                return false;
            }
        }

        // 오른쪽,아래 검사
        for(int i=0;i<size;i++){
            int[] n=list.get(i);
            int ny=n[0]+1;
            int nx=n[1]+1;

            if(map[ny][nx].seq!=0 || ny>=Y){
                return false;
            }
        }

        for(int i=0;i<size;i++){
            int[] n=list.get(i);

            int ny=n[0]+1;
            int nx=n[1]+1;
            list.set(i,new int[] {ny,nx});
        }

        changeD("R");
        return true;
    }

    public static void changeD(String LR){
        if(D==0){
            if(LR.equals("L"))D=3;
            else D=1;
        }else if(D==1){
            if(LR.equals("L"))D=0;
            else D=2;
        }else if(D==2){
            if(LR.equals("L"))D=1;
            else D=3;
        }else if(D==3){
            if(LR.equals("L"))D=2;
            else D=0;
        }
    }

    public static void draw(int seq){
        for(int i=0;i<list.size();i++){
            int[] n=list.get(i);

            // 현재 나오는 순서랑 방향이 같다면 해당 위치는 출구가 되었다 표시
            if(i==D)map[n[0]][n[1]]=new Node(seq,true);
            else map[n[0]][n[1]]=new Node(seq,false);
        }
    }

    public static void println(){
        for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                System.out.printf(map[i][j].seq+" ");
            }
            System.out.println();
        }
        System.out.println("============");
    }
}
