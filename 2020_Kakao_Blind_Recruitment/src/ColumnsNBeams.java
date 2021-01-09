/**
 * @author Minha Gwon
 * @date 2021. 1. 9.
 * 
 * 2020 카카오 신입 공채 - 기둥과 보 설치 
 * https://programmers.co.kr/learn/courses/30/lessons/60061
 */

import java.util.ArrayList;

public class ColumnsNBeams {
	static int N;
	static boolean[][] columns;
	static boolean[][] beams;

	public static void main(String[] args) {
		int n = 5;
		//int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};

		int[][] build_frame = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};


		solution(n, build_frame);
	}

	public static int[][] solution(int n, int[][] build_frame) {
		N = n;

		int[][] answer;
		
		columns = new boolean[N+3][N+3];
		beams = new boolean[N+3][N+3];

		for(int i = 0; i < build_frame.length; i++) {
			int[] frame = build_frame[i];
			int x = frame[0]+1;
			int y = frame[1]+1;
			int a = frame[2];
			int b = frame[3];

			if(b == 0) { // 삭제할 경우 
				delete(x, y, a);
			} else { // 설치할 경우 
				create(x, y, a);
			}
		}  
		
		ArrayList<int[]> list = new ArrayList<>();
		for(int i = 1; i <= N+1; ++i) {
			for(int j = 1; j <= N+1; ++j) {
				if(columns[i][j]) {
					list.add(new int[] {i-1, j-1, 0});
				}
				
				if(beams[i][j]) {
					list.add(new int[] {i-1, j-1, 1});
				}
			}
		}
		
		answer = new int[list.size()][3];
		for(int i = 0; i < answer.length; i++) {
			answer[i][0] = list.get(i)[0];
			answer[i][1] = list.get(i)[1];
			answer[i][2] = list.get(i)[2];

			System.out.println(answer[i][0] + " " + answer[i][1] + " " + answer[i][2]);
		}
		

		return answer;
	}

	public static void print(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// 설치하기 
	public static void create(int x, int y, int a) {
		if(a == 0) { // 기둥일 때 
			if(canColumn(x, y)) {
				columns[x][y] = true;
			}
		} else { // 보일 때 
			if(canBeam(x, y)) {
				beams[x][y] = true;
			}
		}
	}

	// 삭제하기 
	public static void delete(int x, int y, int a) {
		if(a == 0) { // 기둥일 때, 일단 기둥 삭제 
			columns[x][y] = false;	
		} else { // 보일 때, 일단 보 삭제 
			beams[x][y] = false;
		}

		// 삭제 후 남은 기둥과 보가 요건에 충족하는지 다시 세우면서 확인 
		if(!canDelete(x, y)) { // 삭제할 수 없다면 
			if(a == 0) { // 기둥 원상태로 
				columns[x][y] = true;	
			} else { // 보 원상태로 
				beams[x][y] = true;
			}
		}
	}

	public static boolean canDelete(int x, int y) {
		for(int i = 1; i <= N+1; ++i) {
			for(int j = 1; j <= N+1; ++j) {
				if(columns[x][y] && !canColumn(x, y)) { // 기둥 확인 
					return false;
				}

				if(beams[x][y] && !canBeam(x, y)) { // 보 확인 
					return false;
				}
			}
		}

		return true;
	}

	public static boolean canColumn(int x, int y) {
		if(y == 1) { // 기둥이 바닥 위에 있을 때 
			return true;
		} else if(beams[x][y] == true || beams[x-1][y] == true) { // 보의 한쪽 끝 부분 위에 있을 때 
			return true;
		} else if(columns[x][y-1] == true) { // 다른 기둥 위에 있을 때 
			return true;
		}

		return false;
	}

	public static boolean canBeam(int x, int y) {
		if(columns[x][y-1] == true || columns[x+1][y-1] == true) { // 한쪽 끝 부분이 기둥 위일 때 
			return true;
		} else if(beams[x+1][y] == true || beams[x-1][y] == true) { // 양쪽 끝 부분이 다른 보와 동시에 연결되어있을 때 
			return true;
		}

		return false;
	}

}

class Node {
	int x;
	int y;
	int a;

	Node(int x, int y, int a) {
		this.x = x;
		this.y = y;
		this.a = a;
	}
}




