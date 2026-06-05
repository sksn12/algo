package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 달력 {
    static int N;
    static List<Node> list=new ArrayList<>();
    static class Node implements Comparable<Node>{
        int s,e;

        Node(int s,int e){
            this.s=s;
            this.e=e;
        }

        @Override
        public int compareTo(Node n){
            if(n.s==this.s)return Math.abs(n.s-n.e) - Math.abs(this.s-this.e); // 시작일이 같으면 일정길이가 큰 순으로 정렬
            return this.s-n.s;
        }
    }

    static class Node2 implements Comparable<Node2>{
        int s,e,y;

        Node2(int s,int e,int y){
            this.s=s;
            this.e=e;
            this.y=y;
        }

        @Override
        public int compareTo(Node2 n){
            return this.s-n.s;
        }
    }
    static List<Node2> list2=new ArrayList<>();
    static boolean[][] map;
    static int min=Integer.MAX_VALUE;
    static int max=Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            int S=Integer.parseInt(st.nextToken());
            int E=Integer.parseInt(st.nextToken());

            list.add(new Node(S,E));
            min=Math.min(min,S);
            max=Math.max(max,E);
        }

        Collections.sort(list);

        map=new boolean[N+1][max+1];
        // map배열에 일정 넣기
        Input();

        // 모든 줄이 false인 라인을 만나기 전까지 크기
        int line=N;
        L:for (int i = 1; i <= N; i++) {
            for (int j = min; j <=max ; j++) {
                if(map[i][j])continue L;
            }
            line=i-1;
            break;
        }

        boolean val=false;
        int index=0;
        for (int i = min; i <= max; i++) {
            int cnt=0;
            for (int j = 1; j <=line ; j++) {
                if(map[j][i]){
                    cnt=j;
                }
            }
            // val== false일 경우만 새롭게 구간을 추가 아니면 기존 값을 가지고 비교
            if(cnt>0 && !val){
                list2.add(new Node2(i,i,cnt));
                val=true;
            }else if(cnt==0 && val) {
                val=false;
                index+=1;
            }else if(cnt>0 && val){
                Node2 n=list2.get(index);
                list2.set(index,new Node2(n.s,n.e+1,Math.max(cnt,n.y)));
            }
        }

        int answer=0;

        Collections.sort(list2);
        for (Node2 n:list2) {
            answer+=((n.e-n.s+1)*n.y);
        }

        System.out.println(answer);
    }

    private static void Input() {
        L2:for (Node n:list) {
           int s=n.s;
           int e=n.e;

           L: for(int i=1;i<=N;i++){

               // 시작 점 부터 끝점까지 map에서 현재 하나라도 사용했으면 해당 라인 사용불가!
               for (int j = s; j <= e; j++) {
                   if(map[i][j])continue L;
               }

               // 현재 y축에 일정이 사용가능하다면  true로 표기
               for (int j = s; j <= e; j++) {
                   map[i][j]=true;
               }
               continue L2; // 성공적으로 넣었으면 다음 일정으로 넘어가기
           }
        }
    }
}
