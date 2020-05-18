/**
 * @author Minha Gwon
 * @date 2020. 5. 18.
 * 단어 변환 (Level 3)
 * https://programmers.co.kr/learn/courses/30/lessons/43163
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class WordConversion {
	static int answer = 0;

	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

		System.out.println(solution(begin, target, words));
	}

	public static int solution(String begin, String target, String[] words) {
		if(Arrays.asList(words).contains(target)) {
			LinkedList<Integer>[] adjList = new LinkedList[words.length+1];
			int targetNum = 0;
			targetNum = makeAdjGraph(begin, target, words, adjList, targetNum);

			int[] count = new int[words.length+1];
			Arrays.fill(count, -1);
			bfs(adjList, targetNum, count);
		}
		
		return answer;
	}

	public static int makeAdjGraph(String begin, String target, String[] words, LinkedList<Integer>[] adjList, int targetNum) {
		String temp = begin;

		for(int i = 0; i < words.length+1; i++) {
			adjList[i] = new LinkedList<Integer>();

			for(int j = 0; j < words.length; j++) {
				int cnt = 0;

				for(int k = 0; k < words[j].length(); k++) {
					if(temp.charAt(k) != words[j].charAt(k))
						cnt++;
				}
				if(cnt == 1) 
					adjList[i].add(j+1);
			}
			if(i < words.length) {
				temp = words[i];
				if(target.equals(words[i])) 
					targetNum = i+1;
			}
			
		} 
		
		/*
		//LinkedList 출력
		for(int i = 0; i < adjList.length; i++ ) {
			System.out.print("i : " + i + "     ");
			for(int j = 0; j < adjList[i].size(); j++) {
				System.out.print(adjList[i].get(j) + " ");
			}
			System.out.println();
		}
		*/
 
		return targetNum;
	}

	public static void bfs(LinkedList<Integer>[] list, int targetNum, int[] count) {
		Queue<Integer> q = new LinkedList<Integer>();
		int v = 0;
		count[v] = 0;
		q.add(v);

		while(!q.isEmpty()) { 
			v = q.poll(); 

			Iterator<Integer> iter = list[v].listIterator();
			while(iter.hasNext()) { 
				int next = iter.next(); 
				if(count[next] == -1) { 
					count[next] = count[v] + 1;
					q.add(next);
				} 
				if(next == targetNum) {
					answer = count[next];
					break;
				}
			}
		}
	}
}


