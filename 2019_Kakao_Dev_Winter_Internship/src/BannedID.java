/**
 * @author Minha Gwon
 * @date 2020. 5. 8.
 * 
 * 2019 카카오 개발자 겨울 인턴십 - 불량사용자
 * https://programmers.co.kr/learn/courses/30/lessons/64064
 */

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BannedID {
	static HashSet<HashSet<String>> set = new HashSet();
	static boolean[] visited;
	static int answer = 0;

	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"*rodo", "*rodo", "******"};
		System.out.println(solution(user_id, banned_id));
	}

	public static int solution(String[] user_id, String[] banned_id) {
		visited = new boolean[user_id.length];
		dfs(user_id, banned_id, 0, 0, visited);

		return answer;
	}

	static void dfs(String[] user_id, String[] banned_id, int n, int idx, boolean[] visited) {
		if(n == banned_id.length) {
			HashSet<String> result = new HashSet();

			for(int i = 0; i < visited.length; i++) 
				if(visited[i]) 
					result.add(user_id[i]);

			if(!set.contains(result)) {
				set.add(result);
				answer++;
			}

			return;
		}

		for(int i = 0; i < user_id.length; i++) {
			String s = banned_id[idx].replace("*", ".");
			Pattern pattern = Pattern.compile(s);
			Matcher matcher = pattern.matcher(user_id[i]);

			if(matcher.find() && user_id[i].length() == s.length()) {
				if(!visited[i]) {
					visited[i] = true;
					dfs(user_id, banned_id, n+1, idx+1, visited);
					visited[i] = false;
				}
			}
		} 
	}
	
}