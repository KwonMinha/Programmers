/**
 * @author Minha Gwon
 * @date 2021. 1. 15.
 * 
 * 2018 카카오 신입 공채 - [1차] 프렌즈4블록 
 * https://programmers.co.kr/learn/courses/30/lessons/17679
 * BLOG - https://minhamina.tistory.com/109
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node {
	int x;
	int y;

	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Friends4Blocks {
	public static int[][] map;
	public static int[] dx = {0, 0, 1, 1}; // 현위치 - 오른쪽 - 오른쪽 아래 - 아래 순서 
	public static int[] dy = {0, 1, 1, 0};
	public static int answer = 0;

	public static void main(String[] args) {
		int m = 6; // 4
		int n = 6; // 6
		//String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};

		System.out.println(solution(m, n, board));
	}

	public static int solution(int m, int n, String[] board) {
		map = new int[m][n];
		for(int i = 0; i < m; i++) {
			String[] row = board[i].split("");

			for(int j = 0; j < n; j++) {
				map[i][j] = (int) row[j].charAt(0) - 64; // 알파벳대신 숫자로 변환해 map 구성 
			}
		}

		while(true) {
			if(removeBlock(m, n)) { // removeBlock true 반환시 삭제 가능 
				mergeBlock(m, n);
			} else {
				break; // 더이상 삭제할 것이 없다면 끝 
			}
		}

		return answer;
	}

	// 블록 삭제하기 
	public static boolean removeBlock(int m, int n) {
		boolean isRemove = false;

		// 중복으로 삭제되지 못하는 블록을 막기 위해,
		// 삭제 가능한 위치의 블록 좌표를 저장 후, 전체 map 탐색 후 한번에 삭제할 것 
		ArrayList<Node> removeList = new ArrayList<>(); 

		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] != 0) {
					int curBlock = map[i][j]; // 현재 블록 

					boolean flag = true;
					for(int k = 1; k < 4; k++) { // 오른쪽 - 오른쪽 아래 - 아래 
						int nx = i + dx[k];
						int ny = j + dy[k];

						if(nx < 0 || ny < 0 || nx >= m || ny >= n) { // 범위를 넘으면 2*2 형태 안됨 
							flag = false;
							break;
						}

						if(map[nx][ny] != curBlock) {  // 같은 블록이 아닐 경우 안됨 
							flag = false;
							break;
						}
					}

					if(flag) // 범위를 넘지 않는 모든 방향이 같은 블록일 경우 
						removeList.add(new Node(i, j));
				}
			}
		}

		if(!removeList.isEmpty()) { // 삭제 가능한 블록이 있다면 
			isRemove = true; // true 반환 

			for(int i = 0; i < removeList.size(); i++) {
				int x = removeList.get(i).x;
				int y = removeList.get(i).y;

				// 삭제 시작 위치 - 오른쪽 - 오른쪽 아래 - 아래 순서 
				for(int j = 0; j < 4; j++) {
					if(map[x+dx[j]][y+dy[j]] != 0) { // 삭제될 자리에 블록이 있다면 삭제하고, 삭제 횟수 추가 
						map[x+dx[j]][y+dy[j]] = 0;
						answer++;
					}
				}
			}
		}

		return isRemove;
	}

	// 블록 아래로 밀기 
	public static void mergeBlock(int m, int n) {
		for(int i = 0; i < n; i++) {
			Queue<Integer> queue = new LinkedList<>();
			for(int j = m-1; j >= 0; j--) {
				if(map[j][i] != 0) { // 각 열의 블록만 큐에 저장 
					queue.add(map[j][i]);
					map[j][i] = 0; // 저장 후 모두 0으로 초기화 
				}
			}

			int size = m - queue.size();
			for(int j = m-1; j >= size; j--) 
				map[j][i] = queue.poll(); // 열의 바닥부터 저장된 블록값 채우기 
		}
	}

}
