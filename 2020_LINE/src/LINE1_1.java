public class LINE1_1 {

	public static void main(String[] args) {
		String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
		String[] languages = {"PYTHON", "C++", "SQL"};
		int[] preference = {7, 5, 5};

		System.out.println(solution(table, languages, preference));
	}

	public static String solution(String[] table, String[] languages, int[] preference) {
		String[][] arr = new String[5][6];

		for(int i = 0; i < table.length; i++) {
			String[] str = table[i].split(" ");

			for(int j = 0; j < 6; j++) {
				arr[i][j] = str[j];
			}
		}

		int max = 0;
		String maxString = "";

		for(int j = 0; j < 5; j++) {
			int result = 0;

			for(int i = 0; i < languages.length; i++) {
				String lang = languages[i];
				int pre = preference[i];

				for(int k = 1; k < 6; k++) {
					if(arr[j][k].equals(lang)) {
						result += (5-k) * pre;
					}
				}
			}

			if(max < result) {
				max = result;
				maxString = arr[j][0];
			} else if(max == result) {
				if(maxString.compareTo(arr[j][0]) >= 1) {
					maxString = arr[j][0];
				}
			}
		}

		return maxString;
	}

}
