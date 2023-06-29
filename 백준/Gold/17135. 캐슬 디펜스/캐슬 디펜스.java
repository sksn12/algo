
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int Y,X,D;
    static int answer=0;
    static int monster;
    static int[][] map;
    static int[][] useMap;
    static boolean[] sel;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        Y=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());
        D=Integer.parseInt(st.nextToken());

        map=new int[Y][X];
        sel=new boolean[X];

        for (int i = 0; i < Y; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        // X에서 3명의 궁수가 올 수 있는 조합 짜기
        recursive(0,0);

        System.out.println(answer);
    }

    private static void recursive(int idx, int k) {
        if(idx==X){
            if(k==3){
                // 요번 조합에서 궁수의 위치 저장
                List<Integer> archer=new ArrayList<>();

                for (int i = 0; i < sel.length; i++) {
                    if(sel[i]){
                        archer.add(i);
                    }
                }

                // 원본 배열 map 하나 복사
                useMap=new int[Y][X];
                for (int i = 0; i < Y; i++) {
                    for (int j = 0; j < X; j++) {
                        useMap[i][j]=map[i][j];
                    }
                }

                start(archer);
            }
            return;
        }


        sel[idx]=true;
        recursive(idx+1,k+1);
        sel[idx]=false;
        recursive(idx+1,k);
    }

    private static void start(List<Integer> archer) {
        monster=0;

        while (true){
            // 몬스터 공격
            attack(archer);

            // 몬스터가 라인 한줄 내려오기
            Line();


            // useMap에 1이라는 값이 없으면 종료
            if(!end())break;
        }

        answer=Math.max(answer,monster);
    }

    private static void Line() {
        for (int i = Y-2; 0<=i; i--) {
            int[] tmp=new int[X];

            // 한라인 복사 후 다음 라인에 넘기기
            for (int j = 0; j < X; j++) {
                tmp[j]=useMap[i][j];
            }

            for (int j = 0; j < X; j++) {
                useMap[i+1][j]=tmp[j];
            }

        }

        // 첫줄 0으로 만들기
        for (int i = 0; i < X; i++) {
            useMap[0][i]=0;
        }


    }



    private static void attack(List<Integer> archer) {
        List<int[]> list=new ArrayList<>();
        // 각 아처 위치마다 가장 거리가 작은 몬스터 위치를 찾아냄
        for (int i = 0; i <archer.size(); i++) {

            int d=Integer.MAX_VALUE;
            int[] yx=new int[2];
            yx[0]=Integer.MAX_VALUE;
            yx[1]=Integer.MAX_VALUE;

            for (int j = 0; j < Y; j++) {
                for (int k = 0; k < X; k++) {
                    // 현재 위치에 몬스터가 없으면 계산 필요 x
                    if(useMap[j][k]==0)continue;

                    int distance=Math.abs(j-Y)+Math.abs(k-archer.get(i));

                    // 거리가 같다면 왼쪽으로 고정
                   if(distance<=D){
                       if(d==distance){
                           if(yx[1]>k){
                               yx[0]=j;
                               yx[1]=k;
                           }

                       }else if(distance<d){
                           yx[0]=j;
                           yx[1]=k;
                           d=distance;
                       }
                   }
                }
            }

            // 배열 yx에 들어있는 값이 현재 궁수의 위치에서 가장 짧은 거리에 몬스터 임으로 공격해서 몬스터 삭제 && 값 +1
            if(d!=Integer.MAX_VALUE){
                list.add(new int[] {yx[0],yx[1]});
            }

        }

        for (int i = 0; i < list.size(); i++) {
            if(useMap[list.get(i)[0]][list.get(i)[1]]!=0)monster+=1;
            useMap[list.get(i)[0]][list.get(i)[1]]=0;
        }
    }

    private static boolean end() {
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if(useMap[i][j]==1){
                    return true;
                }
            }
        }
        return false;
    }
}
