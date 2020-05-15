import java.util.LinkedList;

public class WordConversion {

	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		
		solution(begin, target, words);
	}
	
    public static int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        boolean[] v = new boolean[words.length];
        
        bfs(begin, target, words, v);
        
        return answer;
    }
    
    public static void makeAdjList(String begin, String[] words) {
    	LinkedList<Integer>[] adjList = new LinkedList[words.length];
    	
    	for(int i = 0; i < words.length; i++) {
    		adjList[i] = new LinkedList<Integer>();
    		
    		for(int j = 0; j < words.length; j++) {
    			String s = words[j];
    			int cnt = 0;
    			for(int k = 0; k < s.length(); k++) {
    				if(s.charAt(k) == begin.charAt(k)) {
    					cnt++;
    				}
    			}
    			if(cnt == 1) {
    		
    			}
    		}
    	}
    }
    
    public static void bfs(String begin, String target, String[] words, boolean[] v) {

	}

}
