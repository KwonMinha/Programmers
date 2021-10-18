/*
 * - 하이픈으로 나눠진 수를 비교해 다른 비트 수 구하기
 * 
		3
		3
		255-128-3
		254-128-3
		254-128-19
		1
		0
		0
		1
		3
		254-255-255
		255-254-255
		255-255-254
 */

import java.util.Arrays;

public class Solution2 {

	public static void main(String[] args) {

		//		// 테스트케이스만큼 반복 
		//		Scanner sc = new Scanner(System.in);
		//		
		//		int L = sc.nextInt();
		//		sc.nextLine();
		//		String str1 = sc.nextLine();
		//		String str2 = sc.nextLine();
		//		String str3 = sc.nextLine();

		int L = 3;
		String str1 = "255-128-3";
		String str2 = "254-128-3";
		String str3 = "254-128-19";

		int[] arr1 = Arrays.stream(str1.split("-"))
				.mapToInt(Integer::parseInt)
				.toArray();

		int[] arr2 = Arrays.stream(str2.split("-"))
				.mapToInt(Integer::parseInt)
				.toArray();

		int[] arr3 = Arrays.stream(str3.split("-"))
				.mapToInt(Integer::parseInt)
				.toArray();

		int cnt = 0;

		for(int i = 0; i < L; i++) {
			if(arr1[i] == arr2[i] && arr2[i] == arr3[i]) {
				continue;
			} else {
				String b1 = Integer.toBinaryString(arr1[i]); 
				String b2 = Integer.toBinaryString(arr2[i]); 
				String b3 = Integer.toBinaryString(arr3[i]); 

				int d = Math.max(b1.length(), Math.max(b2.length(), b3.length()));

				b1 = String.format("%0" + d + "d", Integer.parseInt(b1));
				b2 = String.format("%0" + d + "d", Integer.parseInt(b2));
				b3 = String.format("%0" + d + "d", Integer.parseInt(b3));

				for(int j = 0; j < b1.length(); j++) {
					if(b1.charAt(j) == b2.charAt(j) && b2.charAt(j) == b3.charAt(j)) {
						continue;
					} else {
						cnt++;
					}
				}
			}
		}

		System.out.println(cnt);
	}

}
