/**
 * @author Minha Gwon
 * @date 2021. 6. 17.
 * Level2 - 완전탐색 - 카펫 
 * https://programmers.co.kr/learn/courses/30/lessons/42842
 */

import java.util.*;

public class Carpet {

	public static void main(String[] args) {
		int[] ans = solution(24, 24);
		System.out.println(Arrays.toString(ans));
	}

	public static int[] solution(int brown, int yellow) {
		int[] answer = new int[2];

		for(int i = 1 ; i <= Math.sqrt(yellow); i++) { // i*i <= yellow;
			if(yellow % i == 0) {
				if((i + 2) * (yellow / i + 2) == yellow + brown) {
					int a = i + 2;
					int b = yellow / i + 2;

					answer[0] = Math.max(a, b);
					answer[1] = Math.min(a, b);
					break;
				}
			}
		}

		return answer;
	}

}
