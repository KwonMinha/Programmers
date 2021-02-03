import java.util.Arrays;

/**
 * @author Minha Gwon
 * @date 2021. 2. 3.
 * 
 * 2021 카카오 신입 공채 - 메뉴 리뉴얼 
 * https://programmers.co.kr/learn/courses/30/lessons/72411
 */

public class MenuRenewal {

	public static void main(String[] args) {
//		{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}	{2,3,5}
//		{"XYZ", "XWY", "WXA"}	{2,3,4}
		
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};

		System.out.println(Arrays.toString(solution(orders, course)));
	}
	
	public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        return answer;
    }

}
