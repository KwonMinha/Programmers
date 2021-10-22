import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LINE2_2 {

	public static void main(String[] args) {
		//		String program = "line"; 
		//		String[] flag_rules = {"-s STRINGS", "-n NUMBERS", "-e NULL"};
		//		String[] commands = {"line -n 100 102 -s hi -e", "line -n id pwd -n 100"};

		String program = "trip"; 
		String[] flag_rules = {"-days NUMBERS", "-dest STRING"};
		String[] commands = {"trip -days 15 10 -dest Seoul Paris", "trip -days 10 -dest Seoul"};

		boolean[] arr = solution(program, flag_rules, commands);


		System.out.println();
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static boolean[] solution(String program, String[] flag_rules, String[] commands) {
		boolean[] answer = new boolean[commands.length];

		// flag, flag_type, HashMap에 Key-Value로 저장 
		HashMap<String, String> hashMap = new HashMap<>();
		for(int i = 0; i < flag_rules.length; i++) {
			String[] rule = flag_rules[i].split(" ");
			hashMap.put(rule[0], rule[1]);
		}

		// 명령어 처리 
		for(int i = 0; i < commands.length; i++) {
			System.out.println("\ncommand : " + commands[i]);
			String[] command = commands[i].split(" ");

			String program_name = command[0];

			// 1. 프로그램 이름 검사 	
			if(!program_name.equals(program)) {
				System.out.println("* error 1\n");
				continue;
			}
			HashSet<String> set = new HashSet<>(); // 명령어를 처리하면서 나온 flag 저장 (중복 방지를 위함)

			// 2. flag_argument_type 일치 검사
			boolean flag = true;

			loop:
				for(int j = 1; j < command.length; j++) {
					String flag_name = command[j];

					if(!flag_name.startsWith("-")) { // -로 시작하는 명령어가 아닐 경우 
						System.out.println("* error 4 - dash X\n");
						flag = false;
						break loop;
					}

					if(hashMap.containsKey(flag_name)) { // flag_rules에 존재하는 명령어만 처리 
						System.out.print("--falg_name : " + flag_name);

						if(set.contains(flag_name)) { // 3. 각 flag는 0번이나 1번 나타남 - 이미 set에 존재한다면 처리 X 
							System.out.println("* error 3\n");
							flag = false;
							break loop;
						} else {
							set.add(flag_name);
						}

						String flag_type = hashMap.get(flag_name);
						System.out.println(" / flag_type : " + flag_type);

						if(flag_type.equals("NULL")) {
							if(j+1 != command.length && !command[j+1].startsWith("-")) { // 마지막 명령이 아닌데, 다음이 명령어로 시작하지 않는다면 X 
								System.out.println("* error 2 - NULL\n");
								flag = false;
								break;
							}
						} else if(flag_type.equals("STRING")){
							int cnt = typeMatch(command, j, false, true); // isDig = false (문자), isSingle = true (단수)라는 의미 
							if(cnt != -1) {
								j += cnt;
							} else {
								flag = false;
								break;
							}
						} else if(flag_type.equals("STRINGS")) {
							int cnt = typeMatch(command, j, false, false);
							if(cnt != -1) {
								j += cnt;
							} else {
								flag = false;
								break;
							}
						} else if(flag_type.equals("NUMBER")) {
							int cnt = typeMatch(command, j, true, true);
							if(cnt != -1) {
								j += cnt;
							} else {
								flag = false;
								break;
							}
						} else if(flag_type.equals("NUMBERS")) {
							int cnt = typeMatch(command, j, true, false);
							if(cnt != -1) {
								j += cnt;
							} else {
								flag = false;
								break;
							}
						}
					} else { // 4. flag_rules에 존재하는 flag가 아닐 경우 
						System.out.println("* error 4 - not exist flag\n");
						flag = false;
						break loop;
					}
				}

			// 모든 조건을 만족한다면 true 반환 
			if(flag) {
				answer[i] = true;
			}

		}

		return answer;
	}

	public static int typeMatch(String[] command, int j, boolean isDig, boolean isSingle) {
		ArrayList<String> list = new ArrayList<>();
		int cnt = 0;

		for(int k = j+1; k < command.length; k++) {
			if(!command[k].startsWith("-")) {
				list.add(command[k]);
				cnt++;
			} else {
				break;
			}
		}
		
		if(isSingle && cnt > 1) { // 단수인데 1이상의 인자를 가질 경우 X 
			System.out.println("* error 2 - 1 more arguments\n");
			return -1;
		}

		if(cnt == 0) { // 인자가 0개일 경우 
			System.out.println("* error 2 - zero arguments\n");
			return -1;
		} else {
			for(int k = 0; k < list.size(); k++) {
				if(isDigit(list.get(k)) != isDig) { // 숫자, 문자 타입이 다른 경우 
					System.out.println("* error 2 - type mismatch\n");
					return -1;
				}
			}
		}

		return cnt; // 조건을 만족할 경우 인자의 개수 반환 
	}

	public static boolean isDigit(String str) {
		// 숫자면 true, 문자면 false 반환 
		boolean flag = true;
		for(int i = 0; i < str.length(); i++) {
			char tmp = str.charAt(i);

			if(!('0' <= tmp && tmp <= '9')) { // 문자가 0~9 사이가 아닐 경우 
				flag = false;
				break;
			}
		}
		
		return flag;
	}

}
