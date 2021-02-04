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
	public static int[] count;
	public static int N;

	public static void main(String[] args) {
		//		7	3	4	1	{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}	
		//		6	4	5	6	{{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}	

		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		//int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
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

		// 합승 안하고 각자 이동할 경우 
		System.out.println("\n합승 X - a : " + a + ", b : " + b + ", start : " + s);
		answer = Math.min(answer, moveAB(s, a, b));
		printCount();
		System.out.println("answer : " + answer + "\n");
		
		// 합승 
		for(int i = 1; i <= N; i++) {
			count = new int[n+1];

			if(i != s) { // i는 합승해서 갈 곳 
				System.out.println("\n합승 O - a : " + a + ", b : " + b + ", start : " + s);
				bfs(s);
				printCount();
				if(count[i] != 0) {
					answer = Math.min(answer, (count[i] - 1 + moveAB(i, a, b))); // bfs 시작점에 1을 넣었기 때문에 -1해줌 
					System.out.println("answer : " + (count[i] - 1 + moveAB(i, a, b)));
				}
			}
		}

		return answer;
	}

	public static int moveAB(int s, int a, int b) {
		count = new int[N+1];

		int fare = 0;
		bfs(s);

		// 스타트 지점이 a, b가 아닌 경우만 이동 
		if(s != a) 
			fare += count[a]-1; // bfs에서 시작점 1넣어줬으니 -1 해줌 

		if(s != b)
			fare += count[b]-1;

		return fare;
	}

	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		count[start] = 1;

		while(!queue.isEmpty()) {
			int v = queue.poll();

			for(int i = 1; i <= N; i++) {
				if(map[v][i] != 0) {
					if(count[i] == 0) {
						queue.add(i);
						count[i] = count[v] + map[v][i];
					} else if(count[i] >= (count[v] + map[v][i])) { //이미 갔던곳이라도 비용이 적으면 갱신 
						queue.add(i);
						count[i] = count[v] + map[v][i];
					}

				}
			}
		}
	}

	public static void printCount() {
		for(int i = 1; i <= N; i++) {
			System.out.println("i : " + i + ", count : " + (count[i]-1));
		}

		System.out.println();
	}

}
