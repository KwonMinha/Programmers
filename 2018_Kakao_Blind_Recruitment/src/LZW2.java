/**
 * @author Minha Gwon
 * @date 2020. 9. 22.
 * 2018 KAKAO BLIND RECRUITMEN - [3차] 압축
 * https://programmers.co.kr/learn/courses/30/lessons/43162
 * BLOG - https://minhamina.tistory.com/56
 */

import java.util.*;

public class LZW2 {
	public static HashMap<String, Integer> map;
	public static ArrayList<Integer> list;
	public static int idx;
	
	public static void main(String[] args) {
		//String msg = "TOBEORNOTTOBEORTOBEORNOT";
		String msg = "KAKAO";

		int[] ans = solution(msg);
		System.out.println(Arrays.toString(ans));
	}

	public static int[] solution(String msg) {
		list = new ArrayList<>();
		map = new HashMap<>();
		idx = 27;

		for(int i = 1; i < 27; i++) {
			char alpha = (char) (64+i);
			map.put(String.valueOf(alpha), i);
		}    

		makeLZW(msg);

		int[] answer = new int[list.size()];
		for(int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}

		return answer;
	}

	public static void makeLZW(String msg) {
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i < msg.length(); i++) {
			sb.append(msg.charAt(i));
			
			if(!map.containsKey(sb.toString())) {
				map.put(sb.toString(), idx++);
				sb.deleteCharAt(sb.length()-1);
				list.add(map.get(sb.toString()));
				makeLZW(msg.substring(sb.length()));
				
				return;
			} else if(i == msg.length()-1) {
				list.add(map.get(sb.toString()));
			}
		}
	}
}

