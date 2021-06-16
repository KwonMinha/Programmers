/**
 * @author Minha Gwon
 * @date 2021. 6. 16.
 * Level2 - 정렬 - H-Index
 * https://programmers.co.kr/learn/courses/30/lessons/42747
 * 
 * 전체 논문중 많이 인용된 순으로 정렬한 후, 
   피인용수가 논문수와 같아지거나 피인용수가 논문수보다 작아지기 시작하는 숫자가 바로 나의 h가 됩니다. 
   출처: H-지수(H-Index) 란 무엇인가? ( https://www.ibric.org/myboard/read.php?Board=news&id=270333 )
 */

import java.util.Arrays;

public class HIndex {

	public static void main(String[] args) {
		int[] citations = {3, 0, 6, 1, 5};
		System.out.println(solution(citations));
	}

	public static int solution(int[] citations) {
		Arrays.sort(citations);
	
		for(int i = 0; i < citations.length; i++) {
			if(citations[i] >= citations.length-i) {
				return citations.length-i;
			} 
		}
		
		return 0;
	}

}
