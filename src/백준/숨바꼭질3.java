package 백준;

import java.io.*;
import java.util.*;

public class 숨바꼭질3 {
    static int N,K;
    static Queue<Node> q=new ArrayDeque<>();
    static class Node{
        int loc;
        int time;

        Node(int loc,int time){
            this.loc=loc;
            this.time=time;
        }
    }
    static boolean[] v=new boolean[100000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        q.offer(new Node(N,0));
        BFS();
    }

    public static void BFS(){
        while (!q.isEmpty()){
            Node n=q.poll();
            v[n.loc]=true;

            if(n.loc==K){
                System.out.println(n.time);
                return;
            }


            q.offer(new Node(n.loc+1, n.time+1));
            q.offer(new Node(n.loc-1, n.time+1));
            q.offer(new Node(n.loc*2, n.time));
        }
    }
}
