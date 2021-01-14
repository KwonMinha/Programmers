/**
 * @author Minha Gwon
 * @date 2021. 1. 15.
 * 
 * 2018 카카오 신입 공채 - [1차] 뉴스 클러스터링
 * https://programmers.co.kr/learn/courses/30/lessons/17677
 * Blog - https://minhamina.tistory.com/108
 */

import java.util.ArrayList;
import java.util.Collections;

public class NewsClustering {

	public static void main(String[] args) {
		//FRANCE	french	16384
		//handshake	shake hands	65536
		//aa1+aa2	AAAA12	43690
		//E=M*C^2	e=m*c^2	65536

		String str1 = "E=M*C^2";
		String str2 = "e=m*c^2";

		System.out.println(solution(str1, str2));
	}

	public static int solution(String str1, String str2) {
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();

		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();

		ArrayList<String> union = new ArrayList<>();
		ArrayList<String> intersection = new ArrayList<>();

		// str1 다중 집합 만들기 
		for(int i = 0; i < str1.length()-1; i++) {
			char a = str1.charAt(i);
			char b = str1.charAt(i+1);

			// 문자만 가진 경우만 추가 
			if(Character.isLetter(a) && Character.isLetter(b)) {
				String str = Character.toString(a) + Character.toString(b);
				list1.add(str);
				union.add(str); // 우선 합집합에 집합1의 원소는 다 넣어 놓고, 나중에 집합2만 확인해서 추가 
			}
		}

		// str2 다중 집합 만들기 
		for(int i = 0; i < str2.length()-1; i++) {
			char a = str2.charAt(i);
			char b = str2.charAt(i+1);

			// 문자만 가진 경우만 추가 
			if(Character.isLetter(a) && Character.isLetter(b)) {
				list2.add(Character.toString(a) + Character.toString(b));
			}
		}

		boolean[] v1 = new boolean[list1.size()];
		boolean[] v2 = new boolean[list2.size()];

		// 합집합 구하기 
		for(int i = 0; i < list2.size(); i++) {
			boolean flag = false;
			for(int j = 0; j < list1.size(); j++) { // 이미 집합1의 원소가 합집합에 있음. 따라서 중복되지 않는 집합2의 원소만 추가할 것 
				if(list2.get(i).equals(list1.get(j)) && !v1[j]) { // 집합2의 원소가 집합1에 있고, 1집합 방문 체크 안된 원소라면 
					v1[j] = true; // 합집합에 넣지는 않고, 방문 체크만 함
					flag = true;
					break;
				}
			}

			if(!flag) { // 집합1에 원소가 있지만 중복되지 않은 원소, 집합2에만 있는 원소라면 추가 
				union.add(list2.get(i));
			}
		}

		// 교집합 구하기 
		for(int i = 0; i < list1.size(); i++) {
			for(int j = 0; j < list2.size(); j++) {
				if(list1.get(i).equals(list2.get(j)) && !v2[j]) { // 집합1의 원소가 집합2에 포함되고, 2집합 방문 체크 안된 원소라면 
					v2[j] = true; // 방문 체크 
					intersection.add(list1.get(i)); // 교집합 추가 
					break;
				}
			}
		}

		// 자카드 유사도 구하기 
		double jakard = 0;
	
		if(union.size() == 0)
			jakard = 1;	// 공집합일 경우 1
		else
			jakard = (double) intersection.size() / (double) union.size();

		return (int) (jakard * 65536);
	}

}
