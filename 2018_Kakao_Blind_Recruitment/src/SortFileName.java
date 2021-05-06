/**
 * @author Minha Gwon
 * @date 2021. 5. 7.
 * 2018 KAKAO BLIND RECRUITMENT - [3차] 파일명 정렬 
 * https://programmers.co.kr/learn/courses/30/lessons/17686
 */

import java.util.Arrays;
import java.util.Comparator;

public class SortFileName {

	public static void main(String[] args) {
		String[] files = {"IMG", "img12.png", "img10.png","img0000002.png", "imG", "img02.png", "img0002.png", "img1.png", "IMG01.GIF", "img2.JPG", "img0000.JPG", "img0.JPG", "img"};
		String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
		String[] files3 = {"a   03003.txt", "bdb. .02", "c .d. .000", "bdb. .0.txt"};

		String[] ans = solution(files3);
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < ans.length; i++) {
			sb.append("\"");
			sb.append(ans[i]);
			sb.append("\"");
			if(i != ans.length-1) {
				sb.append(", ");
			}
		}
		sb.append("]");

		System.out.println(sb);
	}

	public static String[] solution(String[] files) {
		Arrays.sort(files, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int index1 = getHeadIndex(o1);
				int index2 = getHeadIndex(o2);
				
				String head1 = o1.substring(0, index1).toLowerCase();
				String head2 = o2.substring(0, index2).toLowerCase();
				
				int num1 = getNumber(o1, index1);
				int num2 = getNumber(o2, index2);

				if(head1.equals(head2)) {
					if(num1 < num2) {
						return -1;
					} else if(num1 == num2) {
						return 0;
					} else {
						return 1;
					}
				} else {
					return head1.compareTo(head2);
				}
			}
		}); 
		
		return files;
	}

	public static int getHeadIndex(String str) {
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if(ch > 47 && ch < 58) { // 0 유니코드 48, 9 유니코드 57 - 숫자인 경우 
				return i;
			}
		}
		
		return str.length();
	}

	public static int getNumber(String str, int idx) {
		int j = idx;
		while(j < str.length() && (str.charAt(j) > 47 && str.charAt(j) < 58)) {
			j++;
		}
		
		String num = str.substring(idx, j);
		
		for(int i = 0; i < num.length(); i++) {
			if(num.charAt(i) != '0') {
				num = num.substring(i, num.length());
				break;
			}
		}
		
		if(!num.equals(""))
			return Integer.parseInt(num);
		else
			return -1;
	}

}
