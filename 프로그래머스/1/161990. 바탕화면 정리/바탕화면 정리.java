class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        
        int Y=wallpaper.length;
        int X=wallpaper[0].length();
        
        answer[0]=0;
        answer[1]=0;
        answer[2]=0;
        answer[3]=0;
        
        String[][] map=new String[Y][X];
        for(int i=0;i<Y;i++){
            String[] str=wallpaper[i].split("");
            for(int j=0;j<X;j++){
                map[i][j]=str[j];
            }
        }
        
        L : for(int i=0;i<Y;i++){
            for(int j=0;j<X;j++){
                if(map[i][j].equals("#")){
                    answer[0]=i;
                    break L;
                }
            }
        }
        
        L :for(int j=0;j<X;j++){
            for(int i=0;i<Y;i++){
                if(map[i][j].equals("#")){
                    answer[1]=j;
                    break L;
                }
            }
        }
            
        L : for(int i=Y-1;i>=0;i--){
            for(int j=X-1;j>=0;j--){
                if(map[i][j].equals("#")){
                    answer[2]=i+1;
                    break L;
                }
            }
        }
        
        L: for(int j=X-1;j>=0;j--){
            for(int i=Y-1;i>=0;i--){
                if(map[i][j].equals("#")){
                    answer[3]=j+1;
                    break L;
                }
            }
        }
        
        
        return answer;
    }
}

// maxY -> for문으로 찾는 가장 위의 # y
// maxX->       ``             # 