/**
 * @author Minha Gwon
 * @date 2021. 6. 9.
 * Level2 - 스택/큐 - 기능 개발 
 * https://programmers.co.kr/learn/courses/30/lessons/42586
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FunctionDevelopment {

	public static void main(String[] args) {
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		int[] ans = solution(progresses, speeds);
		System.out.println(Arrays.toString(ans));
	}
	
	 public static int[] solution(int[] progresses, int[] speeds) {
	        Queue<Integer> queue = new LinkedList<>();
	        
	        for(int i = 0; i < progresses.length; i++) {
	        
	        	int remain = (100 - progresses[i]);
	        	int day = remain / speeds[i];
	        	if(remain % speeds[i] != 0) 
	        		day += 1;
	        	
	        	queue.add(day);
	        }
	        
	        ArrayList<Integer> result = new ArrayList<>();
	        
	        while(!queue.isEmpty()) {
	        	int cnt = 1;
	        	int d = queue.poll();
	        	
	        	while(!queue.isEmpty() && queue.peek() <= d) {
	        		queue.poll();
	        		cnt++;
	        	}
	        	
	        	result.add(cnt);
	        }
	        
	        int[] answer = result.stream().mapToInt(i -> i).toArray();
	        
	        return answer;
	    }

}
