package 백준;


import java.io.*;
import java.util.*;

public class 생태학 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String,Integer> map=new TreeMap<>();

        int total_tree_cnt = 0;
        String tree_name;

        while ((tree_name = br.readLine()) != null) {
            map.put(tree_name, map.getOrDefault(tree_name, 0) + 1);
            total_tree_cnt++;
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            double percent = (entry.getValue() * 100.0) / total_tree_cnt;
            System.out.printf("%s %.4f%n", entry.getKey(), percent);
        }

    }
}
