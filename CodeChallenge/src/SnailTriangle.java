/**
 * @author Minha Gwon
 * @date 2020. 9. 22.
 * 월간 코드 챌린지 시즌1 - 삼각 달팽이 
 * https://programmers.co.kr/learn/courses/30/lessons/68645
 * BLOG - https://minhamina.tistory.com/58
 */

import java.util.Arrays;

public class SnailTriangle {

	public static void main(String[] args) {
		int n = 4;
		System.out.println(Arrays.toString(solution(n)));
	}
	
	public static int[] solution(int n) {
        int[] answer = new int[(n*(n+1))/2];
        int[][] matrix = new int[n][n];

        int x = -1, y = 0;
        int num = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) { 	
                if (i % 3 == 0) {
                    x++;
                } else if (i % 3 == 1) {
                    y++;
                } else if (i % 3 == 2) {
                    x--;
                    y--;
                }
                matrix[x][y] = num++;
            }
        }
        
        int k = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) 
                	break;
                answer[k++] = matrix[i][j];
            }
        }

        return answer;
    }

}
