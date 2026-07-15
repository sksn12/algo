package 백준;

import java.io.*;
import java.util.*;

public class 게리멘더링 {
    static int N;
    static List[] map;
    static int[] score;
    static int[] arr;
    static boolean[] val;
    static List<Integer> A;
    static List<Integer> B;
    static int answer=Integer.MAX_VALUE;
    static Queue<Integer> A_queue;
    static Queue<Integer> B_queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map=new List[N+1];
        score=new int[N+1];
        val=new boolean[N];
        arr=new int[N];

        for(int i=1;i<=N;i++){
            map[i]=new ArrayList<>();
            arr[i-1]=i;
        }

        st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            score[i+1]=Integer.parseInt(st.nextToken());
        }

        boolean test=false;
        // 1. 인집리스트로 map 생성
        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            int C=Integer.parseInt(st.nextToken());

            if(C!=0)test=true;

            for(int c=0;c<C;c++){
                map[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // * 예외처리 * -> 선거구를 나눌 수 있는 방법이 없는 경우
        if(!test){
            System.out.println(-1);
            return;
        }

        // 2. 부분집합 만들기
        recursive(0);
    }

    public static void recursive(int level){
        if(level==arr.length){
            A=new ArrayList<>();
            B=new ArrayList<>();

            for(int i=0;i<arr.length;i++){
                if(!val[i])A.add(arr[i]);
                else B.add(arr[i]);
            }

            if(A.size()==N)return;
            if(B.size()==N)return;

            A_queue=new LinkedList<>();
            B_queue=new LinkedList<>();

            A_queue.offer(A.get(0));
            boolean[] possible_path=new boolean[N];
            boolean[] v=new boolean[N];

            for(int i=0;i<A.size();i++){
                possible_path[A.get(i)-1]=true;
            }

            // 3. BFS로 각 선거구가 모두 이어져 있는지 확인
            BFS(A_queue,possible_path,v);


            for(int i=0;i<N;i++){
                if(possible_path[i]==v[i]);
            }

            return;
        }

        val[level]=true;
        recursive(level+1);
        val[level]=false;
        recursive(level+1);
    }


    public static void BFS(Queue<Integer> q,boolean[] possible_path,boolean[] v){
        while(!q.isEmpty()){
            int n=q.poll();

            for(int i=0;i<map[n].size();i++){
                if(possible_path[(int) map[n].get(i) -1] && !v[(int) map[n].get(i) -1]){
                    v[(int) map[n].get(i) -1]=true;
                    q.offer((int) map[n].get(i));
                }
            }
        }
    }
}
