/**
 * @author Minha Gwon
 * @date 2021. 5. 8.
 * 2018 KAKAO BLIND RECRUITMENT - [3차] N진수 게임 
 * https://programmers.co.kr/learn/courses/30/lessons/17687
 */

import java.util.ArrayList;
import java.util.Stack;

public class NGame {
	static ArrayList<String> numberList;

	public static void main(String[] args) {
		String answer = solution(16, 16, 2, 2);

		System.out.println(answer);
	}

	public static String solution(int n, int t, int m, int p) {
		String answer = "";

		int intNum = 0;

		// 튜브 순서 시작 전 튜브가 말할 숫자가 있도록 초기 리스트 만듬 
		numberList = new ArrayList<>();

		while(numberList.size() < p) {
			conversion(intNum, n);
			intNum++;
		}

		// 튜브 순서부터 시작 
		for(int i = p; i <= t * m; i += m) {
			while(numberList.size() < i) { // 튜브 순서에 말할 숫자가 없으면 추가 
				conversion(intNum, n);
				intNum++;
			}

			answer += numberList.get(i-1); // 튜브가 말할 순서와 숫자 리스트의 인덱스가 대응 (1부터 시작하니까 -1 해줌)
		}

		return answer;
	}

	public static void conversion(int value, int N){
		Stack<String> stack = new Stack<>();

		while(value > 0){
			if(value % N < 10) { // 나머지가 10 이상일때(즉 11진수 이상일때) 문자열 배당
				stack.push((value % N) + ""); 
			} else {
				stack.push((char)(value % N + 55) + ""); // 그외는 나머지 숫자값 넣기
			}
			value /= N;
		}

		// 입력수가 0일 때는 결과값 0
		if(stack.isEmpty()) { 
			numberList.add("0");
		}

		// 0이외의 값이라면 스택에서 값 추출 
		while(!stack.isEmpty()) {
			numberList.add(stack.pop());
		}
	}

	public static void conversion2(int value, int n) {
		StringBuilder sb = new StringBuilder();

		while(value > 0){
			if(value % n > 9) { // 나머지가 10 이상일때(즉 11진수 이상일때) 문자열 배당
				 sb.append((char) (value % n + 55) + "");
			} else { // 그외는 나머지 숫자값 넣기
				sb.append((value % n) + "");
			}
			value = value / n;
		}
		
		// 입력수가 0일 때는 결과값 0
		if(sb.toString().equals("")) {
			numberList.add("0");
		} else {
			String[] arr = sb.reverse().toString().split(""); // 거꾸로 출력해야 함 
			for(String s : arr) {
				numberList.add(s);
			}
		}

		//return sb.toString().equals("") ? "" : sb.reverse().toString();
	}

}
