/**
 * @author Minha Gwon
 * @date 2021. 5. 7.
 * 2018 KAKAO BLIND RECRUITMENT - [3차] 파일명 정렬 
 * https://programmers.co.kr/learn/courses/30/lessons/17686
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class Cache {
	static class City {
		String name;
		int val;

		City(String name, int val) {
			this.name = name;
			this.val = val;
		}
	}
	
	static PriorityQueue<City> pq;

	public static void main(String[] args) {
		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		int[] stages2 = {4, 4, 4, 4, 4};

		int answer = solution(3, cities);
		System.out.println(answer);
	}

	public static int solution(int cacheSize, String[] cities) {
		int answer = 0;

		pq = new PriorityQueue<>(new Comparator<City>() {

			@Override
			public int compare(Cache.City o1, Cache.City o2) {
				return o1.val - o2.val;
			}

		});

		for(int i = 0; i < cities.length; i++) {
			if(pq.size() != cacheSize) {
				pq.add(new City(cities[i], 1));
			}
		}

		return answer;
	}
	
	public static void addTime() {
		
	}
}
