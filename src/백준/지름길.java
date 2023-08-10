package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 지름길 {
    static class node{
        int s=0;
        int e=0;
        int w=0;

        node(int s,int e,int w){
            this.s=s;
            this.e=e;
            this.w=w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int D=Integer.parseInt(st.nextToken());
        List<node> list=new ArrayList<>();


        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            int s=Integer.parseInt(st.nextToken());
            int e=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());

            if(e-s<=w || e>D)continue;

            list.add(new node(s,e,w));
        }


        Collections.sort(list,(o1, o2) -> {
           if(o1.s==o2.s)return o1.e-o2.e;
           else return o1.s-o2.s;
        });

        for (node n:list){
            System.out.println(n.s+" "+n.e+" "+n.w);
        }

        int[] d=new int[D+1];
        Arrays.fill(d,D+1);
        int idx=0;
        int move=0;
        d[0]=0;

        while(move<D){
            // 지름길 부터 배열에 넣어주고 지금길이 다 들어가면 원래 가는값과 지름길의 값을 비교해서 더 작은값을 넣어줌
            if(idx<list.size()){
                node n=list.get(idx);
                if(move==n.s){
                    d[n.e]=Math.min(d[n.e],d[move]+n.w);
                    idx+=1;
                }else{
                    d[move+1]=Math.min(d[move+1],d[move]+1);
                    move+=1;
                }
            }else{
                d[move+1]=Math.min(d[move+1],d[move]+1);
                move+=1;
            }
        }

        System.out.println(d[d.length-1]);

    }
}
