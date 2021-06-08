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
		System.out.println(Arrays.toString(ans));
	}

	public static int[] solution(String[] genres, int[] plays) {
		int[] answer = {};


		HashMap<String, Integer> genreMap = new HashMap<>();
		
		ArrayList<ArrayList<Integer>> genreList = new ArrayList<>();
		
		ArrayList<String> genreIdx = new ArrayList<>();
		
		int idx = 0;
		for(int i = 0; i < genres.length; i++) {
			if(!genreMap.containsKey(genres[i])) { // map에 저장되지 않은 장르인 경우 (key값이 없음)
				genreMap.put(genres[i], 1); // map에 장르 저장 
				
				genreIdx.add(genres[i]); // 장르 인덱스로 저장 
				
				genreList.add(new ArrayList<>()); // 장르의 인덱스에 해당하는 리스트에 속하는 노래 고유 번호 저장 
				genreList.get(idx).add(i);
				idx++; // 현재 장르 저장했으니 인덱스 번호 + 1 
			} else { // map에 저장된 장르의 경우 
				genreMap.put(genres[i], genreMap.get(genres[i]) + 1); // 장르가 재생된 횟수 + 1 
				
				int j = genreIdx.indexOf(genres[i]); // 장르 인덱스 얻어옴 
				genreList.get(j).add(i); // 장르 인덱스에 해당하는 리스트에 노래 고유 번호 추가 
			}
		}
		
		for(int i = 0; i < genreList.size(); i++) {
			System.out.println("i : " + i + ", genre : " + genreList.get(i));
			
			for(int j = 0; j < genreList.get(i).size(); j++) {
				System.out.println(genreList.get(i).get(j));
			}
			
			System.out.println();
		}
		
		return answer;
	}

}

class Song {
	int idx;
	int play;
	
	Song(int idx, int play) {
		this.idx = idx;
		this.play = play;
	}
}
