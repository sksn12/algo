
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(bf.readLine());
		
		String[] strArr=st.nextToken().split("");
		
		ArrayList<String> arrList=new ArrayList<>();
		
		int cnt=0; // : 전까지 몇개의 문자가 있는지 
		int semiCnt=0; // 지금까지 몇개의 : 이 나왓는지
		int strArrLoc=0; // 원본 문자열의 위치 
		boolean semiVal=false; // : 이 연속되는지를 확인 하기 위한 벨리데이션 
		
		int semiTotalCnt=0; // 문자열에서 총 :의 갯수 
		for (int i = 0; i < strArr.length; i++) {
			if(strArr[i].equals(":"))semiTotalCnt+=1;
		}
		
		for (int i = 0; i < strArr.length; i++) {
			if(strArr[i].equals(":")) {
				semiCnt+=1;
				
				// : 연속되지 않음
				if(!semiVal) {
					int tmp=4-cnt;
					
					for (int j = 0; j <tmp; j++) {
						arrList.add("0");
					}
					
					for (int j = 0; j < cnt; j++) {
						arrList.add(strArr[strArrLoc++]);
					}
					
					arrList.add(":");
					cnt=0;
					
				} 
				
				// : 건너뛰기 
				strArrLoc+=1;
				
				// : 연속될 경우 
				if(semiVal) {
					int tmp=8-semiTotalCnt;
					for (int j = 0; j < tmp; j++) {
						for (int j2 = 0; j2 < 4; j2++) {
							arrList.add("0");
						}
						arrList.add(":");
					}
				}
				
				semiVal=true;
				
			}else {
				cnt+=1;
				semiVal=false;
			}
			
		}
		// 마지막 문자열 추가 
		int tmp=4-cnt;
		
		for (int j = 0; j <tmp; j++) {
			arrList.add("0");
		}
		
		for (int j = 0; j < cnt; j++) {
			arrList.add(strArr[strArrLoc++]);
		}
		
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < arrList.size(); i++) {
			sb.append(arrList.get(i));
		}
		System.out.println(sb);
	}
}
