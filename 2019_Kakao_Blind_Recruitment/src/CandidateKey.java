/**
 * @author Minha Gwon
 * @date 2021. 1. 11.
 * 
 * 2019 카카오 신입 공채 - 후보키  
 * https://programmers.co.kr/learn/courses/30/lessons/42890
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CandidateKey {
	public static ArrayList<String> list = new ArrayList<>(); // 모든 후보키 조합 저장 
	public static ArrayList<List<String>> candidateKeys = new ArrayList<>(); // 유일성, 최소성 만족하는 후보키 저장 

	public static void main(String[] args) {
		String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
		System.out.println(solution(relation));
	}

	public static int solution(String[][] relation) {
		int tuple = relation.length; // 전체 튜플 수 
		int column = relation[0].length; // 총 애트리뷰트 수 
		boolean[] visited = new boolean[column]; // 조합을 뽑기 위한 visited 배열 
		
		String lastNum = "0"; // 마지막 후보키추가를 위한 변수 
		for(int i = 1; i < column; i++) { // 조합을 이용해 모든 후보키 조합 추출 
			comb(visited, 0, i);
			lastNum += i;
		}
		list.add(lastNum); // 마지막에 모든 속성의 집합으로 만든 후보키 추가 ex. 속성의 총 개수 4라면 0123

		// 후보키 유일성 판별
		for(int i = 0; i < list.size(); i++) {  
			HashSet<String> set = new HashSet<>(); // 중복 없이 저장하는 HashSet을 이용해 후보키 조합으로 뽑아진 튜플 저장 
			
			//System.out.println("key : " + list.get(i));
			String[] keys = list.get(i).split("");

			for(int j = 0; j < relation.length; j++) { // key에 해당하는 속성들만 뽑아서 문자열 형태로 만듬 
				String r = "";

				for(int k = 0; k < keys.length; k++) {
					r += relation[j][Integer.parseInt(keys[k])];
				}
				set.add(r);
			}

			if(set.size() == tuple) { //전체 튜플의 수와 후보키 조합으로 뽑아진 튜플의 수가 같다면 후보키 유일성 통과 
				// 후보키 최소성 판별 
				// containsAll 메서드 사용하기 위해 List 사용
				// 기존의 String 비교 contains 메서드 쓰면 123, 13을 포함하지 않음으로 판별하기 때문 
				List<String> cKey = Arrays.asList(list.get(i).split("")); 

				boolean flag = true;
				for(int j = 0; j < candidateKeys.size(); j++) {
					if(cKey.containsAll(candidateKeys.get(j))) { // 후보키 리스트에 부분집합으로 있다면 최소성 만족 X
						flag = false;
						break;
					}
				}

				if(flag) { // 어떠한 부분집합으로라도 없다면 최소성 통과 
					candidateKeys.add(cKey);
				}
			}
		}

		// 유일성과 최소성 판별을 통과한 후보키만을 담고 있는 후보키리스트의 사이즈가 바로 정답 
		return candidateKeys.size();
	}

	// 모든 후보키 조합을 뽑는 함수 
	// 애트리뷰트 수가 4일 경우, 0, 1, 2, 3, 01, 02, 03, ... 023, 123 String 형태로 뽑아짐 
	public static void comb(boolean[] visited, int start, int r) {
		if(r == 0) { 
			String num = "";
			for(int i = 0; i < visited.length; i++) {
				if(visited[i]) {
					num = num + i;
				}
			}

			list.add(num);

			return;
		} else {
			for(int i = start; i < visited.length; i++) {
				visited[i] = true;
				comb(visited, i + 1, r - 1);
				visited[i] = false;
			}
		}
	}
}
