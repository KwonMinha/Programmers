/**
 * @author Minha Gwon
 * @date 2021. 1. 30.
 * 
 * 2020 카카오 인턴십 - 보석 쇼핑 
 * https://programmers.co.kr/learn/courses/30/lessons/67258
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class JewelryShopping {

	public static void main(String[] args) {
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};

		System.out.println(Arrays.toString(solution(gems)));
	}

	public static int[] solution(String[] gems) {
		HashSet<String> set = new HashSet<>();
		HashMap<String, Integer> map = new HashMap<>();
		
		for(String s : gems) { // HashSet을 이용해 보석의 중복 제거 
			set.add(s);
		}
		
		Queue<String> queue = new LinkedList<>();
		int start = 0;
		int end = Integer.MAX_VALUE;
		
		int startPoint = 0;
		for(int i = 0; i < gems.length; i++) {
			map.put(gems[i], map.getOrDefault(gems[i], 0) + 1); // map에 없다면 1로, 있다면 값에 +1 로 저장 
			queue.add(gems[i]);
			
			while(true) {
				String gem = queue.peek();
				if(map.get(gem) > 1) { // 보석이 중복으로 나온다면 
					queue.poll();
					start++; // 다음 큐에있는 값으로 새롭게 스타트 시작 
					map.put(gem, map.get(gem)-1);
				} else {
					break;
				}
			}
			
			if(map.size() ==  set.size() && queue.size() < end) { // 모든 보석이 선택되었고, 이전의 end값보다 짧은 구간일 때 
				end = queue.size();
				startPoint = start; // 스타트 포인트 변경 
			}
		}
		
		return new int[]{startPoint + 1, startPoint + end};
	}
}
