/**
 * @author Minha Gwon
 * @date 2020. 7. 3.
 * 
 * 2018 카카오 신입 공채 3차 - 방금 그 곡
 * https://programmers.co.kr/learn/courses/30/lessons/17683
 */

import java.util.Arrays;

public class Song2 {

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

		String m1 = "ABC";
		String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		System.out.println(solution(m1, musicinfos));
	}

	public static String solution(String m, String[] musicinfos) {
		String answer = "";
		String result = "";
		int maxTime = 0;
		m = lowerCase(m);

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
			info = lowerCase(info);

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

			//재생 시간동안 나온 음악의 전체 악보 구하기 
			int songTime = info.length(); //음악 길이 
			StringBuilder sb = new StringBuilder();
			
			for(int j = 0; j < playTime; j++) {
				sb.append(info.charAt(j % songTime));
				//System.out.println(sb.toString());
			}
			
			/*
			// 몫
			int a = minutes / paper.length;
			// 나머지
			int b = minutes % paper.length;

			for (int j = 0; j < a; j++)
				sb.append(arr[3]);
			for (int j = 0; j < b; j++) {
				sb.append(arr[3].charAt(j));
			}
			 */
			
			/*
			if (t > time) {
                info[3] = edit(info[3]);
                StringBuffer sb = new StringBuffer();
                for (int jnx = 0; jnx < t; jnx++) {
                    sb.append(info[3].charAt(jnx % (info[3].length())));
                }
                if (sb.toString().indexOf(m) >= 0) {
                    answer = info[2];
                    time = t;
                }
            }
			*/
			
			/*
			int cnt = 0;
			int pt = playTime;
			while(pt != 0) {
				if(pt >= musicTime) {
					sb.append(info);
					pt -= musicTime;
				} else {
					sb.append(info.charAt(cnt));
					cnt++;
					
					if(cnt == musicTime)
						cnt = 0;
					pt--;
				}
			}
			*/
			
			//전체 악보 중 m이 있다면, 그 중 재생 시간이 가장 긴 음악의 제목을 결과 변수에 넣음 
			String melody = sb.toString();
			if(melody.contains(m)) {
				if(maxTime < playTime) {
					maxTime = playTime;
					result = title;
				}		
			}
		}
		
		if(result.equals(""))
			answer = "(None)";
		else
			answer = result;

		return answer;
	}

	//원활한 문자열 비교를 위해 #음 소문자로 치환 
	public static String lowerCase(String str) {
		str = str.replaceAll("C#", "c");
		str = str.replaceAll("D#", "d");
		str = str.replaceAll("F#", "f");
		str = str.replaceAll("G#", "g");
		str = str.replaceAll("A#", "a");

		return str;
	}
}