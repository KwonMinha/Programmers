/**
 * @author Minha Gwon
 * @date 2021. 2. 3.
 * 
 * 2021 카카오 신입 공채 - 메뉴 리뉴얼 
 * https://programmers.co.kr/learn/courses/30/lessons/72411
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MenuRenewal {
	public static HashMap<String, Integer> courseMap;
	public static int max;

	public static void main(String[] args) {
			
//		{"XYZ", "XWY", "WXA"}	{2,3,4}
		
//		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
//		int[] course = {2,3,4};
		
		String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
		int[] course = {2,3,5};

		System.out.println(Arrays.toString(solution(orders, course)));
	}
	
	public static String[] solution(String[] orders, int[] course) {
        HashMap<Character, Integer> menuMap = new HashMap<>();
        for(int i = 0; i < orders.length; i++) {
        	for(int j = 0; j < orders[i].length(); j++) 
        		menuMap.put(orders[i].charAt(j), menuMap.getOrDefault(orders[i].charAt(j), 0) + 1);
        }
        
        ArrayList<Character> menuList = new ArrayList<>();
        for (char key : menuMap.keySet()) 
        	menuList.add(key);
        boolean[] visited = new boolean[menuList.size()];
    	ArrayList<String> resultList = new ArrayList<>();
    	
        for(int i = 0; i < course.length; i++) {
        	courseMap = new HashMap<>();
        	max = Integer.MIN_VALUE;
            
        	combination(menuList, visited, 0, course[i], orders);

            for(String key : courseMap.keySet()) {
            	if(courseMap.get(key) == max) // 최대 주문수와 같은 값을 가진 메뉴만 코스에 추가 
            		resultList.add(key);
            }
        }
        
        Collections.sort(resultList);
        String[] answer = resultList.toArray(new String[resultList.size()]);

        return answer;
    }
	
	// 메뉴 조합 뽑기 
	public static void combination(ArrayList<Character> list, boolean[] visited, int start, int r, String[] orders) {
		if(r == 0) {
			String comb = "";
			for(int i = 0; i < list.size(); i++) {
				if(visited[i]) 
					comb += list.get(i);
			}
			
			makeCourse(comb, orders);
			
			return;
		} else {
			for(int i = start; i < list.size(); i++) {
				visited[i] = true;
				combination(list, visited, i+1, r-1, orders);
				visited[i] = false;
			}
		}
	}
	
	// 뽑힌 메뉴가 코스 요리 메뉴가 될지 안될지 확인 
	public static void makeCourse(String comb, String[] orders) {
		int cnt = 0;
		int customerCnt = 0;
		
		for(int i = 0; i < orders.length; i++) { // 뽑힌 메뉴가 각 주문에 얼마나 포함되는지 확인 
			boolean flag = true;
			for(int j = 0; j < comb.length(); j++) {
				if(!orders[i].contains(comb.charAt(j)+"")) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				customerCnt++;
				cnt++;
			}
		}
		
		if(cnt >= max && customerCnt >= 2) { // 기존의 max값 보다 같거나 크고, 2명 이상의 손님에게서 주문된 메뉴라면 코스에 추가 
			max = cnt;
			courseMap.put(comb, cnt);
		}
	}
	
}
