package 코드트리;

import java.io.*;
import java.util.*;

public class 민트초코우유 {
    static int N,T;
    static class Kind implements Comparable<Kind>{
        String name;
        int cnt;
        int y;
        int x;

        Kind(String name,int cnt,int y,int x){
            this.name=name;
            this.cnt=cnt;
            this.y=y;
            this.x=x;
        }

        @Override
        public int compareTo(Kind k){
            if(this.cnt==k.cnt){
                if(this.y==k.y)return this.x-k.x;
                return this.y-k.y;
            }
            return k.cnt-this.cnt;
        }
    }

    static class P implements Comparable<P>{
        String name;
        int cnt;
        int y;
        int x;

        P(String name,int cnt,int y,int x){
            this.name=name;
            this.cnt=cnt;
            this.y=y;
            this.x=x;
        }

        @Override
        public int compareTo(P p){
            if(p.name.length()==this.name.length()){
                if(this.cnt==p.cnt){
                    if(this.y==p.y)return this.x-p.x;
                    return this.y-p.y;
                }

                return p.cnt-this.cnt;
            }

            return this.name.length()-p.name.length();
        }
    }
    static Kind[][] map;
    static int[] dy={-1,1,0,0}; // 위 아래 왼 오
    static int[] dx={0,0,-1,1};
    static boolean[][] v;
    static Queue<Kind> q;
    static PriorityQueue<Kind> pq;
    static PriorityQueue<P> pq2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        T=Integer.parseInt(st.nextToken());

        map=new Kind[N][N];

        for(int i=0;i<N;i++){
            String[] str=br.readLine().split("");
            for(int j=0;j<N;j++){
                map[i][j]=new Kind(str[j],0,i,j);
            }
        }

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=new Kind(map[i][j].name,Integer.parseInt(st.nextToken()),i,j);
            }
        }

        for(int t=0;t<T;t++){
            // 1. 아침에는 전부 신앙심 +1;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    map[i][j]=new Kind(map[i][j].name,map[i][j].cnt+1,i,j);
                }
            }

            // 2.점심 그룹 나누기
            v=new boolean[N][N];
            q=new ArrayDeque<>();
            pq2=new PriorityQueue<>();
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(!v[i][j]){
                        pq=new PriorityQueue<>();
                        q.offer(new Kind(map[i][j].name,map[i][j].cnt,i,j));
                        pq.offer(new Kind(map[i][j].name,map[i][j].cnt,i,j));
                        v[i][j]=true;
                        BFS();

                        // pq에 담긴 그룹의 그룹장을 뽑고 해당 그룹장의 위치에는 pq.size()-1을 신암심에 더해줌
                        Kind k=pq.poll();
                        int size=map[k.y][k.x].cnt+pq.size();
                        map[k.y][k.x]=new Kind(k.name,size,k.y,k.x);

                        // 각 그룹의 대표자들을 모으기
                        pq2.offer(new P(k.name,size,k.y,k.x));

                        // 나머지 그룹원들 신암심 -1
                        while(!pq.isEmpty()){
                            Kind kk=pq.poll();
                            map[kk.y][kk.x]=new Kind(kk.name,map[kk.y][kk.x].cnt-1,kk.y,kk.x);
                        }


                    }
                }
            }

            // 3. 저녁시간 대표자들 신앙 전파
            dinner();

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    System.out.printf(map[i][j].name+" ");
                }
                System.out.println(" ");
            }
            System.out.println("======= ");

            // 4. 정답 출력

            HashMap<String,Integer> hashMap=new HashMap<>();
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    hashMap.put(map[i][j].name,hashMap.getOrDefault(map[i][j].name,0)+map[i][j].cnt);
                }
            }
            for(String key:hashMap.keySet()){
                System.out.printf(key+" "+hashMap.get(key)+" ");
            }
            System.out.println();


        }
    }

    public static void BFS(){
        while(!q.isEmpty()){
            Kind k=q.poll();

            for(int d=0;d<4;d++){
                int ny=k.y+dy[d];
                int nx=k.x+dx[d];

                if(0<= ny && 0<=nx && ny <N && nx<N && !v[ny][nx] && k.name.equals(map[ny][nx].name)){
                    v[ny][nx]=true;
                    q.offer(new Kind(k.name,map[ny][nx].cnt,ny,nx));
                    pq.offer(new Kind(k.name,map[ny][nx].cnt,ny,nx));
                }
            }
        }
    }

    public static void dinner(){
        while(!pq2.isEmpty()){
            P p=pq2.poll();
            System.out.println("대표자 "+ p.cnt+" : "+p.y+" "+p.x);
            int d=map[p.y][p.x].cnt%4;
            int x= p.cnt-1; // 간절함
            map[p.y][p.x].cnt=1;


            int ny=dy[d]+p.y;
            int nx=dx[d]+p.x;

            while(true){
                if(0<=ny && 0<=nx && ny<N && nx<N && x>0){
                    if(!map[ny][nx].name.equals(p.name)){
                        // 강한 전파
                        if(x>map[ny][nx].cnt){
                            map[ny][nx]=new Kind(p.name,map[ny][nx].cnt+1,ny,nx);
                            x-=map[ny][nx].cnt+1;
                        }
                        // 약한 전파
                        else{
                            String newName=map[ny][nx].name+p.name;
                            if(newName.length()>3)newName="TMC";
                            map[ny][nx]=new Kind(newName,map[ny][nx].cnt+x,ny,nx);
                            x=0;
                        }
                    }
                }else break;

                ny+=dy[d];
                nx+=dx[d];

//                System.out.println("전파방향 "+d);
//                for(int i=0;i<N;i++){
//                    for(int j=0;j<N;j++){
//                        System.out.printf(map[i][j].cnt+" ");
//                    }
//                    System.out.println(" ");
//                }
//                System.out.println("======= ");
            }

        }
    }
}
