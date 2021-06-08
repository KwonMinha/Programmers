/**
 * @author Minha Gwon
 * @date 2021. 6. 9.
 * Level3 - 해시 - 베스트 앨범  
 * https://programmers.co.kr/learn/courses/30/lessons/42579
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class BestAlbum {

	public static void main(String[] args) {
		String[] genres = {"A", "A", "B", "A"};
		int[] plays = {5, 5, 6, 5};
		int[] ans = solution(genres, plays);
		System.out.println(Arrays.toString(ans));
	}

	public static int[] solution(String[] genres, int[] plays) {
		ArrayList<Integer> result = new ArrayList<>();

		HashMap<String, Integer> genreMap = new HashMap<>();
		ArrayList<ArrayList<Song>> genreList = new ArrayList<>();
		ArrayList<String> genreIdx = new ArrayList<>();

		int idx = 0;
		for(int i = 0; i < genres.length; i++) {
			if(!genreMap.containsKey(genres[i])) { // map에 저장되지 않은 장르인 경우 (key값이 없음)
				genreMap.put(genres[i], plays[i]); // map에 장르 저장 

				genreIdx.add(genres[i]); // 장르 인덱스로 저장 

				genreList.add(new ArrayList<>()); // 장르의 인덱스에 해당하는 리스트에 속하는 노래 고유 번호, 노래 재생 횟수 저장 
				genreList.get(idx).add(new Song(i, plays[i]));
				idx++; // 현재 장르 저장했으니 인덱스 번호 + 1 
			} else { // map에 저장된 장르의 경우 
				genreMap.put(genres[i], genreMap.get(genres[i]) + plays[i]); // 장르가 재생된 횟수 + 1 

				int j = genreIdx.indexOf(genres[i]); // 장르 인덱스 얻어옴 
				genreList.get(j).add(new Song(i, plays[i])); // 장르 인덱스에 해당하는 리스트에 노래 고유 번호 추가 
			}
		}

		ArrayList<String> keySetList = new ArrayList<>(genreMap.keySet());
		// value (genre 재생 횟수) 기준 내림차순 정렬 		
		Collections.sort(keySetList, (o1, o2) -> (genreMap.get(o2).compareTo(genreMap.get(o1))));

		for(int i = 0; i < keySetList.size(); i++) {
			idx = genreIdx.indexOf(keySetList.get(i));

			System.out.println(keySetList.get(i));
			Collections.sort(genreList.get(idx)); // 장르 내 노래들 재생 횟수로 내림차순 정렬 

			if(genreList.get(idx).size() == 1) { // 장르에 속한 곡이 하나인 경우 
				result.add(genreList.get(idx).get(0).idx);
			} else { // 2 이상인 경우 
				result.add(genreList.get(idx).get(0).idx);
				result.add(genreList.get(idx).get(1).idx);
			}
		}

		int[] answer = result.stream().mapToInt(i -> i).toArray();

		return answer;
	}

}

class Song implements Comparable<Song>{
	int idx;
	int play;

	Song(int idx, int play) {
		this.idx = idx;
		this.play = play;
	}

	@Override
	public int compareTo(Song o) {
		return o.play - this.play;
	}
}
