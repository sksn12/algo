
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] point;
    static int n,f1,f2,f3,f4;
    static int[] dy={-1,0,1,0};
    static int[] dx={0,-1,0,1};
    static List<Node> list;
    static List<Node> List2;
    static List<Node> List3;
    static int FMax,EMax,answer=0;

    static class Node implements Comparable<Node>{
        int y,x,f,e; // f : favorite 가장 좋아하는 친구가 몇명인지, e : empty : 빈 공간의 갯수

        Node(int y,int x,int f,int e){
            this.y=y;
            this.x=x;
            this.f=f;
            this.e=e;
        }

        @Override
        public int compareTo(Node n){
            if(this.y!=n.y)return this.y-n.y;
            return this.x-n.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        map=new int[N][N];
        point=new int[(N*N)+1][4];

        for(int i=0;i<N*N;i++){
            st=new StringTokenizer(br.readLine());

            n=Integer.parseInt(st.nextToken());
            f1=Integer.parseInt(st.nextToken());
            f2=Integer.parseInt(st.nextToken());
            f3=Integer.parseInt(st.nextToken());
            f4=Integer.parseInt(st.nextToken());

            point[n][0]=f1;
            point[n][1]=f2;
            point[n][2]=f3;
            point[n][3]=f4;


            FMax=0;
            EMax=0;

            list=new ArrayList<>();
            List2=new ArrayList<>();
            List3=new ArrayList<>();
            Condition1();


            // list에서 가장 주위에 친한친구가 많은 값들을 제외하고 버리기
            for (Node n:list) {
                if(n.f==FMax)List2.add(n);
            }

            if(List2.size()>1){
                // 비어 있는 최대값
                for (Node n:List2) {
                    EMax=Math.max(EMax,n.e);
                }

                // 비어 있는 칸이 가장 큰 값들을 제외하고 버리기
                for (Node n:List2) {
                    if(n.e==EMax)List3.add(n);
                }

                int y=List3.get(0).y;
                int x=List3.get(0).x;
                map[y][x]=n;

            }else{
                int y=List2.get(0).y;
                int x=List2.get(0).x;
                map[y][x]=n;
            }

        }

 

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum=0;
                for (int d = 0; d < 4; d++) {
                    int ny=dy[d]+i;
                    int nx=dx[d]+j;
                    if(0<=ny && 0<=nx && ny<N && nx<N){
                        if(map[ny][nx]==point[map[i][j]][0] || map[ny][nx]==point[map[i][j]][1] ||map[ny][nx]==point[map[i][j]][2] ||map[ny][nx]==point[map[i][j]][3] ) sum+=1;
                    }
                }
                if(sum==1)answer+=1;
                else if(sum==2)answer+=10;
                else if(sum==3)answer+=100;
                else if(sum==4)answer+=1000;
            }
        }

        System.out.println(answer);
    }

    // map에 0인 값이 있으면 해당 위치를 기준으로 좋아하는 친구가 몇명있는지 && 빈공간이 몇개 인지 구함!
    private static void Condition1() {
        for(int i=0;i<N;i++){
            for (int j = 0; j < N; j++) {
                if(map[i][j]==0){
                    int fCnt=0; // 4방에서 좋아하는 친구들의 숫자
                    int eCnt=0; // 4방에서 빈공간

                    for (int d = 0; d < 4; d++) {
                        int ny=i+dy[d];
                        int nx=j+dx[d];

                        if(0<=ny && 0<=nx && ny<N && nx<N){
                            if(map[ny][nx]==0)eCnt+=1;
                            if(map[ny][nx]==f1 || map[ny][nx]==f2 ||map[ny][nx]==f3 ||map[ny][nx]==f4 ) fCnt+=1;
                        }
                    }

                    FMax=Math.max(FMax,fCnt);
                    list.add(new Node(i,j,fCnt,eCnt));
                }
            }
        }
    }
}
