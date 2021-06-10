/**
 * @author Minha Gwon
 * @date 2021. 6. 10.
 * Level2 - 스택/큐 - 주식 가격 
 * https://programmers.co.kr/learn/courses/30/lessons/42584
 */

import java.util.*;

public class StockPrice {

	public static void main(String[] args) {
		int[] prices = {1, 2, 3, 2, 3};
		int[] ans = solution(prices);
		System.out.println(Arrays.toString(ans));
	}

	public static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

//		for(int i = 0; i < prices.length; i++) {
//			int cnt = 0;
//			
//			for(int j = i+1; j < prices.length; j++) {
//				if(prices[i] > prices[j]) {
//					cnt++;
//					break;
//				} else {
//					cnt++;
//				}
//			}
//			answer[i] = cnt;
//		}
		
		for(int i = 0; i < prices.length; i++) {
			for(int j = i+1; j < prices.length; j++) {
				answer[i]++;
				if(prices[i] > prices[j]) {
					break;
				} 
			}
		}

		return answer;
	}

}
