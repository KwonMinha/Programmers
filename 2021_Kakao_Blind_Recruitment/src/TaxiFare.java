/**
 * @author Minha Gwon
 * @date 2021. 2. 3.
 * 
 * 2021 카카오 신입 공채 - 합승 택시 요금 
 * https://programmers.co.kr/learn/courses/30/lessons/72413
 */

public class TaxiFare {

	public static void main(String[] args) {
//		7	3	4	1	{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}	
//		6	4	5	6	{{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}	
		
		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		
		System.out.println(solution(n, s, a, b, fares));
	}
	
	 public static int solution(int n, int s, int a, int b, int[][] fares) {
	        int answer = 0;
	        return answer;
	    }

}
