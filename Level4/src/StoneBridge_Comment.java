/**
 * @author Minha Gwon
 * @date 2021. 2. 7.
 * 
 * Level4 - 이분 탐색 - 징검다리   
 * https://programmers.co.kr/learn/courses/30/lessons/43236
 */

import java.util.Arrays;

public class StoneBridge_Comment {

	public static void main(String[] args) {
		int[] rocks = {2, 14, 11, 21, 17};
		System.out.println(solution(25, rocks, 2));
	}
	
	public static int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks); // 거리를 오름차순으로 정렬 
        
        //도착지점까지의 거리 distance는 1 이상 1,000,000,000 이하
        int low = 1;
        int high = distance; // 최대 distance 만큼 떨어져있을 수 있음 
        int mid; // 임의로 정한 (돌 사이) 거리의 최솟값
        
        int removeCnt; // 제거된 돌의 총합을 저장 
        int lastRock = 0; // 마지막으로 선택된 돌의 인덱스를 저장 
        
        while(low <= high) {
        	mid = (low + high) / 2; // 중간값을 임의의 값으로 지정 
        
        	removeCnt = 0; // 처음 시작은 제거한게 없으니 0부터 시작 
        	
        	lastRock = 0; // 처음엔 출발점인 0부터 시작
        	
        	for(int i = 0; i < rocks.length; i++) {
        		// 현재돌에서 마지막으로 선택된 돌을 뺀 거리가 mid(임의로 정한 (돌 사이) 거리의 최솟값) 보다 작은 경우
        		// mid값이 최솟값이 될 수 없게 됨
        		// 현재돌을 제거해서, 마지막으로 선택된 돌과 다음 for문에서 새롭게 선택될 다음돌과의 거리를 늘려야 함 
        		if(rocks[i] - lastRock < mid) { 
        			removeCnt++; // 제거 
        		} else {
        			// mid값 보다 같거나 큰 경우
        			// 현재돌을 제거하지 않아도 돌 사이의 최솟값이 mid값으로 유지됨
        			// 따라서 현재돌을 마지막돌로 만들어, 다음 for문에서 다음돌과 현재돌 사이의 거리를 구할 수 있도록 함 
        			lastRock = rocks[i];
        		}
        	}
        	
        	// 마지막 돌까지 확인한 후, 최종적으로 도착 지점과의 거리를 따져봐야함 
        	if(distance - lastRock < mid) // 도착 지점과 마지막 돌 사이의 거리가 mid보다 작다면 
        		removeCnt++; // 마지막돌을 삭제해야 함 
        	
        	// 출발 지점(0)부터 도착 지점(distance)까지의 거리를 모두 확인 후, 
        	if(removeCnt <= n) { //n 이하로 삭제한 경우 -> 현재 mid값(거리의 최솟값)을 만들 수 있음 
        		// 따라서 조건을 만족하는 다른 mid값이 있는지 재탐색 (현재 mid보다 작은 값들은 당연히 가능) 
        		low = mid + 1;
        		
        		// 또한 조건을 만족해 정답이 될 수 있으니, 이전에 구했던 answer와 비교해 큰 값으로 answer 갱신 
        		answer = Math.max(answer, mid);
        	} else { // 삭제해야할 n보다 더 많이 삭제한 경우 -> n개의 돌을 제거해서 현재의 mid(거리의 최솟값)값을 만들 수 없음
        		// mid값은 더 작아져야함 (현재 mid보다 큰 값들은 당연히 불가능)
        		high = mid -1;
        	}
        }
        
        return answer;
    }

}
