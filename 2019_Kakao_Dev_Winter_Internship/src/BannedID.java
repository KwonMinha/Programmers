/**
 * @author Minha Gwon
 * @date 2020. 5. 8.
 * 2019 카카오 개발자 겨울 인턴십 - 불량사용자
 * https://programmers.co.kr/learn/courses/30/lessons/64064
 */

import java.util.*;

public class BannedID {

	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};

		String[] banned_id = {"*rodo", "*rodo", "******"};
		solution(user_id, banned_id);
	}

	public static int solution(String[] user_id, String[] banned_id) {
		int answer = 0;

		HashSet<String> set = new HashSet<String>();
		HashSet<String> banned = new HashSet<String>();

		for(int i = 0; i < banned_id.length; i++) {
			set.add(banned_id[i]);
		}

		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			String b = iter.next();
			int bLen = b.length();

			for(int j = 0; j < user_id.length; j++) {
				if(bLen == user_id[j].length()) {
					boolean isBanned = true;
					String u = user_id[j];
					for(int k = 0; k < bLen; k++) {
						if(b.charAt(k) == '*' || b.charAt(k) == u.charAt(k)){
							continue;
						} else {
							isBanned = false;
							break;
						}  
					}

					if(isBanned == true) {
						banned.add(u);
					}
				}
			}
		}

		Iterator<String> bannedIter = banned.iterator();
		while (bannedIter.hasNext()) {
			System.out.println(bannedIter.next());
		}

		return answer;
	}
}
