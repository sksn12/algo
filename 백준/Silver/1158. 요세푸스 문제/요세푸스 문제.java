
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Queue<Integer> q=new LinkedList<>();
		
		String[] str=sc.nextLine().split(" ");
		int N=Integer.parseInt(str[0]);
		int M=Integer.parseInt(str[1]);
		
		for (int i = 1; i <= N; i++) q.add(i);
		
		StringBuilder st=new StringBuilder();
		st.append("<");
		while (1<q.size()) {
			for (int i = 0; i < M-1; i++) {
				q.add(q.poll());
			} 
			st.append(q.poll()+", ");
		}
		st.append(q.poll());
		st.append(">");
		
		System.out.println(st);
		
	}

}
