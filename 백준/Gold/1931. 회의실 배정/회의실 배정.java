
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Time implements Comparable<Time>{
        int start;
        int end;

        Time(int start,int end){
            this.start=start;
            this.end=end;
        }

        @Override
        public int compareTo(Time t) {
            if (this.end == t.end) {
                return this.start - t.start;
            }
            return this.end - t.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        Time[] schedule=new Time[N];

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine());

            int s=Integer.parseInt(st.nextToken());
            int e=Integer.parseInt(st.nextToken());

            schedule[i]=new Time(s,e);
        }

        Arrays.sort(schedule);

        int count = 0;
        int lastEndTime = 0; // 마지막으로 선택된 회의의 종료 시간

        for (int i = 0; i < N; i++) {
            int current_start = schedule[i].start;
            int current_end = schedule[i].end;

            // 현재 회의의 시작 시간이 이전에 선택된 회의의 종료 시간과 겹치지 않는다면
            // (끝나는 시간 <= 다음 시작 시간)
            if (lastEndTime <= current_start) {
                lastEndTime = current_end; // 새로운 종료 시간으로 갱신
                count++;                   // 회의 개수 증가
            }
        }

        System.out.println(count);
    }
}
