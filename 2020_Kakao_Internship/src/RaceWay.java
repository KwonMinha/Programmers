/**
 * @author Minha Gwon
 * @date 2021. 1. 30.
 * 
 * 2020 카카오 인턴십 - 경주로 건설
 * https://programmers.co.kr/learn/courses/30/lessons/67259
 */

import java.util.LinkedList;
import java.util.Queue;

public class RaceWay {
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	public static int N;
	public static int[][] visited;
	public static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		int[][] b1 = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},
				{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},
				{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
		
		int[][] board = {
		        {0, 0, 0, 0, 0},
		        {0, 1, 1, 1, 0},
		        {0, 0, 1, 0, 0},
		        {1, 0, 0, 0, 1},
		        {0, 1, 1, 0, 0}
		    };

		int[][] b2 = {{0,0,0},{0,0,0},{0,0,0}};

		System.out.println(solution(board));
	}

	public  static int solution(int[][] board) {
		N = board.length;
		
		// 기존의 BFS에서의 visited 배열은 방문했나 안했나 T/F만 체크 
		// 하지만 지금은 BFS로 최단 경로 탐색을 하면서, 이미 방문한 곳도 비용이 더 적게 든다면 재방문이 가능해야함 
		// 따라서 int형 배열로 비용의 정보를 저장하도록 함 
		visited = new int[N][N]; 

		bfs(board);

		return min;
	}


	public static void bfs(int[][] board) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, -1, 0)); // 시작점의 dir은 -1 
		visited[0][0] = 1; // 시작점은 방문했다는 의미로 1 

		while(!queue.isEmpty()) {
			int cx = queue.peek().x;
			int cy = queue.peek().y;
			int cd = queue.peek().dir;
			int cost = queue.poll().cost;
			
			// 도착점이라면 더 작은 비용으로 갱신
			// (BFS를 통해(이미 방문한 곳이라도 cost가 작아진다면 또 방문함) 도착점에 다른 비용으로 여러번 올 수 있기 때문) 
			if(cx ==  N-1 && cy == N-1) { 
				min = Math.min(min, cost);
				continue; // 갱신했으니 다음 큐에 있는 포인트 처리 
			}

			for(int i = 0; i < 4; i++) { // 상하좌우 새로운 포인트로 이동 
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				int nd = i;

				if(nx < 0 || ny < 0|| nx >= N || ny >= N || board[nx][ny] == 1) // board 범위를 벗어나거나, 벽인 경우 pass 
					continue;	

				int newCost = cost; // 새로운 포인트의 비용은, 이전 비용의 값으로 초기화  
				if(cd == -1 || cd == nd) { // 처음 시작(-1)이거나 방향이 같은 경우 -> +무조건 직선 도로 100 
					newCost += 100; 
				} else {
					newCost += 600; // +(직선 도로 100 + 코너 500)
				}

				if(visited[nx][ny] == 0) { // 처음 방문하는 곳일 경우 
					visited[nx][ny] = newCost; // 새로운 비용으로 초기화 
					queue.add(new Point(nx, ny, nd, newCost));
				} else if(visited[nx][ny] >= newCost) { // 이미 방문한 곳이더라도, 어떤 경로로 왔든 이전 경로의 비용보다 새로운 비용이 같거나 작다면 갱신  
					visited[nx][ny] = newCost; // 무조건 작은 경우만 되는게 아니라, 같은 경우도 확인해야지 혹시나 있을지 모르는 최소 비용을 구할 수 있음
					queue.add(new Point(nx, ny, nd, newCost));
				}
			}
		}
	}


}

class Point {
	int x; 
	int y;
	int dir; // 거리 
	int cost; // 비용 

	Point(int x, int y, int dir, int cost) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cost = cost;
	}
}
