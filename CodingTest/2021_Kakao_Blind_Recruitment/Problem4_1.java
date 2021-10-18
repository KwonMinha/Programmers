import java.util.Arrays;
import java.util.PriorityQueue;

class Problem4_1 {
	
	class Node implements Comparable<Node>{
		int node;
		int cost;

		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	static int answer = Integer.MAX_VALUE;

	public int solution(int n, int start, int end, int[][] roads, int[] traps) {
		int[][] map = new int[n + 1][n + 1];
		boolean [] isTrap = new boolean[n + 1];

		for(int i = 0; i <= n; i++) {
			Arrays.fill(map[i], -1);
		}

		for(int i = 0; i < roads.length; i++) {
			int startNode = roads[i][0];
			int endNode = roads[i][1];
			int cost = roads[i][2];

			if(map[startNode][endNode] > 0) {
				map[startNode][endNode] = Math.min(map[startNode][endNode], cost);
			} else if(map[startNode][endNode] == -1){
				map[startNode][endNode] = cost;
				map[endNode][startNode] = 0;
			} else {
				map[startNode][endNode] = cost;
			}
		}

		for(int i = 0; i < traps.length; i++) {
			isTrap[traps[i]] = true;
		}

		int [] distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		Node now;

		while (!pq.isEmpty()) {
			now = pq.poll(); // 시작 정점
			int nowNode = now.node; // 시작 정점이 가진 다음 정점
			int nowCost = now.cost; // 시작 정점이 가진 가중치

			if(isTrap[nowNode]) {
				for(int i = 1; i <= n; i++) {
					if(map[nowNode][i] >= 0) {
						int temp = map[nowNode][i];
						map[nowNode][i] = map[i][nowNode];
						map[i][nowNode] = temp;
					}
				}
			}

			for (int i = 1; i <= n; i++) {
				if(map[nowNode][i] > 0) {
					if(distance[i] > nowCost + map[nowNode][i] && distance[end] > nowCost + map[nowNode][i]) {
						if(i == end) {
							distance[i] = nowCost + map[nowNode][i];
						} else {
							distance[i] = nowCost + map[nowNode][i];
							pq.add(new Node(i, distance[i]));
						}
					} else if(isTrap[i] && distance[end] > nowCost + map[nowNode][i]) {
						pq.add(new Node(i, nowCost + map[nowNode][i]));
					}
				}
			}
		}
		
		answer = distance[end];

		return answer;
	}
}