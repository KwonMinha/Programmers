/**
 * @author Minha Gwon
 * @date 2021. 2. 1.
 * 
 * 2018 카카오 신입 공채 - [1차] 다트 게임 
 * https://programmers.co.kr/learn/courses/30/lessons/17682
 */

public class DartGame2 {

	public static void main(String[] args) {
		String[] dart = {"1S2D*3T", "1D2S#10S", "1D2S0T", "1S*2T*3S", "1D#2S*3S", "1T2D3D#", "1D2S3T*"};
		//37, 9, 3, 23, 5, -4, 59

		System.out.println(solution(dart[1]));
	}

	public static int solution(String dartResult) {
		int[] score = new int[3];
		int index = -1;
		char[] dart = dartResult.toCharArray();
		for(int i = 0; i < dart.length; i++) {
			if(dart[i] >= '0' && dart[i] <= '9') { // 숫자일 경우 
				index++;
				if(dart[i+1] == '0') { // 10인 경우 
					score[index] = 10;
					i++;
				} else {
					score[index] = dart[i] - '0';
				}
			} else if(dart[i] == 'D') { // 더블 
				score[index] *= score[index];
			} else if(dart[i] == 'T') { // 트리플 
				score[index] *= score[index] * score[index];
			} else if(dart[i] == '*') { // 스타상 
				if(index > 0) // 첫번째는 제외 
					score[index-1] *= 2;
				score[index] *= 2;
			} else if(dart[i] == '#') { // 아차상 
				score[index] *= -1;
			}
		}

		return score[0] + score[1] + score[2];
	}
}