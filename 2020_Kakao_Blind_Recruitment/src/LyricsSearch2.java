import java.util.Arrays;
import java.util.HashMap;

public class LyricsSearch2 {

	public static void main(String[] args) {
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

		int[] ans = solution(words, queries);
		System.out.println(Arrays.toString(ans));
	}

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];

		HashMap<String, Integer> hashMap = new HashMap<>();
		
		for(int i = 0; i < queries.length; i++) {
			int startIdx = queries[i].indexOf("?");
			int lastIdx = queries[i].lastIndexOf("?");

			StringBuilder sb = new StringBuilder();
			String q = "";
			int qLen = queries[i].length();
			int flag = 0;

			if(startIdx == 0) { // ?가 접두사로 올 경우
				flag = 0;
				q = queries[i].substring(lastIdx+1);
				sb.append(queries[i].substring(lastIdx+1));
			} else { // ?가 접미사로 올 경우
				flag = 1;
				q = queries[i].substring(0, startIdx);
				sb.append(queries[i].substring(0, startIdx));
			}

			//String key = qLen + flag + q;
			String key = qLen + flag + sb.toString();

			if(hashMap.containsKey(key)) {
				answer[i] = hashMap.get(key);
			} else {
				int count = 0;
				
				for(int j = 0; j < words.length; j++) {
					String word = words[j];
					
					if(word.length() != qLen) continue;
					
					if(flag == 0 && word.endsWith(sb.toString())) {
						count++;
					} else if(flag == 1 && word.startsWith(sb.toString())) {
						count++;
					}
				}
				
				answer[i] = count;
				hashMap.put(key, count);
			}
		}

		return answer;
	}

}
