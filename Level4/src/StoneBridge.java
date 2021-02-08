/**
 * @author Minha Gwon
 * @date 2021. 2. 7.
 * 
 * Level4 - 이분 탐색 - 징검다리   
 * https://programmers.co.kr/learn/courses/30/lessons/43236
 */

import java.util.Arrays;

public class StoneBridge {

	public static void main(String[] args) {
		int[] rocks = {2, 14, 11, 21, 17};
		System.out.println(solution(25, rocks, 2));
	}
	
	public static int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);

        int low = 1;
        int high = distance;
        int mid; 
        
        int removeCnt; 
        int lastRock = 0;
        
        while(low <= high) {
        	mid = (low + high) / 2;
        
        	removeCnt = 0; 
        	lastRock = 0;
        	
        	for(int i = 0; i < rocks.length; i++) {
        		if(rocks[i] - lastRock < mid) { 
        			removeCnt++;
        		} else {
        			lastRock = rocks[i];
        		}
        	}
        	
        	if(distance - lastRock < mid) 
        		removeCnt++; 
        	
        	if(removeCnt <= n) { 
        		low = mid + 1;
        		answer = Math.max(answer, mid);
        	} else { 
        		high = mid -1;
        	}
        }
        
        return answer;
    }

}