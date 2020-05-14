/**
 * @author Minha Gwon
 * @date 2020. 5. 15.
 * 타겟 넘버(Level 2)
 * https://programmers.co.kr/learn/courses/30/lessons/43165
 */

import java.util.LinkedList;

public class TargetNumber {
	static int[] numbers = {1, 1, 1, 1, 1};
	static int target = 3;
	static int answer = 0;

	public static void main(String[] args) {
		System.out.println(solution(numbers, target));
	}

	public static int solution(int[] numbers, int target) {
		int r = numbers.length;
		String[] oper = {"+", "-"};
		LinkedList<String> operList = new LinkedList();

		dfs(oper, operList, 2, r);

		return answer;
	}

	public static void dfs(String[] oper, LinkedList<String> operList, int n, int r) {
		if(operList.size() == r) {
			isTarget(operList);
			
			return;
		}

		for(int i = 0; i < n; i++) {
			operList.add(oper[i]);
			dfs(oper, operList,  n, r);
			operList.removeLast();
		}
	}

	public static void isTarget(LinkedList<String> operList) {
		double result = 0;
		for(int i = 0; i < operList.size(); i++) {
			if(operList.get(i).equals("+")) {
				result += numbers[i];
			} else {
				result -= numbers[i];
			}
		}
		
		if(result == target) {
			answer++;
		}
	}
}
