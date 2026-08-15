import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        
        HashMap<String,Integer> map=new HashMap<>();
        
        for(int i=0;i<players.length;i++){
            map.put(players[i],i);
        }
        
        for(int i=0;i<callings.length;i++){
            int Loc=map.get(callings[i]);
            
            String frontPlayer=players[Loc-1];
            String backPlayer=players[Loc];
            players[Loc-1]=backPlayer;
            players[Loc]=frontPlayer;
            
            map.put(backPlayer,Loc-1);
            map.put(frontPlayer,Loc);
            
        }
        
        return players;
    }
}