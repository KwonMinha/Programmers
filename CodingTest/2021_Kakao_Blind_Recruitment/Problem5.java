import java.util.*;

public class Problem5 {
	static int[] tree;
	static ArrayList<Point> pointList = new ArrayList<>();


	public static void main(String[] args) {
		int[] num = {12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1};
		int[][] links = {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {8, 5}, {2, 10}, {3, 0}, {6, 1}, {11, -1}, {7, 4}, {-1, -1}, {-1, -1}};

		int answer = solution(3, num, links);
		System.out.println(answer);
	}

	public static int solution(int k, int[] num, int[][] links) {
		int answer = 0;
		
		tree = num.clone();

		int idx = 0;
		for(int i = 0; i < links.length; i++) {
			int node = links[i][0];
			int left = links[i][1];
			int right = links[i][2];

			if(left != -1) {
				pointList.add(new Point(idx, node, left));
			}

			if(right != -1) {
				pointList.add(new Point(idx, node, right));
			}
		}
		
		int[] arr = new int[k]; 
		combination(arr, 0, num.length, k, 0);


		return answer;
	}

//	static int init(int start, int end, int node) {
//		if (start == end) {
//			return tree[node] = arr[start];
//		} else {
//			int mid = (start + end) / 2;
//			return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
//		}
//	}

	// left, right - 구해야하는 구간 합 범위
	static int sum(int start, int end, int node, int left, long right) {
		if (left > end || right < start) {  // 범위 밖에 있는 경우
			return 0;
		}
		if (left <= start && end <= right) { // 범위 안에 속하는 경우
			return tree[node];
		}
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}

	static void solve(int[] arr) {

	}

	static void combination(int[] arr, int index, int n, int r, int target) { 
		if (r == 0) 
			solve(arr);
		else if (target == n) 
			return; 
		else { 
			arr[index] = target;
			combination(arr, index + 1, n, r - 1, target + 1); 
			combination(arr, index, n, r, target + 1); 
		} 
	}
}

class Point {
	int i;
	int p;
	int c;

	Point(int i, int p, int c) {
		this.i = i;
		this.p = p;
		this.c = c;
	}
}
