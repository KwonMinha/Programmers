/**
 * @author Minha Gwon
 * @date 2020. 5. 15.
 * 네트워크(Level 3)
 * https://programmers.co.kr/learn/courses/30/lessons/43162
 */

import java.util.HashSet;

public class Network {
	static int answer = 0;
	static HashSet<HashSet<Integer>> hs = new HashSet();
	
	public static void main(String[] args) {
		int n = 3;
		int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
		
		System.out.println(solution(n, computers));
	}
	
	public static int solution(int n, int[][] computers) {
		boolean[] visited = new boolean[n];

		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				HashSet<Integer> result = new HashSet();
				dfs(computers, visited, i, result);

				if(!hs.contains(result))
					hs.add(result);

				//System.out.println(Arrays.toString(hs.toArray()));
			}
		}
		answer = hs.size();
		
		return answer;
	}

	public static void dfs(int[][] computers, boolean[] visited, int v, HashSet<Integer> result) {
		visited[v] = true;
		result.add(v);

		for(int i = 0; i < computers.length; i++) {
			if(computers[v][i] == 1 && !visited[i]) {
				dfs(computers, visited, i, result);
			}
		}
	}
}
