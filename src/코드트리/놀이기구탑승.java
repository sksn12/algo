package 코드트리;

import java.io.*;
import java.util.*;

public class 놀이기구탑승 {
    static int N;
    static int[][] map;
    static class Node implements Comparable<Node>{
        int like;
        int empty;
        int y;
        int x;

        Node(int like,int empty,int y,int x){
            this.like=like;
            this.empty=empty;
            this.y=y;
            this.x=x;
        }

        @Override
        public int compareTo(Node n){
            if(n.like==this.like){
                if(n.empty==this.empty){
                    if(n.y==this.y)return this.x-n.x;
                    return this.y-n.y;
                }
                return n.empty-this.empty;
            }
            return n.like-this.like;
        }
    }

    static PriorityQueue<Node> pq;

    static class Student{
        int student;
        int l1;
        int l2;
        int l3;
        int l4;

        Student(int student,int l1,int l2,int l3,int l4){
            this.student=student;
            this.l1=l1;
            this.l2=l2;
            this.l3=l3;
            this.l4=l4;
        }
    }

    static List<Student> students=new ArrayList<>();
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map=new int[N][N];

        for (int i = 0; i <N*N ; i++) {
            st=new StringTokenizer(br.readLine());
            int student=Integer.parseInt(st.nextToken());
            int l1=Integer.parseInt(st.nextToken());
            int l2=Integer.parseInt(st.nextToken());
            int l3=Integer.parseInt(st.nextToken());
            int l4=Integer.parseInt(st.nextToken());

            students.add(new Student(student,l1,l2,l3,l4));
            pq=new PriorityQueue<>();

            // map의 모든 위치에서 사방에 비어있는갯수가 몇개 인지, 좋아하는 사람이 몇명이 있는지 확인하고 pq에 넣음
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {

                    if(map[j][k]==0){
                        int like=0;
                        int empty=0;

                        for (int d = 0; d <4; d++) {
                            int ny=dy[d]+j;
                            int nx=dx[d]+k;

                            if(0<=ny && 0<=nx && ny<N && nx<N){
                                if(map[ny][nx]==0)empty+=1;
                                else if(map[ny][nx] == l1 || map[ny][nx] == l2 || map[ny][nx] == l3 ||map[ny][nx] == l4)like+=1;
                            }
                        }

                        pq.offer(new Node(like,empty,j,k));
                    }
                }
            }

            // pq에서 제일 첫번째로 나오는 위치가 현재 학생이 들어가야할 위치
            Node n=pq.poll();
            map[n.y][n.x]=student;
        }

        int answer=0;
        // map을 순회하면서 각 학생들이 좋아하는 학생옆에 얼마나 앉았는지로 점수 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                for (int k = 0; k < students.size(); k++) {
                    if(students.get(k).student==map[i][j]){
                        Student s=students.get(k);
                        int like=0;

                        for (int d = 0; d < 4; d++) {
                            int ny=dy[d]+i;
                            int nx=dx[d]+j;

                            if(0<=ny && 0<=nx && ny<N && nx<N){
                                if(map[ny][nx] == s.l1 || map[ny][nx] == s.l2 || map[ny][nx] == s.l3 ||map[ny][nx] == s.l4)like+=1;
                            }
                        }

                        if(like==1)answer+=1;
                        else if(like==2)answer+=10;
                        else if (like==3) answer+=100;
                        else if (like==4)answer+=1000;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
