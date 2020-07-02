import java.util.*;

public class Test {

	public static void main(String[] args) {
		/*
		 * ex 1)
		 * "ABCDEFG";
		 * {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		 * 
		 * ex 2)
		 * "CC#BCC#BCC#BCC#B";
		 * {"03:00,03:30,FOO,CC#B, 04:00,04:08,BAR,CC#BCC#BCC#B"};
		 * 
		 * ex 3)
		 * "ABC";
		 * {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		 */

		String m = "ABC";
		String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		System.out.println(solution(m, musicinfos));
	}

	public static String solution(String m, String[] musicinfos) {
		String answer = "";

		return answer;
	}
}
