
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st=new StringTokenizer(br.readLine());

    int N=Integer.parseInt(st.nextToken());
    int M=Integer.parseInt(st.nextToken());

    HashMap<String,Integer> hashMap=new HashMap<>();

    for (int i = 0; i <N ; i++) {
      st=new StringTokenizer(br.readLine());
      String str=st.nextToken();

      if(str.length()>=M){
        Integer cnt=hashMap.getOrDefault(str,0);
        hashMap.put(str,cnt+1);
      }
    }

    List<String> words=hashMap.keySet().stream().collect(Collectors.toList());

    words.sort((o1,o2)->{
      int v1=hashMap.get(o1);
      int v2=hashMap.get(o2);


      if(v1==v2){
        if(o1.length()==o2.length()){
          return o1.compareTo(o2);
        }
        return o2.length()-o1.length();
      }
      return v2-v1;
    });

   StringBuilder sb=new StringBuilder();
    for (int i = 0; i < words.size() ; i++) {
      sb.append(words.get(i)).append("\n");
    }
    System.out.println(sb);
  }
}
