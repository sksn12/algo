import java.util.*;

class Solution {
    public List<int[]> solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer = {};
        ArrayList<int[]> list=new ArrayList<>();
        
        int num=0;
        if(ext.equals("date"))num=1;
        else if(ext.equals("maximum"))num=2;
        else if(ext.equals("remain"))num=3;
        
        int order=0;
        if(sort_by.equals("date"))order=1;
        else if(sort_by.equals("maximum"))order=2;
        else if(sort_by.equals("remain"))order=3;
        
        for(int i=0;i<data.length;i++){
            if(data[i][num]<val_ext){
                list.add(data[i]);
            }
        }
        
        
        final int ord=order;
        Collections.sort(list,(o1,o2)->o1[ord]-o2[ord]);
        
        
        return list;
    }
}