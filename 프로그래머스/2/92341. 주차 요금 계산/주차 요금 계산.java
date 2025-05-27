import java.util.*;

class Solution {
    static class Record{
        String time;
        int totalTime;
        String inAndOut;
        
        Record(String time,int totalTime,String inAndOut){
            this.time=time;
            this.totalTime=totalTime;
            this.inAndOut=inAndOut;
        }
    }
    
    public int[] solution(int[] fees, String[] records) {
        
        Map<Integer,Record> map=new TreeMap<>();
        
        for(String record:records){
            String[] tni=record.split(" ");
            
            String time=tni[0];
            int number=Integer.parseInt(tni[1]);
            String InAndOut=tni[2];
            
            Record newObj=new Record("",0,"");
            Record obj=map.getOrDefault(number,newObj);
            
            // 현재 번호의 값이 map에 없다면 내역을 넣어줌
            if(obj.inAndOut.equals("")){
                map.put(number,new Record(time,0,InAndOut));
            }else{
                // 차량이 이전에 입차한경우 -> 출고해야함
                if(obj.inAndOut.equals("IN")){
                    map.put(number,new Record(time,TimeCal(time)-TimeCal(obj.time)+obj.totalTime,"OUT"));
                }else{
                    map.put(number,new Record(time,obj.totalTime,"IN"));
                }
                
            }
        }
        
        map.forEach((key,value)->{
            if(value.inAndOut.equals("IN")){
                map.put(key,new Record(value.time,TimeCal("23:59")-TimeCal(value.time)+value.totalTime,"OUT"));
            }
        });
        
        int[] answer = new int[map.size()];
        
        int[] index=new int[1];
        index[0]=0;
        
        map.forEach((key,value)->{
            int totalPrice=fees[1];
            
            if(value.totalTime>fees[0]){
                totalPrice+=Math.ceil((double)(value.totalTime-fees[0])/fees[2])*fees[3];
            }
            
            answer[index[0]++]=totalPrice;
        });
        
        return answer;
    }
    
    public int TimeCal(String timeStr){
        String[] time=timeStr.split("\\:");
        return Integer.parseInt(time[0])*60+Integer.parseInt(time[1]);
    }
}