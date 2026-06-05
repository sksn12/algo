class Solution {
    public int[] solution(int n) {
        int[][] map = new int[n+1][n+1];
        
        int max=0;
        int[] dy={1,0,-1};
        int[] dx={-1,1,0};
        
        for(int i=1;i<=n;i++){
            max+=i;
        }
        
        int y=1;
        int x=n;
        int d=0;
        int num=1;
        map[y][x]=num++;
        
        while(num<=max){
            int ny=y+dy[d];
            int nx=x+dx[d];
            
            if(ny<=0 || nx <=0 || ny>n || nx> n){
                d+=1;
                if(d==3)d=0;
            }else{
                if(map[ny][nx]!=0){
                    d+=1;
                    if(d==3)d=0;
                }else{
                    map[ny][nx]=num++;
                    y=ny;
                    x=nx;
                }
            }
        }
        
        int loc=0;
        int[] answer=new int[max];
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(map[i][j]!=0)answer[loc++]=map[i][j];
            }
        }
        return answer;
    }
}