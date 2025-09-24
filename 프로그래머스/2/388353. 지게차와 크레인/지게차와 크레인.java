import java.util.*;

class Solution {
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        String[][] map=new String[storage.length+2][storage[0].length()+2];
        
        for(int j=0;j<map[0].length;j++){
            map[0][j]="OUT";
        }
        
        for(int i=1;i<map.length-1;i++){
            String[] str=storage[i-1].split("");
            for(int j=0;j<map[0].length;j++){
                if(j==0 || j==map[0].length-1)map[i][j]="OUT";
                else map[i][j]=str[j-1];
            }
        }
        
        for(int j=0;j<map[0].length;j++){
            map[map.length-1][j]="OUT";
        }

        /* ======= [초기 셋팅] ======= */
        
        for(int z=0;z<requests.length;z++){
            String[] s=requests[z].split("");
            
            if(s.length>1){
                
                for(int i=0;i<map.length;i++){
                    for(int j=0;j<map[0].length;j++){
                        if(map[i][j].equals(s[0]))map[i][j]="OUT";
                    }
                }
            }
            else{
               List<int[]> list=new ArrayList<>();
               Queue<int[]> q=new ArrayDeque<>();
               boolean[][] v=new boolean[map.length][map[0].length];
               q.offer(new int[] {0,0});
               v[0][0]=true;
                
               while(!q.isEmpty()){
                   int[] yx=q.poll();
                   int y=yx[0];
                   int x=yx[1];
                   
                   for(int d=0;d<4;d++){
                       int ny=dy[d]+y;
                       int nx=dx[d]+x;
                       
                       if(0<=ny && 0<=nx && ny<map.length && nx<map[0].length && !v[ny][nx] ){
                           if(map[ny][nx].equals(s[0])){
                               list.add(new int[] {ny,nx});
                           }else if(map[ny][nx].equals("OUT")){
                               q.offer(new int[] {ny,nx});
                           }
                           
                           v[ny][nx]=true;
                           
                       }
                   }
               }
                
                for(int c=0;c<list.size();c++){
                    int[] yx=list.get(c);
                    map[yx[0]][yx[1]]="OUT";
                }
                
            }
        }
        
       
        
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(!map[i][j].equals("OUT"))answer+=1;
            }
        }
        return answer;
    }
}