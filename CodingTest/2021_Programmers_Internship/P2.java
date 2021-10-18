import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class P2 {
	
	public static void main(String[] args) {

		
		int[] t = {0,1,3,0};
		int[] r = {0,1,2,3};
		int[] ans = solution(t, r);
		
		System.out.println(Arrays.toString(ans));
	}
	
	 public static int[] solution(int[] t, int[] r) {
	     
//	        PriorityQueue<Customer> pq = new PriorityQueue<Customer>(new Comparator<Customer>() {
//				@Override
//				public int compare(Customer o1, Customer o2) {
//					
//					return 0;
//				}  
//				     
//			}); 
	        
	        LinkedList<Customer> list = new LinkedList<>();
	        
	        for(int i = 0; i < t.length; i++) {
	        	list.add(new Customer(r[i], t[i], i));
	        }
	        
	        Collections.sort(list);
	        
	        for(int i = 0; i < list.size(); i++) {
	        	System.out.println("time :  " + list.get(i).time + ", ticket : " + list.get(i).ticket + ", id : " + list.get(i).id);
	        }
	        
	        int maxTime = list.get(list.size()-1).time;
	        for(int i = 0; i < maxTime; i++) {
	        	ArrayList<Customer> tempList = new ArrayList<>();
	        	
//	        	for()
	        }
	  
	        
	        int[] answer = new int[list.size()];
	        for(int i = 0; i < answer.length; i++) {
	        	answer[i] = list.get(i).id;
	        }
	        		
	        
	        return answer;
	    }
}

class Customer implements Comparable<Customer> {
	int ticket;
	int time;
	int id;
	
	Customer(int ticket, int time, int id) {
		this.ticket = ticket;
		this.time = time;
		this.id = id;
	}

	@Override
	public int compareTo(Customer o) {
//		if(o.ticket == this.ticket) {
//			if(o.time == this.time) {
//				return o.id - this.id;
//			} else {
//				return o.time - this.time;
//			}
//		} else {
//			return o.ticket - this.ticket;
//		}
		
		return this.time - o.time;
	}
}
