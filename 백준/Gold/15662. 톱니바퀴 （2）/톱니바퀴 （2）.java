
import java.io.*;
import java.util.*;

public class Main {
    static int T,D;
    static int[][] map;
    static List<Integer> list;
    static List<Integer> list2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T=Integer.parseInt(st.nextToken());
        map=new int[T+1][8];

        for(int i=0;i<T;i++){
            String[] t=br.readLine().split("");
            for(int j=0;j<8;j++){
                map[i+1][j]=Integer.parseInt(t[j]);
            }
        }

        int cnt=Integer.parseInt(br.readLine());

        for(int i=0;i<cnt;i++){
            st=new StringTokenizer(br.readLine());

            int L=Integer.parseInt(st.nextToken());
            D=Integer.parseInt(st.nextToken());

            list=new ArrayList<>();
            list2=new ArrayList<>();

            // 1. 현재 톱니에서 우측 방향으로 돌릴 수 있는 톱니 구하기
            right(L);
            // 2. 현재 톱니 기준 좌측 방향으로 돌릴 수 있는 톱니 구하기
            left(L);

            // 3. 현재 톱니 먼저 방향에 맞게 돌리기
            if(D==1){
                rightRotate(L,D);
            }else if(D==-1){
                leftRotate(L,D);
            }

            // 4. 우측 방향으로 돌릴 수 있는 톱니 회전
            int RD=D;
            for(int c=0;c<list.size();c++){
                if(RD==1){
                    RD=leftRotate(list.get(c),RD);
                }else{
                    RD=rightRotate(list.get(c),RD);
                }
            }

            int LD=D;
            // 5. 좌측 방향으로 돌릴 수 있는 톱니 회전
            for(int c=0;c<list2.size();c++){
                if(LD==1){
                    LD=leftRotate(list2.get(c),LD);
                }else{
                    LD=rightRotate(list2.get(c),LD);
                }
            }
        }

        print();
    }

    public static void right(int L){
        while(L<T){
            if(map[L][2]==map[L+1][6])break;

            L+=1;
            list.add(L);
        }
    }

    public static void left(int L){
        while(1<L){
            if(map[L-1][2]==map[L][6])break;

            L-=1;
            list2.add(L);
        }
    }

    public static int rightRotate(int L,int D){
        int first=map[L][7];
        for(int i=6;0<=i;i--){
            map[L][i+1]=map[L][i];
        }
        map[L][0]=first;

        if(D==1)return -1;
        else return 1;
    }

    public static int leftRotate(int L,int D){
        int first=map[L][0];
        for(int i=0;i<7;i++){
            map[L][i]=map[L][i+1];
        }
        map[L][7]=first;

        if(D==1)return -1;
        else return 1;
    }

    public static void print(){
        int answer=0;

        for(int i=1;i<=T;i++){
            if(map[i][0]==1)answer+=1;
        }

        System.out.println(answer);
    }
}