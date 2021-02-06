/**
 * @author Minha Gwon
 * @date 2021. 2. 5.
 * 
 * 2019 카카오 신입 공채 - 징검다리 건너기    
 * https://programmers.co.kr/learn/courses/30/lessons/64062
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SteppingStoneBridge {

	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;

		System.out.println(solution(stones, k)); // 3
	}

	public static int solution(int[] stones, int k) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		// 디딤돌 수의 최대값, 최소값 구함 
		for(int n : stones) {
			max = Math.max(max, n); // 건널 수 있는 인원의 최대값은 배열에서 디딤돌의 최대값과 같음
			min = Math.min(min, n); // 건널 수 있는 인원의 최소값은 배열에서 디딤돌의 최소값과 같음 
		}

		return parametricSearch(stones, k, min, max);
	}

	public static int parametricSearch(int[] stones, int k, int low, int high) {
		if(low == high) // 최소값, 최대값이 같다면 그 값으로 건널 수 있으니 확인할 필요 없음 
			return low;

		while(low < high) {
			// +1 하면 mid가 1.5가 나올시 2가 되는 경우
			int mid = (low + high + 1) / 2; // 시간을 줄이기 위해 최대와 최소의 중간값으로 건널 수 있는지 확인 

			if(cross(stones, k, mid)) { // 중간값으로 건널 수 있다는 것은 중간보다 작은 값은 모두 건널 수 있다는 의미 -> 중간+1 값부터 다시 확인 
				low = mid;
			} else {
				high = mid - 1; // 중간값으로 건널 수 없다는 것은 중간보다 큰 값은 모두 건널 수 없다는 의미 -> 중간-1 값부터 다시 확인 
			}
		}

		return low; // or return high;
	}

	public static int parametricSearch2(int[] stones, int k, int low, int high) {
		if(low == high) 
			return low;

		while(low < high) {
			// mid가 1.5가 나올시 1이 되는 경우 
			int mid = (low + high) / 2; 

			if(cross(stones, k, mid)) { 
				low = mid + 1;
			} else {
				high = mid; 
			}
		}

		return high-1; // cross의 반환값이 true인 경우에서, 다음 mid 가능한지 확인하기 위해서 +1 했으니 리턴할 땐 -1해서 반환 
	}

	// 현재 mid 값(인원)으로 징검다리를 건널 수 있는지 없는지 확인 
	public static boolean cross(int[] stones, int k, int mid) {
		int cnt = 0;

		for (int stone : stones) {
			if (stone - mid < 0) { // 0보다 작아서 못 건너가면 cnt++
				cnt++;
			} else {
				cnt = 0; // 0이상이면 건너갈 수 있음 - cnt 0으로 다시 갱신 
			}

			if (cnt == k) // 못 건너서 쌓인 cnt의 값이 건널 수 있는 최대칸 수 k를 넘으면 현재 mid 값으로는 건널 수 없음 
				return false;
		}

		return true; // 다 통과하면 건널 수 있음 
	}

}