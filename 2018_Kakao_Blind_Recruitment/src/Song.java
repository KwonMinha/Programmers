/**
 * @author Minha Gwon
 * @date 2020. 7. 3.
 * 
 * 2018 카카오 신입 공채 3차 - 방금 그 곡
 * https://programmers.co.kr/learn/courses/30/lessons/17683
 */

import java.util.Arrays;

public class Song {

	public static void main(String[] args) {
		/*
		 * ex 1)
		 * "ABCDEFG";
		 * {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		 * 
		 * ex 2)
		 * "CC#BCC#BCC#BCC#B";
		 * {"03:00,03:30,FOO,CC#B, 04:00,04:08,BAR,CC#BCC#BCC#B"};
		 * 
		 * ex 3)
		 * "ABC";
		 * {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		 */

		String m = "ABC";
		String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		System.out.println(solution(m, musicinfos));
	}

	public static String solution(String m, String[] musicinfos) {
		String answer = "";
		String result = "";
		int maxTime = 0;

		for(int i = 0; i < musicinfos.length; i++) {
			//재생 시작 시간, 종료 시간, 음악 제목, 악보 값 초기화
			String[] music = musicinfos[i].split(",");
			int[] startTime = Arrays.stream(music[0].split(":"))
					.mapToInt(Integer::parseInt)
					.toArray();
			int[] endTime = Arrays.stream(music[1].split(":"))
					.mapToInt(Integer::parseInt)
					.toArray();
			String title = music[2];
			String info = music[3];

			// 재생 시간(playTime) 구하기
			int sh = startTime[0];
			int sm = startTime[1];
			int eh = endTime[0];
			int em = endTime[1];   

			int playTime = 0;
			if(em >= sm) {
				playTime = (eh - sh) * 60 + (em - sm);
			} else {
				playTime = ((eh-1) - sh) * 60 + ((em+60) - sm);
			}

			int musicTime = info.length(); //음악 길이 

			StringBuilder sb = new StringBuilder();
			int cnt = 0;
			int pt = playTime;
			while(pt != 0) {
				if(pt >= musicTime) {
					sb.append(info);
					pt -= musicTime;
				} else {
					if(cnt != musicTime-1 && info.charAt(cnt+1) == '#') {
						sb.append(info.charAt(cnt)).append(info.charAt(cnt+1));
						cnt += 2;
					} else {
						sb.append(info.charAt(cnt));
						cnt++;
					}

					if(cnt == musicTime)
						cnt = 0;

					pt--;
				}
			}

			String melody = sb.toString();
			if(melody.contains(m)) {
				int index = melody.indexOf(m) + m.length();
				if(melody.charAt(index) != '#') {
					if(maxTime < playTime) {
						maxTime = playTime;
						result = title;
					}
				}		
			}
		}

		if(result.equals(""))
			answer = "(None)";
		else
			answer = result;

		return answer;
	}
}
