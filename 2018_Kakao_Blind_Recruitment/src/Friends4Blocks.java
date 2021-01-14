/**
 * @author Minha Gwon
 * @date 2021. 1. 15.
 * 
 * 2018 카카오 신입 공채 - [1차] 프렌즈4블록 
 * https://programmers.co.kr/learn/courses/30/lessons/17679
 */

public class Friends4Blocks {
	public static int[][] map;
	public static int[] dx = {0, 1, 1};
	public static int[] dy = {1, 1, 0};

	public static void main(String[] args) {
		int m = 6; // 4
		int n = 6; // 6
		//String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};

		solution(m, n, board);
	}

	public static int solution(int m, int n, String[] board) {
		int answer = 0;

		map = new int[m][n];
		for(int i = 0; i < m; i++) {
			String[] row = board[i].split("");

			for(int j = 0; j < n; j++) {
				map[i][j] = (int) row[j].charAt(0) - 64;
			}
		}

		//	        for(int i = 0; i < m; i++) {
		//	        	for(int j = 0; j < n; j++) {
		//	        		System.out.print(map[i][j] + " ");
		//	        	}
		//	        	System.out.println();
		//	        }

	
		while(true) {
			if(removeBlock(m, n)) {
				mergeBlock(m, n);
			} else {
				break;
			}
		}
	
		return answer;
	}

	public static boolean removeBlock(int m, int n) {
		boolean isRemove = false;
		
		// 블록 삭제하기 
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] != 0) {
					int block = map[i][j];

					boolean flag = true;
					for(int k = 0; k < 3; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						if(nx < 0 || ny < 0 || nx >= m || ny >= n)
							continue;

						if(map[nx][ny] != block) {
							flag = false;
							break;
						}
					}

					if(flag) {
						isRemove = true;
						map[i][j] = 0;
						for(int k = 0; k < 3; k++) {
							map[i+dx[k]][j+dy[k]] = 0;
						}
					}
				}
			}
		}
		
		return isRemove;
	}

	public static void mergeBlock(int m, int n) {

	}

}
