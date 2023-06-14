import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());

        char[][] map=new char[N][N];
        boolean val=false;
        int startY=0;
        int startX=0;

        for (int i = 0; i < N; i++) {
            String str=br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j]=str.charAt(j);
                if(!val && map[i][j]=='*'){
                    startX=j;
                    startY=i;
                    val=true;
                }
            }
        }
        // 왼쪽 팔 찾기
        int leftArm=0;
        for (int i = startX-1; 0<=i; i--) {
            if(map[startY+1][i]=='*')leftArm+=1;
            else break;
        }

        // 오른 팔 찾기
        int rightArm=0;
        for (int i = startX+1; i<N; i++) {
            if(map[startY+1][i]=='*')rightArm+=1;
            else break;
        }

        // 허리 찾기
        int hug=0;
        for (int i = startY+2; i<N; i++) {
            if(map[i][startX]=='*')hug+=1;
            else break;
        }

        // 왼쪽 다리 찾기
        int leftLeg=0;
        for (int i = startY+hug+2; i < N; i++) {
            if(map[i][startX-1]=='*')leftLeg+=1;
            else break;
        }

        // 오른쪽 다리 찾기
        int rightLeg=0;
        for (int i = startY+hug+2; i < N; i++) {
            if(map[i][startX+1]=='*')rightLeg+=1;
            else break;
        }


        System.out.println((startY+2)+" "+(startX+1));
        System.out.println(leftArm+" "+rightArm+" "+hug+" "+leftLeg+" "+rightLeg);
    }
}
