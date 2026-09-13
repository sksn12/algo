
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Vertex>[] adj;
	
	static class Vertex implements Comparable<Vertex>{
		int e,w; //노드 번호와 무게
		
		Vertex(int e,int w){
			this.e=e;
			this.w=w;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(bf.readLine());
		
		int V=Integer.parseInt(st.nextToken());
		int E=Integer.parseInt(st.nextToken());
		
		adj=new ArrayList[V];
		
		for (int i = 0; i < adj.length; i++) {
			adj[i]=new ArrayList<Vertex>();
		}
		
		for (int i = 0; i <E; i++) {
			st=new StringTokenizer(bf.readLine());
			int e=Integer.parseInt(st.nextToken()); 
			int e2=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			
			// Vertex객체 생성시에 현재 내 노드가 어떤 노드를 가르키고 있는지 + 가중치를 넣어줌
			adj[e-1].add(new Vertex(e2-1, w));
			adj[e2-1].add(new Vertex(e-1, w));
		}
		
		// 힙정렬을 위해 pq생성
		PriorityQueue<Vertex> pq=new PriorityQueue<>();
		
		boolean[] v=new boolean[V];
		
		v[0]=true;
		
		pq.addAll(adj[0]);
		
		int cnt=1;
		
		int result=0;
		
		while(cnt<V){
			Vertex p=pq.poll();
			if(!v[p.e]) {
				v[p.e]=true;
				result+=p.w;
				cnt++;
				pq.addAll(adj[p.e]);
			}
		}
		System.out.println(result);
	}
	
}
