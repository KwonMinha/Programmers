import java.util.*;

public class Problem1 {

	public static void main(String[] args) {
		String s = "one4seveneight";
		int answer = solution(s);
		System.out.println(answer);
	}

	public static int solution(String s) {
		String[] stinrgNum = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		String[] intNum = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		
		for(int i = 0; i < stinrgNum.length; i++) {
			s = s.replaceAll(stinrgNum[i], intNum[i]);
		}
		
		return Integer.parseInt(s);
	}

}
