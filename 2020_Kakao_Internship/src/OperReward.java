import java.util.ArrayList;
import java.util.HashSet;

class OperReward {
	public static long answer = Long.MIN_VALUE;
	public static ArrayList<String> expList = new ArrayList<>();
	public static String[] oper;
	public static String[] output;
	public static boolean[] visited;

	public static void main(String[] args) {
		String exp1 = "100-200*300-500+20";
		String exp2 = "50*6-3*2";

		System.out.println(solution(exp1));
	}

	public static long solution(String expression) {
		HashSet<String> operSet = new HashSet<>();

		int index = 0;
		for(int i = 0; i < expression.length(); i++) {
			char exp = expression.charAt(i);
			if(exp == '+' || exp == '-' || exp == '*') {
				String str = expression.substring(index, i);
				expList.add(str);
				expList.add(exp + "");
				index = i+1;
				operSet.add(exp + "");
			}
		}   
		expList.add(expression.substring(index, expression.length()));

		oper = new String[operSet.size()];
		operSet.toArray(oper);
		
		//순열 만들기
		output = new String[oper.length];
		visited = new boolean[oper.length];
		per(0, oper.length);
		
		return answer;
	}

	//순열 찾기
	static void per(int depth, int r) {
		if(depth == r) {
			solve();
		
			return;
		}

		for(int i = 0; i < oper.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = oper[i];
				per(depth + 1, r);  
				visited[i] = false;
			}
		}
	}

	static void solve() {
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(expList);

		for(int i = 0; i < output.length; i++) {
			String o = output[i];

			for(int j = 0; j < list.size()-1; j++) {
				if(list.get(j).equals(o)) {
					long n1 = Integer.parseInt(list.get(j-1));
					long n2 = Integer.parseInt(list.get(j+1));
					if(o.equals("*")) {
						long res = n1 * n2;
						list.set(j , res + "");
						list.remove(j+1);
						list.remove(j-1);
						j--;
					} else if(o.equals("-")) {
						long res = n1 - n2;
						list.set(j , res + "");
						list.remove(j+1);
						list.remove(j-1);
						j--;
					} else if(o.equals("+")) {
						long res = n1 + n2;
						list.set(j , res + "");
						list.remove(j+1);
						list.remove(j-1);
						j--;
					} 
				}
			}
		}

		long res = Math.abs(Long.parseLong(list.get(0)));
		answer = Math.max(answer, res);
	}

}