package 싸피11;

import java.util.Scanner;

public class 화분 {
}


class Solution
{
    static int[] p1,p2;
    static int N,P,answer=Integer.MIN_VALUE;
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
            N=sc.nextInt();
            P=sc.nextInt();

            p1=new int[N];
            p2=new int[N];

            for(int i=0;i<N;i++){
                p1[i]=sc.nextInt();
            }

            for(int i=0;i<N;i++){
                p2[i]=sc.nextInt();
            }

            DFS(true,1,p1[0]);
            DFS(false,1,p2[0]);

            // 표준출력(화면)으로 답안을 출력합니다.
            System.out.println("#" + test_case+" "+answer);
            answer=Integer.MIN_VALUE;
        }

        sc.close(); // 사용이 끝난 스캐너 객체를 닫습니다.
    }

    // 그 전에 어떤 비료가 들어갔는지,배열의 인덱스 번호가 몇번인지, 누적합은 몇인지
    private static void DFS(boolean preB, int index, int sum) {
        if(index==N){
            answer=Math.max(answer,sum);
            return;
        }

        // 이전에 p1을 썼다
        if(preB){
            DFS(true,index+1,sum+(p1[index]-P)); // 현재도 p1을 쓸거다
            DFS(false,index+1,sum+p2[index]); // 현재는 p2를 쓸거다
        }else{
            DFS(true,index+1,sum+p1[index]);
            DFS(false,index+1,sum+(p2[index]-P));
        }

    }
}
