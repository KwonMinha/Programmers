/**
 * @author Minha Gwon
 * @date 2021. 6. 16.
 * Level2 - 정렬 - H-Index
 * https://programmers.co.kr/learn/courses/30/lessons/42747
 */

import java.util.Arrays;

public class HIndex {

	public static void main(String[] args) {
		int[] citations = {3, 0, 6, 1, 5};
		System.out.println(solution(citations));
	}

	public static int solution(int[] citations) {
		Arrays.sort(citations);
		int h = 0;
		int len = citations.length;
		for(int i = 0; i < citations.length; i++) {
			if(h < citations[i] && citations[i] < len) {
				if(len-i > citations[i]) {
					h = citations[i];
				} else {
					h++;
				}
			}
		}

		return h;
	}

}
