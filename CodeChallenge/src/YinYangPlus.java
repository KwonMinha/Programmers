/**
 * @author Minha Gwon
 * @date 2021. 6. 19.
 * 월간 코드 챌린지 시즌2 - 음양 더하기 
 * https://programmers.co.kr/learn/courses/30/lessons/76501
 */

import java.util.*;

public class YinYangPlus {

	public static void main(String[] args) {
		int[] absolutes = {4, 7, 12};
		boolean[] signs = {true, false, true};
		System.out.println(solution(absolutes, signs));
	}

	public static int solution(int[] absolutes, boolean[] signs) {
		int answer = 0;
		
		for(int i = 0; i < absolutes.length; i++) {
			if(signs[i]) {
				answer += absolutes[i];
			} else {
				answer -= absolutes[i];
			}
		}
		
		return answer;
	}

}
