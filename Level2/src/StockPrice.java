/**
 * @author Minha Gwon
 * @date 2021. 6. 10.
 * Level2 - 스택/큐 - 주식 가격 
 * https://programmers.co.kr/learn/courses/30/lessons/42584
 */

import java.util.*;

public class StockPrice {

	public static void main(String[] args) {
		int[] prices = {1, 2, 3, 2, 3, 1};
		int[] ans = solution(prices);
		System.out.println(Arrays.toString(ans));
	}

	public static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

		Stack<Integer> stack = new Stack<>();
		stack.push(prices[prices.length-1]);
		answer[prices.length-1] = 0;

		for(int i = prices.length-2; i >=0 ; i--) {
			if(stack.peek() >= prices[i]) {
				answer[i] = stack.size();
			} else {
				answer[i] = 1;
			}
			stack.push(prices[i]);

		}

		return answer;
	}

}
