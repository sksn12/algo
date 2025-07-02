import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int d = y - x;
            
            int n = (int) Math.sqrt(d);
            
            if (d == n * n) {
                System.out.println(2 * n - 1);
            } else if (d <= n * (n + 1)) {
                System.out.println(2 * n);
            } else {
                System.out.println(2 * n + 1);
            }
        }
    }
}
