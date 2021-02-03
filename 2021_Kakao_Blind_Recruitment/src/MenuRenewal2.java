/**
 * @author Minha Gwon
 * @date 2021. 2. 4.
 * 
 * 2021 카카오 신입 공채 - 메뉴 리뉴얼 
 * https://programmers.co.kr/learn/courses/30/lessons/72411
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MenuRenewal2 {
	public static HashMap<String, Integer> map;
	public static int max;

	public static void main(String[] args) {
		//		{"XYZ", "XWY", "WXA"}	{2,3,4}

		//		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		//		int[] course = {2,3,4};

		String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
		int[] course = {2,3,5};

		System.out.println(Arrays.toString(solution(orders, course)));
	}

	public static String[] solution(String[] orders, int[] course) {
		ArrayList<String> answerList = new ArrayList<>();

		for(int i = 0; i < course.length; i++) {
			map = new HashMap<>();
			max = Integer.MIN_VALUE;

			// 모든 주문에 대해 course 길이만큼의 조합을 뽑음
			for(int j = 0; j < orders.length; j++) 
				combination(orders[j], "", course[i], 0);

			// 뽑힌 조합들 중 max만큼 즉, 가장 많이 주문된 조합이고 2명 이상의 손님이 주문한 조합이면 정답 리스트에 추가 
			for(String key : map.keySet()) {
				if(map.get(key) == max && max > 1) 
					answerList.add(key);
			}
		}

		Collections.sort(answerList); // 알파벳순 오름차순 정렬 
		String[] answer = answerList.toArray(new String[answerList.size()]);

		return answer;
	}

	// 메뉴 조합 뽑기 
	public static void combination(String order, String comb, int r, int start) {
		if(r == 0) {
			// 뽑은 조합 알파벳 오름차순으로 정렬 
			char[] c = comb.toCharArray();
			Arrays.sort(c);
			String temps = "";
			for (int i = 0; i < c.length; i++)
				temps += c[i];

			// map에 저장 / 이미 key가 있다면 value에 +1 해서 저장 
			map.put(temps, map.getOrDefault(temps, 0) + 1);
			max = Math.max(max, map.get(temps));

			return;
		} 
		for(int i = start; i < order.length(); i++) {
			combination(order, comb+order.charAt(i) ,r-1, i+1);
		}
	}
	
}
