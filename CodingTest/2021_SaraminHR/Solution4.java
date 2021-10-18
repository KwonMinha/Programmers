/*
 * 논문 목록 인덱스 변환
 * 패턴매치 찾는거에서 바로바로 치환도 안 됨 (인덱스 에러)
 * 
 
 Deeper neural networks are more difficult to train. We present a residual learning framework to ease the training of networks that are substantially deeper than those used previously.[ some_paper_a, some_paper_b ] We explicitly reformulate the layers as learning residual functions with reference to the layer inputs, instead of learning unreferenced functions.[ some_book_a, some_paper_a ] We provide comprehensive empirical evidence showing that these residual networks are easier to optimize, and can gain accuracy from considerably increased depth. [ some_book_b ]

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String text = scanner.nextLine().trim();

		ArrayList<String> idxList = new ArrayList<>();
		ArrayList<String> resultList = new ArrayList<>();

		Pattern pattern = Pattern.compile("\\[[^\\[\\]]+\\]");
		Matcher matcher = pattern.matcher(text);

		String text2 = text;
		String find = new String();

		HashMap<String, Integer> map = new HashMap<>();
		int idx = 1;

		// 대괄호 찾기 
		while(matcher.find()) {
			int startIdx = matcher.start();
			int endIdx = matcher.end();

			find = text2.substring(startIdx, endIdx); // 대괄호 저장
			text2 = text2.replace(find, "*");
			matcher = pattern.matcher(text2);

			String findText = find;
			// 대괄호 문자열 치환 
			findText = findText.replaceAll(",", "");
			findText = findText.replace("[ ", "");
			findText = findText.replace(" ]", "");

			// 목차 인덱스 만들기 
			String[] findArr = findText.split(" ");
			int[] idxArr = new int[findArr.length];

			for(int i = 0; i < findArr.length; i++) {
				if(!map.containsKey(findArr[i])) {
					map.put(findArr[i], idx);
					idxArr[i] = idx;
					idxList.add(findArr[i]);
					idx++;
				} else {
					idxArr[i] = map.get(findArr[i]);
				}
			}

			Arrays.sort(idxArr); // 오름차순 정렬 

			String replace = "[ ";
			for(int i = 0; i < idxArr.length; i++) {
				if(i == idxArr.length-1) {
					replace += idxArr[i];
				} else {
					replace += idxArr[i] + ", ";
				}
			}
			replace += " ]";

			resultList.add(replace);
		}

//		for(int i = 0; i < resultList.size(); i++) {
//			text2 = text2.replace("*", resultList.get(i));
//			resultList.remove(i);
//			i--;
//		}
		
		for(int i = 0; i < resultList.size(); i++) {
			text2 = text2.replaceFirst("\\*", resultList.get(i));
		}

		StringBuilder sb = new StringBuilder();

		sb.append(text2).append("\n");

		for(int i = 0; i < idxList.size(); i++) {
			sb.append("[");
			sb.append(i+1);
			sb.append("] ");
			sb.append(idxList.get(i));
			sb.append("\n");
		}

		System.out.println(sb);
	}

}
