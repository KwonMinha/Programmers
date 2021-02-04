/**
 * @author Minha Gwon
 * @date 2021. 2. 3.
 * 
 * 2021 카카오 신입 공채 - 합승 택시 요금 
 * https://programmers.co.kr/learn/courses/30/lessons/72413
 */

import java.util.*;

public class TaxiFare {
	public static int[][] map;
	public static int[] count;
	public static int N;

	public static void main(String[] args) {
//		7	3	4	1	{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}	
//		6	4	5	6	{{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}	
		
		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		//int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}	;
		
		System.out.println(solution(7, 3, 4, 1, fares));
	}
	
	 public static int solution(int n, int s, int a, int b, int[][] fares) {
	        int answer = Integer.MAX_VALUE;
	        
	        N = n;
	        map = new int[n+1][n+1];
	        for(int i = 0; i < fares.length; i++) {
	        	int[] fare = fares[i];
	        	map[fare[0]][fare[1]] = fare[2];
	        	map[fare[1]][fare[0]] = fare[2];
	        }
	        
	        for(int i = 1; i <= N; i++) {
	        	for(int j = 1; j <= N; j++)  {
	        		System.out.print(map[i][j] + " ");
	        	}
	        	System.out.println();
	        }
	        System.out.println();
	        

	        count = new int[n+1];
	        
	        
	        // 합승 안하고 각자 이동할 경우 
	        System.out.println("합승 안함 ");
	        answer = Math.min(answer, moveAB(s, a, b));
	      
	        System.out.println("합승 안함 요금은 X ??? : " + answer);
	        
	        // 합승 
	        for(int i = 1; i <= N; i++) {
	        	count = new int[n+1];
		        Arrays.fill(count, -1);
		        
		        if(i != s) {
		    
		        	System.out.println("합승 "  +s + "부터 "+ i + "까지 ");
		        	bfs(s, i);
		        	printCount();
		        	
		        	int carPool = count[i];
		        	if(carPool != -1)
		        		answer = Math.min(answer, (carPool+moveAB(i, a, b)));
		        	
		        	
		        	System.out.println("합승 요금은 ??? : " + (carPool+moveAB(i, a, b)));
		        }
	        }
	        
	        return answer;
	    }
	 
	 public static int moveAB(int s, int a, int b) {
		 int fare = 0;
		 
		 if(s != a) {
			 System.out.println("a count : " + a);
			 Arrays.fill(count, -1);
			 bfs(s, a);
			 fare += count[a];
			 printCount();
		 }
	
		 if(s != b) {
			 System.out.println("b count : " + b);
			 Arrays.fill(count, -1);
			 bfs(s, b);
			 fare += count[b];
			 printCount();
		 }
		 
		 
		 return fare;
	 }
	 
	 public static void bfs(int start, int end) {
		 Queue<Integer> queue = new LinkedList<>();
		 queue.add(start);
		 count[start] = 0;
		 
		 while(!queue.isEmpty()) {
			 int v = queue.poll();
			 
			 for(int i = 1; i <= N; i++) {
				 if(map[v][i] != 0) {
					 if(count[i] == -1) {
						 queue.add(i);
						 count[i] = count[v] + map[v][i];
					 } else if(count[i] >= (count[v] + map[v][i])){
						 queue.add(i);
						 count[i] = count[v] + map[v][i];
					 }
					 
				 }
			 }
		 }
	 }
	 
	 public static void printCount() {
		 for(int i = 1; i <= N; i++) {
			 System.out.println("i : " + i + ", count : " + count[i]);
		 }
		 
		 System.out.println();
	 }

}
