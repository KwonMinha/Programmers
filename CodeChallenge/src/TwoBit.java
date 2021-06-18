/**
 * @author Minha Gwon
 * @date 2021. 6. 19.
 * 월간 코드 챌린지 시즌2 - 2개 이하로 다른 비트
 * https://programmers.co.kr/learn/courses/30/lessons/77885
 */

import java.util.*;

public class TwoBit {

	public static void main(String[] args) {
		long[] numbers = {2, 7};
		long[] ans = solution(numbers);
		System.out.println(Arrays.toString(ans));
	}

	public static long[] solution(long[] numbers) {
		long[] answer = new long[numbers.length];

		for(int i = 0; i < numbers.length; i++) {
			String num1 = conversion(numbers[i], 2);
			
			loop:
			for(long j = numbers[i] + 1; j < Long.MAX_VALUE; j++) {
				String num2 = conversion(j, 2);
				
				int d = num2.length() > num1.length() ? num2.length() : num1.length();
				
				num1 = String.format("%" + d + "s", num1);
				num2 = String.format("%" + d + "s", num2);
				
				int cnt = 0;
				
				for(int k = num1.length()-1; k >= 0; k--) {
					if(cnt > 2) 
						break;
					
					if(num1.charAt(k) != num2.charAt(k))
						cnt++;
				}
				
				if(cnt == 1 || cnt == 2) {
					answer[i] = j;
					break loop;
				}
			}

		}

		return answer;
	}

	public static String conversion(long value, int n) {
		StringBuilder sb = new StringBuilder();

		while(value > 0){
			if(value % n > 9) { // 나머지가 10 이상일때(즉 11진수 이상일때) 문자열 배당
				sb.append((char) (value % n + 55) + "");
			} else { // 그외는 나머지 숫자값 넣기
				sb.append((value % n) + "");
			}
			value = value / n;
		}

		// 입력수가 0일 때는 결과값 0
		if(sb.toString().equals("")) {
			sb.append("0");
		} 

		return sb.reverse().toString();
	}

}
