/**
 * @author Minha Gwon
 * @date 2021. 2. 3.
 * 
 * 2021 카카오 신입 공채 - 합승 택시 요금 
 * https://programmers.co.kr/learn/courses/30/lessons/72413
 */

import java.util.*;

public class TaxiFare {
	public static int[][] map;
	public static int N;

	public static void main(String[] args) {
		//		7	3	4	1	{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}	
		//		6	4	5	6	{{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}	
		
		int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}	;

		System.out.println(solution(7, 3, 4, 1, fares));
	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;
		
		N = n;
		map = new int[n+1][n+1];
		for(int i = 0; i < fares.length; i++) {
			int[] fare = fares[i];
			map[fare[0]][fare[1]] = fare[2];
			map[fare[1]][fare[0]] = fare[2];
		}

		// 합승 안하고 각자 이동할 경우 - BFS로 start부터 각 지점까지의 최소 비용 배열 구함 
		int[] startCheck = new int[N+1];
		Arrays.fill(startCheck, -1);
		bfs(s, startCheck);
		answer = Math.min(answer, (startCheck[a] + startCheck[b]));
		
		// 주석 
		System.out.println("합승 X - a : " + a + ", b : " + b + ", start : " + s + "\n----------------------------------------");
		printCount(startCheck);
		System.out.println("answer : " + answer + "\n----------------------------------------");
		
		
		// 합승 
		for(int i = 1; i <= N; i++) {
			if(i != s && startCheck[i] != -1) { // start 지점이 아니고, start에서부터 지나갈 수 있는 지점이라면 합승 비용 확인 
				int[] carPoolCheck = new int[N+1];
				Arrays.fill(carPoolCheck, -1);
				bfs(i, carPoolCheck);
				answer = Math.min(answer, (startCheck[i] + carPoolCheck[a] + carPoolCheck[b]));
				
				// 주석 
				System.out.println("합승 i : " + i  + ", a : " + a + ", b : " + b + ", start carPool : " + i + "\n----------------------------------------");
				printCount(carPoolCheck);
				System.out.println("answer : " + (startCheck[i] + carPoolCheck[a] + carPoolCheck[b]) + "\n----------------------------------------");
			}
		}

		return answer;
	}

	public static void bfs(int start, int[] check) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		check[start] = 0;

		while(!queue.isEmpty()) {
			int v = queue.poll();

			for(int i = 1; i <= N; i++) {
				if(map[v][i] != 0) {
					if(check[i] == -1) {
						queue.add(i);
						check[i] = check[v] + map[v][i];
					} else if(check[i] >= (check[v] + map[v][i])) { //이미 갔던곳이라도 비용이 적으면 갱신 
						queue.add(i);
						check[i] = check[v] + map[v][i];
					}

				}
			}
		}
	}

	public static void printCount(int[] count) {
		for(int i = 1; i <= N; i++) {
			System.out.println("i : " + i + ", count : " + (count[i]));
		}

		System.out.println();
	}

}
