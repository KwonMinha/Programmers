import java.util.ArrayList;
import java.util.HashSet;

class OperReward {
	static ArrayList<Long> result = new ArrayList();

	public static void main(String[] args) {
		String exp1 = "100-200*300-500+20";
		//String exp2 = "50*6-3*2";

		solution(exp1);
		//solution(exp2);
		
		long max = 0;
		for(int i = 0; i < result.size(); i++) {
			if(result.get(i) > max)
				max = result.get(i);
		}
		
		System.out.println(max);
	}



	public static long solution(String expression) {
		long answer = 0;

		//list에 숫자 연산자 잘라서 넣기
		ArrayList<String> list = new ArrayList();
		ArrayList<String> operList = new ArrayList();
		HashSet<String> operSet = new HashSet();

		int index = 0;
		for(int i = 0; i < expression.length(); i++) {
			char exp = expression.charAt(i);
			if(exp == '+' || exp == '-' || exp == '*') {
				String str = expression.substring(index, i);
				list.add(str);
				list.add(exp + "");
				index = i+1;
				operList.add(exp + "");
				operSet.add(exp + "");
			}
		}   
		list.add(expression.substring(index, expression.length()));

		String[] oper = new String[operSet.size()];
		operSet.toArray(oper);

		//순열 만들어서 !개 만들기
		String[] output = new String[oper.length];
		boolean[] visited = new boolean[oper.length];
		per(oper, output, visited, 0, oper.length, oper.length, list);

		long max = 0;
		for(int i = 0; i < result.size(); i++) {
			if(result.get(i) > max) {
				max = result.get(i);
				answer = max;
			}
		}

		return answer;
	}

	//순열 찾기
	static void per(String[] arr, String[] output, boolean[] visited, int depth, int n, int r, ArrayList<String> list) {
		if(depth == r) {
			cal(output, r, list);
			return;
		}

		for(int i=0; i<n; i++) {
			if(visited[i] != true) {
				visited[i] = true;
				output[depth] = arr[i];
				per(arr, output, visited, depth + 1, n, r, list);  
				visited[i] = false;
			}
		}
	}

	//ArrayList 출력 함수 
	static void print2(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
		}   
		System.out.println();
	}

	static void cal(String[] oper, int r, ArrayList<String> originList) {
		ArrayList<String> list = new ArrayList<String>(originList);

		for(int i = 0; i < oper.length; i++) {
			String o = oper[i];

			for(int j = 0; j < list.size(); j++) {
				if(list.get(j).equals(o)) {
					int n1 = Integer.parseInt(list.get(j-1));
					int n2 = Integer.parseInt(list.get(j+1));
					if(o.equals("*")) {
						int res = n1 * n2;
						list.set(j , res+"");
						list.remove(j+1);
						list.remove(j-1);
						j--;
					} else if(o.equals("-")) {
						int res = n1 - n2;
						list.set(j , res+"");
						list.remove(j+1);
						list.remove(j-1);
						j--;
					} else if(o.equals("+")) {
						int res = n1 + n2;
						list.set(j , res+"");
						list.remove(j+1);
						list.remove(j-1);
						j--;
					} 
				}
			}
		}
		
		result.add(Math.abs(Long.parseLong(list.get(0))));
	}

}