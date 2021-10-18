/*
 * 변화하는 중앙 값
 * https://blog.junu.dev/12
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution3 {

	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		long[] arr = new long[N];
		arr[0] = 1983;

		for(int i = 1; i < N; i++) {
			arr[i] = arr[i-1] * (a + b) % 20090711;
		}

		PriorityQueue<Long> low = new PriorityQueue<>(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return o2.compareTo(o1);
			}
		});

		PriorityQueue<Long> high = new PriorityQueue<>();

		long answer = 0;

		for(int i = 0; i < arr.length; i++) {
			// 큐에 숫자 추가 
			if(low.isEmpty() || arr[i] < low.peek()) {
				low.add(arr[i]);
			} else {
				high.add(arr[i]);
			}

			// 큐 사이즈 조절 
			if(high.size() < low.size()) {
				if(low.size() - high.size() >= 2) {
					high.add(low.poll());
				}
			} else {
				if(high.size() - low.size() >= 2) {
					low.add(high.poll());
				}
			}

			// 중간 값 구하기 
			if(low.size() == high.size()) {
				answer += low.peek();
			} else {
				answer += low.size() > high.size() ? low.peek() : high.peek();
			}
		}

		System.out.println(answer % 20090711);
	}

}
