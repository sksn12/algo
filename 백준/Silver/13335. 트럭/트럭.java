
import java.io.*;
import java.util.*;

public class Main {
    static int N,W,L; // 트럭의 수, 다리의 길이, 최대하중
    static class Track{
        int weight; // 트럭의 무게
        int time; // 트럭이 현재 몇초가 지났는지

        Track(int weight,int time){
            this.weight=weight;
            this.time=time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        W=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());

        Queue<Track> waiting=new ArrayDeque<>();
        for(int i=0;i<N;i++){
            int weight=Integer.parseInt(st.nextToken());
            waiting.add(new Track(weight,0));
        }

        int total_time=0;
        int bridge_total_weight=0;
        Queue<Track> bridge=new ArrayDeque<>();
        while(true){
            total_time+=1;

            // 다리위에 있는 트럭들을 순회하면서 1초를 더햇을때 다리의 길이를 초과하면 탈출!
            int size=bridge.size();
            bridge_total_weight=0;
            for(int i=0;i<size;i++){
                Track t=bridge.poll();
                if(t.time+1<=W){
                    bridge_total_weight+=t.weight;
                    bridge.add(new Track(t.weight,t.time+1));
                }
            }

            if(waiting.size()>0){
                Track now_track=waiting.peek();
                int sum_total_weight=bridge_total_weight+now_track.weight;

                // 대기하고 있던 제일 앞의 트럭을 꺼내서 다리위에 올림
                if(sum_total_weight<=L){
                    Track track=waiting.poll();
                    bridge.add(new Track(track.weight,1));
                }
            }

            // while 탈출 조건 -> 모든 트럭이 다리를 지나갔을떄
            if(bridge.size()==0 && waiting.size()==0)break;
        }

        System.out.println(total_time);
    }
}
