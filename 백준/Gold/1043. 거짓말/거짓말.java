import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] true_person=new boolean[N+1];

        st=new StringTokenizer(br.readLine());

        int true_person_cnt=Integer.parseInt(st.nextToken());

        for (int i = 0; i < true_person_cnt; i++) {
            int true_person_number=Integer.parseInt(st.nextToken());

            true_person[true_person_number]=true;
        }

        List<Integer>[] party_list=new List[M];
        for (int i = 0; i < M; i++) {
            party_list[i]=new ArrayList<>();
        }


        // 1. 각 파티에 참가하는 사람들 중, 진실을 알고 있는 사람이 있다면 해당 파티에 있는 모든 사람들은 진실을 들어야함
        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());

            int party_person_cnt=Integer.parseInt(st.nextToken());

            boolean true_person_val=false;
            for (int j = 0; j < party_person_cnt; j++) {
                int person=Integer.parseInt(st.nextToken());

                if(true_person[person])true_person_val=true;

                party_list[i].add(person);
            }

            if(true_person_val){
                for (int j = 0; j < party_list[i].size(); j++) {
                    int true_person_number=party_list[i].get(j);
                    true_person[true_person_number]=true;
                }
            }
        }

        // 2. 재귀를 돌리지 않아 혹시모를 뒷순서의 예외를 처리하기 위해 1번 반복
        boolean updated = true;
        while (updated) {
            updated = false;
            for (int i = 0; i < M; i++) {
                boolean true_person_found = false;
                for (int person : party_list[i]) {
                    if (true_person[person]) {
                        true_person_found = true;
                        break;
                    }
                }
                if (true_person_found) {
                    for (int person : party_list[i]) {
                        if (!true_person[person]) {
                            true_person[person] = true;
                            updated = true; // 이번 반복에서 새로 진실을 알게 된 사람이 있음
                        }
                    }
                }
            }
        }

        int answer=0;
        // 3. 파티 다시 순회하면서 해당 파티에 진실을 모르는 사람들만 있다면 정답 +1
        for (int i = 0; i < M; i++) {
            boolean true_person_val=false;
            for (int j = 0; j < party_list[i].size(); j++) {
                if(true_person[party_list[i].get(j)])true_person_val=true;
            }

            if(!true_person_val){
                answer+=1;
            }
        }

        System.out.println(answer);
    }

}
