package 싸피11;

import java.util.Arrays;
import java.util.Scanner;

public class 야구선수 {
    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 sample_input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 sample_input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		*/
        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		*/
        Scanner sc = new Scanner(System.in);

		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {

            int N=sc.nextInt();
            int K=sc.nextInt();
            int answer=1;

            int[] map=new int[N];

            for(int i=0;i<N;i++){
                map[i]=sc.nextInt();
            }

            Arrays.sort(map);

            L:for (int i = 0; i < N; i++) {
                for(int j=N-1;j>=0;j--){
                    if(Math.abs(map[i]-map[j])<=K){
                        int tmp=j-i+1;
                        answer=Math.max(tmp,answer);
                        continue L;
                    }
                }
            }


            System.out.println("#" + test_case+" "+answer);
        }

        sc.close(); // 사용이 끝난 스캐너 객체를 닫습니다.
    }
}




