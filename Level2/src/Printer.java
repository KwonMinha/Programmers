/**
 * @author Minha Gwon
 * @date 2021. 6. 9.
 * Level2 - 스택/큐 - 프린터 
 * https://programmers.co.kr/learn/courses/30/lessons/42587
 */

import java.util.*;

public class Printer {

	public static void main(String[] args) {
		int[] priorities = {2, 1, 3, 2};
		int location = 2;
		System.out.println(solution(priorities, location));
	}
	
	public static int solution(int[] priorities, int location) {
        Queue<Print> queue = new LinkedList<Print>();
        
        int[] priority = new int[10];
        
        for(int i = 0; i < priorities.length; i++) {
        	priority[priorities[i]] += 1;
        	queue.add(new Print(i, priorities[i]));
        }
        
        int order = 0;
        for(int i = 9; i >= 1; i--) { // 우선순위 높은 것 부터 시작 
        	while(priority[i] != 0) { 
        		Print print = queue.poll();
        		
        		if(print.value == i) { // 현재 우선순위와 같은 순위라면 출력 
        			order++;
        			priority[i] -= 1;
        			
        			if(print.idx == location) { // 출력 문서가 찾는 문서라면 그때의 순서 리턴 
        				return order;
        			}
        		} else { 
        			queue.add(print); // 현재 우선순위보다 낮다면 맨뒤로 
        		}
        	}
        }
        
        return 0;
    }

}

class Print {
	int idx;
	int value;
	
	Print(int idx, int value) {
		this.idx = idx;
		this.value = value;
	}
}
