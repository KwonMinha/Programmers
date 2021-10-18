/*
 * 해피 넘버 구하기 
 */

import java.util.ArrayList;

public class Solution1 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		
		int input = 66;
		
		int n = input; //int n = Integer.parseInt(input);
		
		while(true) {
			String[] arr = String.valueOf(n).split("");
			
			int s = 0;
			
			for(int i = 0; i < arr.length; i++) {
				s += (int) Math.pow(Double.parseDouble(arr[i]), 2);
			}
			
			if(s == 1) {
				System.out.println(input + " is a Happy number");
				break;
			}
			
			if(list.contains(s)) {
				System.out.println(input + " is an Unhappy number");
				break;
			}
			
			list.add(s);
			
			n = s;
		}
	}

}
