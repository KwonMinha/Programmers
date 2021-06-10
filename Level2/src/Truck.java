/**
 * @author Minha Gwon
 * @date 2021. 6. 10.
 * Level2 - 스택/큐 - 트럭 
 * https://programmers.co.kr/learn/courses/30/lessons/42583
 */

import java.util.*;

public class Truck {

	public static void main(String[] args) {
		int[] truck_weights = {7,4,5,6};
		System.out.println(solution(2, 10, truck_weights));

	}
	
	 public static int solution(int bridge_length, int weight, int[] truck_weights) {
	        int answer = 0;
	        
	        Queue<Integer> bridgeQueue = new LinkedList<>();
	        Queue<Integer> readyQueue = new LinkedList<>();
	        
	        for(int i = 0; i < truck_weights.length; i++) {
	        	readyQueue.add(truck_weights[i]);
	        }
	        

	        
	        
	        return answer;
	    }

}
