import java.util.*;
import java.util.regex.Pattern;

public class LINE1_2 {

	public static void main(String[] args) {
//		String inp_str = "AaTa+!12-3";
//		String inp_str = "aaaaZZZZ)";
		String inp_str = "CaCbCgCdC888834A";
//		String inp_str = "UUUUU";
//		String inp_str = "ZzZz9Z824";
		
		ArrayList<Integer> list = solution(inp_str);
		
		if (list.size() == 0) {
			System.out.println(0);
		} else {
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}
	
    public static ArrayList<Integer> solution(String inp_str) {
//        int[] answer = new int[5];
    	ArrayList<Integer> answer = new ArrayList<>();
        
        if (inp_str.length() < 8 || inp_str.length() > 15) {
        	answer.add(1);
        }
        
        int cnt = 0;
        if (Pattern.compile("[^A-Za-z0-9~!@#$%^&*]").matcher(inp_str).find()) {
        	answer.add(2);
        }
        if (Pattern.compile("[A-Za-z0-9~!@#$%^&*]").matcher(inp_str).find()){
        	if (Pattern.compile("[A-Z]").matcher(inp_str).find()) {
        		cnt++;
        	}
        	if (Pattern.compile("[a-z]").matcher(inp_str).find()) {
        		cnt++;
        	}
        	if (Pattern.compile("[0-9*]").matcher(inp_str).find()) {
        		cnt++;
        	}
        	if (Pattern.compile("[~!@#$%^&*]").matcher(inp_str).find()) {
        		cnt++;
        	}
        }
        
        if (cnt < 3) {
        	answer.add(3);
        }
        
        if (Pattern.compile("(.)\\1\\1\\1").matcher(inp_str).find()) {
        	answer.add(4);
        }
        
        if (Pattern.compile("(.)\\1\\1\\1\\1").matcher(inp_str).find()) {
        	answer.add(5);
        }
        
        return answer;
    }

}