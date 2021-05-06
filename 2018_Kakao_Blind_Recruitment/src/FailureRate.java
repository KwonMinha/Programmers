/**
 * @author Minha Gwon
 * @date 2021. 5. 7.
 * 2019 KAKAO BLIND RECRUITMENT - 실패율 
 * https://programmers.co.kr/learn/courses/30/lessons/42889
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FailureRate {
	static class Node implements Comparable<Node>{
		int idx;
		double val;

		Node(int idx, double val) {
			this.idx = idx;
			this.val = val;
		}

		@Override
		public int compareTo(Node o) {
			if(o.val < this.val) {
				return -1;
			} else if(o.val == this.val) { 
				return 0;
			} else {
				return 1;
			}
		}
	}

	public static void main(String[] args) {
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		int[] stages2 = {4, 4, 4, 4, 4};

		int[] answer = solution(5, stages);
		System.out.println(Arrays.toString(answer));
	}

	public static int[] solution(int N, int[] stages) {
		int[] answer = new int[N];

		int[] people = new int[N+2];

		for(int i = 0; i < stages.length; i++) {
			people[stages[i]] += 1;
		}

		ArrayList<Node> list = new ArrayList<>();

		double total = stages.length;

		for(int i = 1; i < people.length-1; i++) {
			if(people[i] == 0) {
				list.add(new Node(i, 0));
				continue;
			}

			double rate = people[i] / total;
			total -= people[i];

			list.add(new Node(i, rate));
		}

		Collections.sort(list);
		
		for(int i = 0; i < N; i++) {
			//System.out.println(list.get(i).idx + " " + list.get(i).val);
			answer[i] = list.get(i).idx;
		}

		return answer;
	}

}