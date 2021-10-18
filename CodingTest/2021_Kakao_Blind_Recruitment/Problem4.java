import java.util.*;

public class Problem4 {

	public static void main(String[] args) {
		int[][] roads = {{1, 2, 2}, {3, 2, 3}};
		int[] traps = {2};
		
		int answer = solution(3, 1, 3, roads, traps);

		System.out.println(answer);
	}

	public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
		int answer = 0;
		
		int INF = 3000 * (roads.length-1);
		
		int[][] arr = new int[n+1][n+1];
		for(int i = 1; i < n+1; i++) {
			Arrays.fill(arr[i], -1); // 가중치 0이 있을수도 있으니 -1로 초기화 
		}

		for(int i = 0; i < roads.length; i++) {
			int p = roads[i][0];
			int q = roads[i][1];
			int s = roads[i][2];
            
			// 시작 정점과 도착 정점이 같은데 비용이 다른 값이 들어올 수 있기 때문에 예외 처리 
			if(arr[p][q] == -1) 
				arr[p][q] = s;
			else if(arr[p][q] > s) 
				arr[p][q] = s;
		}

		boolean[] check = new boolean[n+1]; // 정점이 집합 S에 속하는지 아닌지를 판별할 배열 

		int[] distance = new int[n+1]; // 최단 거리를 담을 배열 
		Arrays.fill(distance, INF); // 무한대로 초기화 
	
		// 처음 시작을 위한 초기화 
		for(int i = 1; i < n+1; i++) {
			if(arr[start][i] != -1) 
				distance[i] = arr[start][i];
		}

		check[start] = true; // 시작 정점 방문 표시 
		distance[start] = 0;
		
		for(int i = 0; i < n-1; i++) { // 시작점을 넣고 시작하기 때문에 N-1만큼만 반복 
			int min = INF;
			int index = -1;

			for(int j = 1; j < n+1; j++) { // 집합 S에 속하지 않는 가장 최단 거리를 갖는 정점 선택 
				if(!check[j] && distance[j] < min) {
					min = distance[j]; // 최단 거리 
					index = j; // 최단 거리를 갖는 정점의 index 
				}
			}

			check[index] = true;
            
			// S에 속하지 않는다면 더 작은 값을 갖는 거리로 distance값 갱신 
			for (int j = 1; j < n+1; j++) { 
				if (!check[j] && arr[index][j] != -1 && distance[index] + arr[index][j] < distance[j]) { // 간선이 연결되지 않은 -1의 경우 역시 제외해야함 
					distance[j] = distance[index] + arr[index][j];
					
					if(Arrays.asList(traps).contains(j)) {
						for(int k = 1; k < n+1; k++) {
							if(arr[j][k] != -1) {
								int temp = arr[j][k];
								arr[j][k] = -1;
								arr[k][j] = temp;
								check[j] = false;
							}
						}
					}
				}
			}
		}

		System.out.println(distance[end]);
		
		return answer;
	}

}
