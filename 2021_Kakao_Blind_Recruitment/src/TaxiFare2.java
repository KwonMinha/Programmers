/**
 * @author Minha Gwon
 * @date 2021. 2. 4.
 * 
 * 2021 카카오 신입 공채 - 합승 택시 요금 
 * https://programmers.co.kr/learn/courses/30/lessons/72413
 */

import java.util.*;

public class TaxiFare2 {
	public static int INF = 10000000;

	public static void main(String[] args) {
		int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}	;

		System.out.println(solution(7, 3, 4, 1, fares));
	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		int[][] map = new int[n+1][n+1];

		// 플로이드 와샬 알고리즘을 적용하기 위한 2차원 배열 map 초기화 
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == j)
					map[i][j] = 0; // 자기 자신으로 가는 비용은 0 
				else 
					map[i][j] = INF; // 아직 비용을 모르니 무한대로 초기화 
			}
		}

		// 비용 넣어줌 
		for(int i = 0; i < fares.length; i++) {
			int[] fare = fares[i];
			map[fare[0]][fare[1]] = fare[2];
			map[fare[1]][fare[0]] = fare[2];
		}

		for(int k = 1; k <= n; k++) { // 거쳐가는 노드 
			for(int i = 1; i <= n; i++) { // 출발 노드 
				for(int j = 1; j <= n; j++) { // 도착 노드 
					//i에서 k를 거쳤다가 k에서 j 까지 가는 비용과 i에서 j 까지 가는 비용을 비교해서 더 작은 값이 최소 비용 거리 
					map[i][j] = Math.min((map[i][k] + map[k][j]), map[i][j]);
					//System.out.println(map[i][j]);
				}
			}
		}
		
		// 합승 최소 비용 구하기 
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			answer = Math.min(answer, map[s][i] + map[i][a] + map[i][b]);
		}

		return answer;
	}

}
