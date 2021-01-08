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
	static int[][] columns;
	static int[][] beams;

	static ArrayList<Node> col = new ArrayList<>();
	static ArrayList<Node> beam = new ArrayList<>();

	static Node[][] c;
	static Node[][] b;

	public static void main(String[] args) {
		int n = 5;
		//int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};

		int[][] build_frame = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};


		solution(n, build_frame);
	}

	public static int[][] solution(int n, int[][] build_frame) {
		int[][] answer;
		ArrayList<int[]> list = new ArrayList<>();

		N = n;

		columns = new int[n+2][n+2];
		beams = new int[n+2][n+2];

		c = new Node[n+2][n+2];
		b = new Node[n+2][n+2];

		for(int i = 0; i < build_frame.length; i++) {
			int[] frame = build_frame[i];
			int x = frame[0];
			int y = frame[1];
			int a = frame[2];
			int b = frame[3];

			if(b == 0) { // 삭제할 경우 
				delete(x, y, a);
			} else { // 설치할 경우 
				create(x, y, a);
			}
		}  

		for(int i = 0; i < n+2; i++) {
			for(int j = 0; j < n+2; j++) {

				if(c[i][j] != null) {
					int[] arr = {i, j, 0};
					list.add(arr);
				}

				if(b[i][j] != null) {
					int[] arr = {i, j, 1};
					list.add(arr);
				}
			}
		}

		answer = new int[list.size()][3];

		for(int i = 0; i < answer.length; i++) {
			answer[i][0] = list.get(i)[0];
			answer[i][1] = list.get(i)[1];
			answer[i][2] = list.get(i)[2];

			//System.out.println(answer[i][0] + " " + answer[i][1] + " " + answer[i][2]);
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

	public static void delete(int x, int y, int a) {
		int[][] copyCol = new int[N+2][N+2];
		int[][] copyBeam = new int[N+2][N+2];

		for(int i = 0; i < N+2; i++) {
			System.arraycopy(columns[i], 0, copyCol[i], 0, N+2);
			System.arraycopy(beams[i], 0, copyBeam[i], 0, N+2);
		}

		if(a == 0) { // 기둥일 때 
			//System.out.println("기둥일 때 ");
			copyCol[x][y] -= 1;
			copyCol[x][y+1] -= 1;
			
			if(isDelete(copyCol, copyBeam)) {
				columns[x][y] -= 1;
				columns[x][y+1] -= 1;
				c[x][y] = null;

				for(int i = 0; i < col.size(); i++) {
					if(col.get(i).x1 == x && col.get(i).y1 == y) {
						col.remove(i);
					}
				}
			}		
		} else { // 보일 때 
			//System.out.println("보일 때 ");
			copyBeam[x][y] -= 1;
			copyBeam[x+1][y] -= 1;
			

			if(isDelete(copyCol, copyBeam)) {
				beams[x][y] -= 1;
				beams[x+1][y] -= 1;
				b[x][y] = null;

				for(int i = 0; i < beam.size(); i++) {
					if(beam.get(i).x1 == x && beam.get(i).y1 == y) {
						beam.remove(i);
					}
				}
			}
		}
	}

	public static boolean isDelete(int[][] copyCol, int[][] copyBeam) {
//		System.out.println("삭제 기둥  ");
//		print(copyCol);
//		System.out.println("삭제 보   ");
//		print(copyBeam);
		
		boolean flag = true;

		for(int i = 0; i < col.size(); i++) {
			int x = col.get(i).x1;
			int y = col.get(i).y1;

			if(y == 0 || copyBeam[x][y] >= 1 || copyBeam[x][y+1] >= 1 || copyCol[x][y] >= 1) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		for(int i = 0; i < beam.size(); i++) {
			int x = beam.get(i).x1;
			int y = beam.get(i).y1;

			if(copyCol[x][y] >= 1 || copyCol[x+1][y] >= 1) { // 한쪽 끝 기둥일 경우 
				flag = true;
			} else if(copyBeam[x][y] > 1 && copyBeam[x+1][y] > 1) { // 양쪽 끝 보일 경우 
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		
		//System.out.println("flag : " + flag);

		if(flag)
			return true;
		else
			return false;


	}

	public static void create(int x, int y, int a) {
		if(a == 0) { // 기둥일 때 
			// 바닥, 보, 기둥에 세울 때만 설치 
			if(y == 0 || beams[x][y] >= 1 || beams[x][y+1] >= 1 || columns[x][y] >= 1) {
				columns[x][y] += 1;
				columns[x][y+1] += 1;

				Node node = new Node(x, y, x, y+1);
				col.add(node);

				c[x][y] = node;
			}
		} else { // 보일 때 
			if(columns[x][y] >= 1 || columns[x+1][y] >= 1) { // 한쪽 끝 기둥일 경우 
				beams[x][y] += 1;
				beams[x+1][y] += 1;

				Node node = new Node(x, y, x, y+1);
				beam.add(node);
				b[x][y] = node;
				
//				System.out.println("보 삽입 1 :  " + x + " " + y + " " + a);
//				print(beams);
			} else if(beams[x][y] >= 1 && beams[x+1][y] >= 1) { // 양쪽 끝 보일 경우 
				beams[x][y] += 1;
				beams[x+1][y] += 1;

				Node node = new Node(x, y, x, y+1);
				beam.add(node);
				b[x][y] = node;
				
//				System.out.println("보 삽입 2 :  " + x + " " + y + " " + a);
//				print(beams);
			}
		}
	}

}

class Node {
	int x1;
	int y1;
	int x2;
	int y2;

	Node(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
}




