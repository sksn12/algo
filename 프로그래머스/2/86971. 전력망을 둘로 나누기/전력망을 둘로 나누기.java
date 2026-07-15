import java.util.*;

class Solution {
    static List<Integer>[] map;
    static Queue<Integer> q;
    static boolean[] v;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        map=new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            map[i]=new ArrayList<>();
        }
        
        for(int i=0;i<wires.length;i++){
            map[wires[i][0]].add(wires[i][1]);
            map[wires[i][1]].add(wires[i][0]);
        }
          
        for(int[] wire:wires){
            int v1=wire[0];
            int v2=wire[1];
            
            map[v1].remove(Integer.valueOf(v2));
            map[v2].remove(Integer.valueOf(v1));
            
            q=new LinkedList<>();
            v=new boolean[n+1];
            v[v1]=true;
            q.offer(v1);
            int cnt=BFS(1);
            int cnt2=n-cnt;
            
            answer=Math.min(answer,Math.abs(cnt-cnt2));
            
            map[v1].add(v2);
            map[v2].add(v1);
        }
        
        return answer;
    }
    
    public int BFS(int cnt){
        
        while(!q.isEmpty()){
            int n1=q.poll();
            
            for(int n2=0;n2<map[n1].size();n2++){
                if(!v[map[n1].get(n2)]){
                    v[map[n1].get(n2)]=true;
                    q.offer(map[n1].get(n2));
                    cnt+=1;
                }
            }
        }
        
        return cnt;
    }
}