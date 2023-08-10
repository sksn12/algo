package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 회장뽑기 {
    static int N;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        list=new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            list[i]=new ArrayList<>();
        }


        while (true){
            st=new StringTokenizer(br.readLine());

            int f1=Integer.parseInt(st.nextToken());
            int f2=Integer.parseInt(st.nextToken());

            if(f1==-1 && f2==-1)break;

            list[f1].add(f2);
            list[f2].add(f1);
        }

        List<Integer> answer=new ArrayList<>();
        int min=Integer.MAX_VALUE;
        // 회장이 될수있는 값 찾기(가장낮은 점수경우 찾기)
        for (int i = 1; i <=N ; i++) {
            Queue<int[]> q=new LinkedList<>();
            boolean[] v=new boolean[N+1];
            int max=0;

            // 0 -> level
            q.offer(new int[] {i,0});
            while (!q.isEmpty()){
                int[] nc=q.poll();
                int n=nc[0];
                int cnt=nc[1];
                v[n]=true;

                for (int j = 0; j < list[n].size(); j++) {
                    // 아직 방문안했다면
                    if (!v[list[n].get(j)]) {
                        v[list[n].get(j)] = true;
                        q.offer(new int[]{list[n].get(j), cnt + 1});
                        max=Math.max(cnt+1,max);
                    }
                }
            }

            if(max<min){
                min=max;
                answer=new ArrayList<>();
                answer.add(i);
            }else if(max==min)answer.add(i);
        }

        System.out.println(min+" "+answer.size());
        Collections.sort(answer);
        for (int i:answer) {
            System.out.printf(i+" ");
        }
    }
}
