/**
 * @author Minha Gwon
 * @date 2021. 6. 11.
 * Level2 - 정렬 - 가장 큰 수 
 * https://programmers.co.kr/learn/courses/30/lessons/42746
 */

import java.util.*;

public class MaximumNumber {

	public static void main(String[] args) {
		int[] numbers = {3, 30, 34, 5, 9};
		System.out.println(solution(numbers));
	}

	public static String solution(int[] numbers) {
		String[] num = new String[numbers.length];
		for(int i = 0; i < numbers.length; i++) {
			num[i] = String.valueOf(numbers[i]);
		}

		Arrays.sort(num, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return (o2 + o1).compareTo(o1 + o2);
			}
		});

		String answer = "";
		for(int i = 0; i < num.length; i++) {
			answer += num[i];
		}
		
		if(answer.charAt(0) == '0') { // 0000 같은 경우 예외 처리 
			return "0";
		}

		return answer;
	}

}
