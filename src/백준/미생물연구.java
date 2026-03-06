package 백준;
import java.io.*;
import java.util.*;


public class 미생물연구 {
    static int N,Q;
    static int[][] map;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static boolean[][] v;
    static Queue<Node> q;
    static HashMap<Integer,Integer> hashMap;
    static class Node{
        int y;
        int x;
        int seq;
        int area;

        Node(int y,int x,int seq,int area){
            this.y=y;
            this.x=x;
            this.seq=seq;
            this.area=area;
        }
    }

    static class Area implements Comparable<Area>{
        int seq;
        int area;

        Area(int seq,int area){
            this.seq=seq;
            this.area=area;
        }

        @Override
        public int compareTo(Area a){
            if(this.area==a.area)return this.seq-a.seq;

            return a.area-this.area;
        }
    }

    static PriorityQueue<Area> pq;

    static int sx,sy,ex,ey,seq;
    static int[][] newMap;
    static int nowArea;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        Q=Integer.parseInt(st.nextToken());

        map=new int[N][N];

        for(int t=0;t<Q;t++){
            st=new StringTokenizer(br.readLine());

            sx=Integer.parseInt(st.nextToken());
            sy=Integer.parseInt(st.nextToken());
            ex=Integer.parseInt(st.nextToken());
            ey=Integer.parseInt(st.nextToken());

            seq=t+1;

            // 1. 해당 영역 미생물 투입
            insert();

            // 2. 새로 투입되는 미생물 집합에 의해 기존 집합이 나눠지는지 확인 -> 만약 나눠지면 전부 삭제
            divide();

            // 3. 배양 용기 이동 전 pq에 영역의 넓이와 해당 시퀀스를 넣어줌
            pq=new PriorityQueue<>();
            v=new boolean[N][N];
            q=new ArrayDeque<>();
            nowArea=1;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(!v[i][j] && map[i][j]!=0){
                        v[i][j]=true;
                        q.offer(new Node(i,j,map[i][j],1));
                        BFS();

                        // nowArea는 BFS에서 탐색한 영역의 크기, 현재 시퀀스
                        pq.offer(new Area(nowArea,map[i][j]));
                    }
                }
            }

            // 4. pq에 들어있는 모든 영역을 새 배양 용기에 옮김
            newMap=new int[N][N];
            while(!pq.isEmpty()){
                Area a=pq.poll();
                move(a.seq);
            }

            copy();

        }
    }

    public static void move(int areaSeq){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(newMap[i][j]==0){

                    for(int z=0;z<N;z++){
                        for(int c=0;c<N;c++){
                            if(areaSeq==map[z][c]){
                                if(i+z<N && j+c<N) newMap[i+z][j+c]=areaSeq;
                            }
                        }
                    }

                }
            }
        }
    }
    public static void divide(){
        v=new boolean[N][N];
        q=new ArrayDeque<>();
        hashMap=new HashMap<>();

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]!=0 && !v[i][j]){
                    q.offer(new Node(i,j,seq,1));
                    hashMap.put(seq,hashMap.getOrDefault(hashMap.get(seq),0)+1);
                    v[i][j]=true;

                    BFS();
                }
            }
        }

        // hashMap에서 value가 2이상이면 영역이 2개로 나눠졌다는 뜻임으로 해당 구역 삭제
        for(int key:hashMap.keySet()){
            int value = hashMap.get(key);

            if(value>1){
                delete(key);
            }
        }
    }

    public static void delete(int key){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==key)map[i][j]=0;
            }
        }
    }
    public static void BFS(){
        while(!q.isEmpty()){
            Node n=q.poll();

            for(int d=0;d<4;d++){
                int ny=dy[d]+n.y;
                int nx=dx[d]+n.x;

                if(0<=ny && 0<=nx && ny<N && nx<N && map[ny][nx]==n.seq && !v[ny][nx]){
                    v[ny][nx]=true;
                    nowArea=n.area+1;
                    q.offer(new Node(ny,nx,n.seq,n.area+1));
                }
            }
        }
    }

    public static void insert(){
        for(int y=sy;y<ey;y++){
            for(int x=sx;x<ex;x++){
                map[y][x]=seq;
            }
        }

        System.out.println(seq);
    }

    public static void copy(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j]=newMap[i][j];
                System.out.printf(map[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("========");
    }
}
