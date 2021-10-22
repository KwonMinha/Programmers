import java.util.ArrayList;

public class LINE1_3 {

	public static void main(String[] args) {
//		int[] enter = {1, 3, 2}; 
//		int[] leave = {1, 2, 3};

		int[] enter =  {1, 4, 2, 3};
		int[] leave = {2, 1, 3, 4};
		
//		int[] enter = {3, 2, 1}; 
//		int[] leave = {2, 1, 3};

//		int[] enter = {3, 2, 1}; 
//		int[] leave = {1, 3, 2};
		
		
		
		int[] arr = solution(enter, leave);

		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static int[] solution(int[] enter, int[] leave) {
		ArrayList<Node> leaveList = new ArrayList<>();
		//ArrayList<Node> enterList = new ArrayList<>();

		int[] result = new int[enter.length];
		
		int[] startIndex = new int[enter.length + 1];
		int[] endIndex = new int[enter.length + 1];
		
		for(int i = 0; i < enter.length; i++) {
			//enterList.add(new Node(i, enter[i]));
			
			startIndex[enter[i]] = i;
			endIndex[leave[i]] = i;
			
			//leaveList.add(new Node(startIndex[leave[i]], leave[i]));
		}
		
		for(int i = 0; i < enter.length; i++) {
			//enterList.add(new Node(i, enter[i]));
			
			//startIndex[enter[i]] = i;
			
			leaveList.add(new Node(startIndex[leave[i]], leave[i], endIndex[leave[i]]));
		}
		
	
		for(int i = 0; i < enter.length; i++) {
			int curNum = enter[i];
			int curIdx = i;

			System.out.println("**** cur : " + curNum);
			for(int j = 0; j < leaveList.size(); j++) {
				int leaveNum = leaveList.get(j).num;
				int leaveIdx = leaveList.get(j).idx;
				int leaveEndIdx = leaveList.get(j).endIdx;

				System.out.println("leave : " + leaveNum + " , leaveID : " + leaveIdx + " end: " + leaveEndIdx);

				if(leaveNum == curNum) {
					break;
				}

				if(curIdx <= j) {
					System.out.println("ok : " + curIdx);
					result[curNum-1] += 1;
					result[leaveNum-1] += 1;
				} else if(curIdx > j) {
					//System.out.println("??? : " + curIdx + " lID : " + leaveIdx);
					
					
					if(curIdx > endIndex[curNum] && curIdx < leaveIdx) {
						System.out.println("smaller");
						result[curNum-1] += 1;
						result[leaveNum-1] += 1;
					} 
				}
			}
			System.out.println();
		}
		
		return result;
	}

}

class Node {
	int idx;
	int num;
	int endIdx;

	Node(int idx, int num, int endIdx) {
		this.idx = idx;
		this.num = num;
		this.endIdx = endIdx;
	}
}