import java.util.*;

class Solution {
    static char[][] map=new char[3][3];
    
    public int solution(String[] board) {
        int cntO=0;
        int cntX=0;
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                map[i][j]=board[i].charAt(j);
                if(map[i][j]=='O')cntO+=1;
                else if(map[i][j]=='X')cntX+=1;
            }
        }
    
        if(cntX>cntO || cntO-cntX>=2)return 0;
        
        // O로 한줄이 완성되어서 게임이 종료되었는데 X의갯수가 같거나 많으면 0
        if(Check('O',false) && cntX==cntO)return 0;
        
        // X로 한줄 완성되면 X의갯수와 O의 갯수가 무조건 같아야함
        if(Check('X',false) && cntX!=cntO)return 0;
        
        return 1;
    }
    
    static boolean Check(char c,boolean val){
        // 가로
        for(int i=0;i<3;i++){
            if(map[i][0]==c && map[i][1]==c && map[i][2]==c)return true;
        }
        
        // 세로
        for(int i=0;i<3;i++){
            if(map[0][i]==c && map[1][i]==c && map[2][i]==c)return true;
        }
        
        // 왼쪽 대각
        if(map[0][0]==c && map[1][1]==c && map[2][2]==c)return true;

        // 오른쪽 대각
        if(map[0][2]==c && map[1][1]==c && map[2][0]==c)return true;
        
        return false;
    }
}