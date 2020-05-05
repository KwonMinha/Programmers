/**
 * @author Minha Gwon
 * @date 2020. 5. 6.
 * 튜플
 * https://programmers.co.kr/learn/courses/30/lessons/64065
 * 
 * s	                             result
  "{{2},{2,1},{2,1,3},{2,1,3,4}}"	[2, 1, 3, 4]
  "{{1,2,3},{2,1},{1,2,4,3},{2}}"	[2, 1, 3, 4]
  "{{20,111},{111}}"	[111, 20]
  "{{123}}"	[123]
  "{{4,2,3},{3},{2,3,4,1},{2,3}}"	[3, 2, 4, 1]
 */

import java.util.*;
import java.util.stream.Collectors;

public class Tuple {

	public static void main(String[] args) {
		String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
		
		int[] answer;

		//집합 자르기
		String[] stringArr = s.split("},");
		ArrayList<ArrayList<Integer>> aList = new ArrayList<ArrayList<Integer>>(stringArr.length);
		answer = new int[stringArr.length];


		for(int i = 0; i < stringArr.length; i++) {
			//중괄호 없애기
			stringArr[i] = stringArr[i].replace("{", "");
			stringArr[i] = stringArr[i].replace("}", "");

			//원소 자르기
			int[] intArray = Arrays.stream(stringArr[i].split(","))
					.mapToInt(Integer::parseInt)
					.toArray();

			//집합별 원소 ArrayList에 넣어주기
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int j = 0; j < intArray.length; j++) {
				list.add(intArray[j]);
			}

			aList.add(list);
		}

		//집합 길이 순 정렬
		Collections.sort(aList, new Comparator<ArrayList>(){
			public int compare(ArrayList a1, ArrayList a2) {
				return  a1.size() - a2.size();
			}
		});

		//차집합을 구해서 튜플의 K번째 원소 찾기
		answer[0] = aList.get(0).get(0);
		for(int i = 1; i < aList.size(); i++) {
			ArrayList<Integer> list1 = aList.get(i);
			ArrayList<Integer> list2 = aList.get(i-1);

			List<Integer> diff = list1.stream()
					.filter(e -> !list2.contains(e))
					.collect (Collectors.toList());

			answer[i] = diff.get(0);
		}

		for(int i = 0; i < answer.length; i++) {
			System.out.print(answer[i] + " ");
		}

		// //ArrayList 출력
		// for (int i = 0; i < aList.size(); i++) { 
		//     for (int j = 0; j < aList.get(i).size(); j++) { 
		//         System.out.print(aList.get(i).get(j) + " "); 
		//     } 
		//     System.out.println(); 
		// } 

	}



}
