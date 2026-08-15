
import java.io.*;
import java.util.*;

public class Main {
    static int r,c,k;
    static int[][] arr=new int[3][3];
    static class Node implements Comparable<Node>{
        int key;
        int value;

        Node(int key,int value){
            this.key=key;
            this.value=value;
        }

        @Override
        public int compareTo(Node n){
            if(n.value==this.value)return this.key-n.key;
            return this.value-n.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        for(int i=0;i<3;i++){
            st=new StringTokenizer(br.readLine());

            arr[i][0]=Integer.parseInt(st.nextToken());
            arr[i][1]=Integer.parseInt(st.nextToken());
            arr[i][2]=Integer.parseInt(st.nextToken());
        }

        int time=0;
        while(true){
            int Y=arr.length; // 행
            int X=arr[0].length; // 열

            if(Y>=r && X>=c && serach()){
                System.out.println(time);
                break;
            }

            time+=1;

            if(Y>=X)R();
            else C();

            if(time>100){
                System.out.println(-1);
                break;
            }

//            print();
        }

    }

    public static void R(){
        Queue<List<Node>> q=new ArrayDeque<>();
        int max=Integer.MIN_VALUE; // 나중에 *2 해줘야함 (객체의 key,value)

        for(int i=0;i<arr.length;i++){
            HashMap<Integer,Integer> map=new HashMap<>();

            for(int j=0;j<arr[0].length;j++){
                map.put(arr[i][j],map.getOrDefault(arr[i][j],0)+1);
            }

            List<Node> list=new ArrayList<>();
            int cnt=0; // 몇개의 쌍이 있는지 카운팅
            for(Integer key:map.keySet()){
                if(key!=0){
                    Integer value=map.get(key);
                    cnt+=1;
                    list.add(new Node(key,value));
                }
            }

            Collections.sort(list);
            q.offer(list);

            max=Math.max(max,cnt); // 최대 몇개의 객체가 있는지
        }

        // 행 또는 열의 맥스는 100임
        if(max>50)max=50;
        arr=new int[arr.length][max*2];

        int y=0;
        while(!q.isEmpty()){
            List<Node> list=q.poll();
            int x=0; // 몇개의 쌍이 됐는지

            for(int i=0;i<list.size();i++){
                arr[y][i*2]=list.get(i).key;
                arr[y][i*2+1]=list.get(i).value;
                x=i;
            }

            // max까지 못 채운 값들은 0으로 채움
            for(int i=x+1;i<max;i++){
                arr[y][i*2]=0;
                arr[y][i*2+1]=0;
            }

            y+=1;
        }
    }

    public static void C(){
        Queue<List<Node>> q=new ArrayDeque<>();
        int max=Integer.MIN_VALUE; // 나중에 *2 해줘야함 (객체의 key,value)

        for(int i=0;i<arr[0].length;i++){
            HashMap<Integer,Integer> map=new HashMap<>();

            for(int j=0;j<arr.length;j++){
                map.put(arr[j][i],map.getOrDefault(arr[j][i],0)+1);
            }

            List<Node> list=new ArrayList<>();
            int cnt=0; // 몇개의 쌍이 있는지 카운팅
            for(Integer key:map.keySet()){
                if(key!=0){
                    Integer value=map.get(key);
                    cnt+=1;
                    list.add(new Node(key,value));
                }
            }

            Collections.sort(list);
            q.offer(list);

            max=Math.max(max,cnt); // 최대 몇개의 객체가 있는지
        }

        // 행 또는 열의 맥스는 100임
        if(max>50)max=50;
        arr=new int[max*2][arr[0].length];

        int x=0;
        while(!q.isEmpty()){
            List<Node> list=q.poll();
            int y=0; // 몇개의 쌍이 됐는지

            for(int i=0;i<list.size();i++){
                arr[i*2][x]=list.get(i).key;
                arr[i*2+1][x]=list.get(i).value;
                y=i;
            }

            // max까지 못 채운 값들은 0으로 채움
            for(int i=y+1;i<max;i++){
                arr[i*2][x]=0;
                arr[i*2+1][x]=0;
            }

            x+=1;
        }
    }

    public static boolean serach(){
        if(arr[r-1][c-1]==k){
            return true;
        }

        return false;
    }

    public static void print(){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.printf(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
