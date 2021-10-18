import java.util.*;

public class Problem3 {

	public static void main(String[] args) {
		//String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};

		String answer = solution(8, 2, cmd);
		System.out.println(answer);
	}

	public static String solution(int n, int k, String[] cmd) {
		Stack<Integer> stLeft = new Stack<>();
		Stack<Integer> stRight = new Stack<>();

		int[] ans = new int[n];
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i <= k; i++) {
			stLeft.push(i);
			sb.append("O");
		}

		if(k != n-1) {
			for(int i = n-1; i > k; i--) {
				stRight.push(i);
				sb.append("O");
			}
		}

		int delete = 0;
		Stack<Integer> stDelete = new Stack<>();

		for(int i = 0; i < cmd.length; i++) {
			StringTokenizer st = new StringTokenizer(cmd[i]);
			String c = st.nextToken();

			if(c.equals("U")) { // 아래로 이동 - Right -> Left
				int cnt = Integer.parseInt(st.nextToken());
				
				for(int j = 0; j < cnt; j++) { 
					stRight.push(stLeft.pop());
				}
			} else if(c.equals("D")) { // 위로 이동 - Left -> Right
				int cnt = Integer.parseInt(st.nextToken());

				for(int j = 0; j < cnt; j++) {
					stLeft.push(stRight.pop());
				}
			} else if(c.equals("C")) { // 삭제 
				delete = stLeft.pop(); // 현재행 삭제 
				//ans[delete] = -1;
				stDelete.push(delete);
	
				sb.setCharAt(delete, 'X');
				
				// 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 
				// 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
				if(!stRight.isEmpty()) { // 
					stLeft.push(stRight.pop());
				}
			} else { // Z - 최근 삭제행 복구 
				int restore = stDelete.pop(); // 최근에 삭제한 값 가져옴 
				//ans[restore] = 0;
				sb.setCharAt(restore, 'O');

				if(restore > stLeft.peek()) { // 현재행보다 크다면 오른쪽에 삽입 
					Stack<Integer> temp = new Stack<>();

					while(!stRight.isEmpty() && stRight.peek() < restore) {
						temp.push(stRight.pop());
					}
					
					stRight.push(restore);

					while(!temp.isEmpty()) {
						stRight.push(temp.pop());
					}
				} else { // 현재행보다 작다면 왼쪽에 삽입 
					Stack<Integer> temp = new Stack<>();

					while(!stLeft.isEmpty() && stLeft.peek() > restore) {
						temp.push(stLeft.pop());
					}
					
					stLeft.push(restore);

					while(!temp.isEmpty()) {
						stLeft.push(temp.pop());
					}
				}
			}
		}

		// 정답 출력 
//		StringBuilder sb = new StringBuilder();
//
//		for(int i = 0; i < ans.length; i++) {
//			if(ans[i] == -1) {
//				sb.append("X");
//			} else {
//				sb.append("O");
//			}
//		}

		return sb.toString();
	}
}