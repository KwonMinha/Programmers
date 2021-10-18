import java.util.*;

public class Problem2 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) {
		String[][] place= {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
		int[] ans = solution(place);
		System.out.println(Arrays.toString(ans));
	}

	public static int[] solution(String[][] places) {
		int[] answer = new int[5];

		for(int i = 0; i < 5; i++) {
			answer[i] = solve(places[i]);
		}

		return answer;
	}

	static int solve(String[] place) {
		char[][] map = new char[5][5];

		for(int i = 0; i < 5; i++) {
			map[i] = place[i].toCharArray();
		}

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(map[i][j] == 'P') {
					if(!check(map, i, j)) {
						return 0;
					}
				}
			}
		}

		return 1;
	}

	static boolean check(char[][] map, int x, int y) {
		boolean[][] visit = new boolean[5][5];
		visit[x][y] = true;

		for(int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if(nextX >= 0 && nextY >= 0 && nextX < 5 && nextY < 5 && visit[nextX][nextY] == false) {
				if(map[nextX][nextY] == 'P') {
					return false;
				} else if (map[nextX][nextY] == 'X') {
					visit[nextX][nextY] = true;
				} else {
					for(int j = 0; j < 4; j++) {
						int nextX2 = nextX + dx[j];
						int nextY2 = nextY + dy[j];

						if(nextX2 >= 0 && nextY2 >= 0 && nextX2 < 5 && nextY2 < 5 && visit[nextX2][nextY2] == false) {
							if(map[nextX2][nextY2] == 'P') {
								return false;
							} else if (map[nextX2][nextY2] == 'X') {
								visit[nextX2][nextY2] = true;
							} else if (map[nextX2][nextY2] == 'O') {
								visit[nextX2][nextY2] = true;
							}
						}
					}
				}
			}
		}

		return true;
	}

}
