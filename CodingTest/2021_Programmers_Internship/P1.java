import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class P1 {

	public static void main(String[] args) {

		 String[] date = {"price=80 code=987654 time=2019062113","price=90 code=012345 time=2019062014","price=120 code=987654 time=2019062010","price=110 code=012345 time=2019062009","price=95 code=012345 time=2019062111"};
		 int[] arr = solution("012345", "20190620", date);

		
		System.out.println(Arrays.toString(arr));
	}
	

	public static int[] solution(String code, String day, String[] data) {
        ArrayList<Node> list = new ArrayList<>();
        
        for(int i = 0; i < data.length; i++) {
        	String[] arr = data[i].split(" ");
        	String price = arr[0].substring(6, arr[0].length());
        	String company = arr[1].substring(5, 11);
        	
        	String allDay = arr[2].substring(5, 15);
        	String d = allDay.substring(0, 8);
        	int hour = Integer.parseInt(allDay.substring(8, 10));
        	
        	if(code.equals(company) && day.equals(d)) {
        		list.add(new Node(Integer.parseInt(price), hour));
        	}
        }
        
        Collections.sort(list);
        
        int[] ans = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
        	ans[i] = list.get(i).price;
        }
        
        return ans;
    }

}

class Node implements Comparable<Node> {
	int price;
	int hour;
	
	Node(int price, int hour) {
		this.price = price;
		this.hour = hour;
	}

	@Override
	public int compareTo(Node o) {
		return this.hour - o.hour;
	}
}
