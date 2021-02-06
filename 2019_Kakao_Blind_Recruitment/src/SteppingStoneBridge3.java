/**
 * @author Minha Gwon
 * @date 2021. 2. 6.
 * 
 * 2019 카카오 신입 공채 - 징검다리 건너기 3  
 * https://programmers.co.kr/learn/courses/30/lessons/64062
 */

public class SteppingStoneBridge3 {

	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;

		System.out.println(solution(stones, k)); // 3
	}

	public static int solution(int[] stones, int k) {
		// 이진 탐색을 이용해 구현 
		int low = 1; // stones 원소의 최소값 
		int high = 200000000; // stones 원소의 최대값
		int mid = 0;
		int answer = 0;

		while(low <= high){
			mid = (low + high) / 2; // 중간값 

			if(!cross(stones, k, mid)) {
				high = mid - 1; // 중간값으로 건널 수 없다는 것은 중간보다 큰 값은 모두 건널 수 없다는 의미 -> 중간-1 값부터 다시 확인 
			} else {
				low = mid + 1; // 중간값으로 건널 수 있다는 것은 중간보다 작은 값은 모두 건널 수 있다는 의미 -> 중간+1 값부터 다시 확인 
				answer = Math.max(answer, mid); // 최대 mid 값을 가진 answer인 경우만 값 갱신 
			}
		}

		return answer;
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