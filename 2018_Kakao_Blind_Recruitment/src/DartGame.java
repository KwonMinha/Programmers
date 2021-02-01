/**
 * @author Minha Gwon
 * @date 2021. 2. 1.
 * 
 * 2018 카카오 신입 공채 - [1차] 다트 게임 
 * https://programmers.co.kr/learn/courses/30/lessons/17682
 */

public class DartGame {

	public static void main(String[] args) {
		String[] dart = {"1S2D*3T", "1D2S#10S", "1D2S0T", "1S*2T*3S", "1D#2S*3S", "1T2D3D#", "1D2S3T*"};
		//37, 9, 3, 23, 5, -4, 59

		System.out.println(solution(dart[6]));
	}

	public static int solution(String dartResult) {
		String[] result = new String[9];
        int index = 0;
        for(int i = 0; i < dartResult.length(); i++) {
        	char ch = dartResult.charAt(i);
        	if(ch >= '0' && ch <= '9') { // 숫자인 경우 
        		if(dartResult.charAt(i+1) == '0') { // 10인 경우 
        			result[index] = "10";
        			i++;
        		} else {
        			result[index] = ch + "";
        		}
        	} else if(ch == 'S' || ch == 'D' || ch == 'T') {
        		if((i+1) != dartResult.length() && (dartResult.charAt(i+1) == '*' || dartResult.charAt(i+1) == '#')) {
        			result[index+1] = ch + "";
        		} else {
        			result[index+1] = ch + ""; // 뒤에 스타상 아차상이 없다면, 바로 다음 숫자로 넘어감 
        			index += 3;
        		}
        	} else { // 스타상, 아차상인 경우 
        		result[index+2] = ch + "";
        		index += 3;
        	}
        }
        
        int[] answer = new int[3];
        for(int i = 0; i < result.length; i += 3) {
        	double num = Double.parseDouble(result[i]);
        	String bonus = result[i+1];
        	String option = result[i+2];
        	
        	double pow = 0;
        	if(bonus.equals("S")) {
        		pow = 1;
        	} else if(bonus.equals("D")) {
        		pow = 2;
        	} else {
        		pow = 3;
        	}
        	
        	int sum = (int) Math.pow(num, pow);
        	
        	if(option != null) {
        		if(option.equals("*")) { // 스타상일 경우 
        			if(i != 0) // 첫번 째 제외 
        				answer[i/3-1] *= 2;
        			
            		sum *= 2;
            		answer[i/3] = sum;
            	} else if(option.equals("#")) { // 아차상일 경우 
            		sum *= -1;
            		answer[i/3] = sum;
            	}
        	} else { // 스타상, 아차상 둘 다 아닐 경우 
        		answer[i/3] = sum;
        	}
        }
        
        return answer[0] + answer[1] + answer[2];
    }
}
