/**
 * @author Minha Gwon
 * @date 2020. 5. 19.
 * 여행 경로(Level 3)
 * https://programmers.co.kr/learn/courses/30/lessons/43164
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;
import java.util.TreeSet;

public class TravelRoute {
	public static ArrayList<String> list = new ArrayList<String>();
	public static boolean[] visited;
	public static String str = "";
	
	public static void main(String[] args) {
		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
		//{{"ICN", "BOO"}, {"ICN", "COO"}, {"COO", "DOO"}, {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"}, {"BOO", "ICN"}, {"COO", "BOO"}}
		//{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}}
		//{{"ICN", "SFO"}, {"SFO", "ICN"}, {"ICN", "SFO"}, {"SFO", "QRE"}}
		//{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}}
		
		System.out.println(Arrays.deepToString(solution(tickets)));
	}

	public static String[] solution(String[][] tickets) {
		for(int i = 0; i < tickets.length; i++) {
			visited = new boolean[tickets.length];
			String start = tickets[i][0];
			String end = tickets[i][1];
			
			if(start.equals("ICN")) {
				str = start + ",";
				visited[i] = true;
				dfs(tickets, end, 1);
			}
		}
		
		Collections.sort(list);
		String[] answer = list.get(0).split(",");
		
		return answer;
	}
	
	public static void dfs(String[][] tickets, String end, int count) {
		str += end + ",";
		
		if(count == tickets.length) {
			list.add(str);
			return;
		}
		
		for(int i = 0; i < tickets.length; i++) {
			if(tickets[i][0].equals(end) && !visited[i]) {
				visited[i] = true;
				dfs(tickets, tickets[i][1], count+1);
				visited[i] = false;
				str = str.substring(0, str.length()-4);
			}
		}
	}
	
}
