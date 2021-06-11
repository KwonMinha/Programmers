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
				int n1 = Integer.parseInt(o1+o2);
				int n2 = Integer.parseInt(o2+o1);

				if(n1 <= n2) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		
		String answer = "";
		for(int i = 0; i < num.length; i++) {
			answer += num[i];
		}

		return answer;
	}

}
