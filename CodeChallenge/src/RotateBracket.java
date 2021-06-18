/**
 * @author Minha Gwon
 * @date 2021. 6. 19.
 * 월간 코드 챌린지 시즌2 - 괄호 회전하기 
 * https://programmers.co.kr/learn/courses/30/lessons/76502
 */

import java.util.*;

public class RotateBracket {

	public static void main(String[] args) {
		System.out.println(solution("[](){}"));
	}

	public static int solution(String s) {
		int answer = 0;

		for(int i = 0; i < s.length(); i++) {
			String str = s.substring(i).concat(s.substring(0, i));

			if(check(str))  
				answer++;
		}

		return answer;
	}

	static boolean check(String s) {
		Stack<Character> stack = new Stack<>();

		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if(ch == '(' || ch == '{' || ch == '[') { // 여는 괄호일 경우 
				stack.push(ch);
			} else { // 닫는 괄호일 경우 
				if(stack.isEmpty()) { // 스택이 비어있는 경우
					return false;
				}

				if((ch == ')' && stack.peek() == '(') ||
			        (ch == '}' && stack.peek() == '{') ||
				     (ch == ']' && stack.peek() == '[')) {
					stack.pop();
				} else {
					return false;
				}
			} 
		}

		if(stack.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}