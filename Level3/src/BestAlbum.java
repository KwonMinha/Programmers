/**
 * @author Minha Gwon
 * @date 2021. 5. 28.
 * Level3 - 해시 - 베스트 앨범  
 * https://programmers.co.kr/learn/courses/30/lessons/42579
 */

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class BestAlbum {

	public static void main(String[] args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		int[] ans = solution(genres, plays);
		System.out.println(ans.toString());
	}

	public static int[] solution(String[] genres, int[] plays) {
		int[] answer = {};


		HashMap<String, Integer> genreMap = new HashMap<>();

		for(int i = 0; i < genres.length; i++) {
			String key = genres[i];
			genreMap.put(key, genreMap.getOrDefault(key, 0) + genreMap.get(key));
		}




		return answer;
	}

}
