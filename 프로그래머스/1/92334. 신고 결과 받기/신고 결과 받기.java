import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        String[][] map=new String[2000][2000];
        for(int i=0;i<2000;i++){
            for(int j=0;j<2000;j++){
                map[i][j]="stop";
            }
        }
        
        
        for(int i=0;i<report.length;i++){
            String[] str=report[i].split(" ");
            
            String As=str[0];
            String To=str[1];
            
            int AsIndex=0;
            for(int j=0;j<id_list.length;j++){
                if(As.equals(id_list[j])){
                    AsIndex=j;
                    break;
                }
            }
            
            
            for(int j=0;j<2000;j++){
                // 이미 해당 사람이 중복된 사람의 신고를 진행했으면 pass
                if(map[AsIndex][j].equals(To)){
                    break;
                }else if(map[AsIndex][j].equals("stop")){
                    map[AsIndex][j]=To;
                    break;
                }
            }
        }
        
        int[] cnt=new int[id_list.length];
        L:for(int i=0;i<id_list.length;i++){
            for(int j=0;j<2000;j++){
                if(map[i][j].equals("stop"))continue L;                
                else{
                    int indexLoc=0;
                    for(int z=0;z<id_list.length;z++){
                        if(map[i][j].equals(id_list[z])){
                            indexLoc=z;
                            // break; 를 나중에 걸던 하기
                        }
                    }
                    
                    cnt[indexLoc]+=1;
                }
            }
        }
        
        L:for(int i=0;i<id_list.length;i++){
            for(int j=0;j<2000;j++){
                if(map[i][j].equals("stop"))continue L;                
                else{
                    int indexLoc=0;
                    for(int z=0;z<id_list.length;z++){
                        if(map[i][j].equals(id_list[z])){
                            indexLoc=z;
                        }
                    }
                    
                    if(cnt[indexLoc]>=k){
                        answer[i]+=1;
                    }
                }
            }
        }
        
        return answer;
    }
}