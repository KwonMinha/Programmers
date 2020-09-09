/**
 * @author Minha Gwon
 * @date 2020. 9. 10
 * 점프와 순간 이동 (Level 2)
 * https://programmers.co.kr/learn/courses/30/lessons/12980
 */

public class JumpAndTeleport {
	public static void main(String[] args) {
		int n = 6;
		System.out.println(solution(n));
	}
	
	  public static int solution(int n) {
	        int ans = 0;
	        
	        while(n != 0) {
	            if(n % 2 == 0) {
	                n /= 2;
	            } else {
	                n -= 1;
	                ans++;
	            }
	        }
	        
	        return ans;
	    }
}
