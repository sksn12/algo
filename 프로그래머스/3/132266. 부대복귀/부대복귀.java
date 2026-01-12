import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] map=new ArrayList[n+1];
        Queue<int[]> q=new LinkedList<>();
        int[] destinations=new int[n+1];
        
        for(int i=1;i<=n;i++){
            map[i]=new ArrayList<>();
        }
        
        for(int i=0;i<roads.length;i++){
            map[roads[i][0]].add(roads[i][1]);
            map[roads[i][1]].add(roads[i][0]);
        }
        
        Arrays.fill(destinations,-1);

        q.offer(new int[] {destination,0});
        destinations[destination]=0;
        
        while(!q.isEmpty()){
            int[] node=q.poll();
            
            for(int i=0;i<map[node[0]].size();i++){
                if(destinations[map[node[0]].get(i)]==-1){
                    destinations[map[node[0]].get(i)]=node[1]+1;
                    
                    q.offer(new int[] {map[node[0]].get(i),node[1]+1});
                }
            }
        }
        
        int[] answer=new int[sources.length];
        int index=0;
        for(int source:sources){
            answer[index++]=destinations[source];
        }
        
        return answer;
    }
}