/**
 * @author Minha Gwon
 * @date 2021. 6. 11.
 * Level2 - 정렬 - 가장 큰 수 
 * https://programmers.co.kr/learn/courses/30/lessons/42746
 */

import java.util.*;

public class MaximumNumber {
	static boolean[] visited;
	static int[] output;
	static int max = 0;
	static String maxStr = "";

	public static void main(String[] args) {
		int[] numbers = {3, 30, 34, 5, 9};
		System.out.println(solution(numbers));
	}

	public static String solution(int[] numbers) {
		int n = numbers.length;

		visited = new boolean[n];
		output = new int[n];

		permutation(n, n, 0, numbers);

		return maxStr;
	}

	static void permutation(int r, int n, int depth, int[] numbers) {
		if(depth == r) {
			String str = "";

			for(int i = 0; i < output.length; i++) {
				str += output[i];
			}

			if(max < Integer.parseInt(str)) {
				max = Integer.parseInt(str);
				maxStr = str;
			}

			return;
		}

		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = numbers[i];
				permutation(r, n, depth + 1, numbers);
				visited[i] = false;
			}
		}
	}

}
