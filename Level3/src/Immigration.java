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
		long answer = Long.MAX_VALUE;
		long low = 1;
		long high = 1000000000;
		long mid = 0;

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
			long time = times[i];
			while(time <= mid) {
				cnt++;
				time += times[i];
			}

			if(cnt >= n) {
				return true;
			}
		}

		return false;
	}

}
