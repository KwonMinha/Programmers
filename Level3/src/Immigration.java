/**
 * @author Minha Gwon
 * @date 2021. 2. 6.
 * 
 * Level3 - 이분 탐색 - 입국 심사  
 * https://programmers.co.kr/learn/courses/30/lessons/43238
 */

public class Immigration {

	public static void main(String[] args) {
		int[] times = {7, 10};
		System.out.println(solution(6, times)); // 28
	}

	public static long solution(int n, int[] times) {
		long max = Long.MIN_VALUE;
		for(int i = 0; i < times.length; i++) // 최대 심사 소요 시간 
			max = Math.max(max, times[i]);
		
		long answer = Long.MAX_VALUE;
		
		long low = 1;
		long high = max * n; // 최대 전체 심사 시간 = 최대 심사 소요 시간 * 심사 대기자 
		long mid = 0;

		// 이진 탐색 수행 
		while(low <= high) {
			mid = (low + high) / 2;

			if(count(times, n, mid)) {
				high = mid - 1;
				answer = Math.min(answer, mid);
			} else {
				low = mid + 1;
			}
		}

		return answer;
	}

	public static boolean count(int[] times, int n, long mid) {
		long cnt = 0;

		for(int i = 0; i < times.length; i++) {
			cnt += mid / times[i]; // mid 시간동안 각 심사관이 몇명을 심사하는지 저장 

			if(cnt >= n) // 심사 대기자 n명 모두 심사할 수 있는 경우 
				return true;
		}

		return false;
	}

}