/**
 * @author Minha Gwon
 * @date 2021. 1. 11.
 * 
 * 2019 카카오 신입 공채 - 길 찾기 게임   
 * https://programmers.co.kr/learn/courses/30/lessons/42892
 */

public class DirectionsGame {
	public static Node root;
	public static int maxX, maxY;

	public static void main(String[] args) {
		int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
		solution(nodeinfo);
	}

	public static int[][] solution(int[][] nodeinfo) {
		int[][] answer = {};

		maxX = 0; //13
		maxY = 0; //6
		for(int i = 0; i < nodeinfo.length; i++) {
			maxX = Math.max(maxX, nodeinfo[i][0]);
			maxY = Math.max(maxY, nodeinfo[i][1]);
		}

		int[][] map = new int[maxX+1][maxY+1];
		for(int i = 0; i < nodeinfo.length; i++) {
			map[nodeinfo[i][0]][nodeinfo[i][1]] = i+1;
		}

		//		for(int i = 0; i < map.length; i++) {
		//			for(int j = 0; j < map[i].length; j++) {
		//				System.out.print(map[i][j] + " ");
		//			}
		//			System.out.println();
		//		}

		DirectionsGame dg = new DirectionsGame();
		int rootX = 0;
		int rootY = 0;

		// root 노드 찾기 
		loop:
			for(int i = maxY; i >= 0; i--) {
				//System.out.println("1. i : " + i);
				for(int j = 0; j <= maxX; j++) {
					//System.out.println("2. j : " + j);
					if(map[j][i] != 0) {
						//System.out.println(map[j][i]);
						dg.root = new Node(map[j][i], rootX, rootY);
						root.parent = null;
						root.x = j;
						root.y = i;
					
						//System.out.println(rootX + " " + rootY);
						break loop;
					}
				}
			}

		search(root, map, root.x, root.y);
		//searchLeft(root, map, rootX, rootY);
		
		preOrder(root);
		System.out.println();
		postOrder(root);
		System.out.println();
		inOrder(root);
		System.out.println();
		return answer;
	}

	public static void search(Node node, int[][] map, int x, int y) {
		System.out.println();
		Node leftNode = searchLeft(node, map, x, y);
		
		System.out.println();
		Node rightNode = searchRight(node, map, x, y);
		
		if(leftNode != null) {
			search(leftNode, map, leftNode.x, leftNode.y);
		}
		
		if(rightNode != null) {
			search(rightNode, map, rightNode.x, rightNode.y);
		}
		
//		System.out.println("left Start");
//		Node leftNode = searchLeft(node, map, x, y);
//		while (leftNode != null) {
//			leftNode = searchLeft(leftNode, map, leftNode.x, leftNode.y);
//			//System.out.println("left node : " + leftNode.data);
//		}
//		
//		System.out.println("right Start");
//		Node rightNode = searchRight(node, map, x, y);
//		while (rightNode != null) {
//			rightNode = searchRight(rightNode, map, rightNode.x, rightNode.y);
//			//System.out.println("right node : " + rightNode.data);
//		}
	}

	// 왼쪽 자식 노드 찾기 
	public static Node searchLeft(Node node, int[][] map, int x, int y) {
		System.out.println("left");
		if(node == null) {
			System.out.println("left null!");
			return null;
		}
		
		System.out.println("node.data : " + node.data + "index : " + (y-1) + " , " + x);
		
		for(int i = y-1; i >= 0; i--) {
			
			int index = 0;
			if(node.parent != null) 
				System.out.println("****" + node.x + " " + node.parent.x);
			
			if(node.parent != null && node.x > node.parent.x) {
				index = node.parent.x;
				System.out.println("parent : "  + node.parent.data + " " + node.parent.x + " " + node.parent.y);
			} else {
				index = 0;
			}
			
			for(int j = index; j < x; j++) {
				if(map[j][i] != 0) {
					System.out.println(j + " " + i + ", val : " + map[j][i]);
					Node newNode = new Node(map[j][i], j, i);
					node.left = newNode;
					newNode.parent = node;
					return newNode;
				}
			}
		}
		System.out.println("left null!22");
		return null;
	}

	// 오른쪽 자식 노드 찾기 
	public static Node searchRight(Node node, int[][] map, int x, int y) {
		System.out.println("right");
		if(node == null) {
			System.out.println("right null!");
			return null;
		} 
		
		System.out.println("node.data : " + node.data + ", index :");
		//System.out.println("parent : "  + node.parent.data + " " + node.parent.x + " " + node.parent.y);
		for(int i = y-1; i >= 0; i--) {
			
			
			int index = 0;
			if(node.parent != null) {
				index = node.parent.x;
				System.out.println("parent : "  + node.parent.data + " " + node.parent.x + " " + node.parent.y);
			} else {
				index = maxX;
			}
			
			for(int j = x+1; j <= index; j++) {
				if(map[j][i] != 0) {
					System.out.println(j + " " + i + ", val : " + map[j][i]);
					Node newNode = new Node(map[j][i], j, i);
					node.right = newNode;
					newNode.parent = node;
					return newNode;
				}
			}
		}
		System.out.println("right null!22");
		return null;
	}

	//전위순회 Preorder : Root -> Left -> Right
	public static void preOrder(Node node) {
		if(node != null) {
			System.out.print(node.data + " ");
			if(node.left != null) preOrder(node.left);
			if(node.right != null) preOrder(node.right);
		}
	}

	//후위순회 Postorder : Left -> Right -> Root
	public static void postOrder(Node node) {
		if(node != null) {
			if(node.left != null) postOrder(node.left);
			if(node.right != null) postOrder(node.right);
			System.out.print(node.data + " ");
		}
	}
	
	//중위 순회 Inorder : Left -> Root -> Right
	public static void inOrder(Node node) {
		if(node != null) {
			if(node.left != null) inOrder(node.left);
			System.out.print(node.data + " ");
			if(node.right != null) inOrder(node.right);
		}
	}
}

class Node {
	int data;
	int x;
	int y;
	Node left;
	Node right;
	Node parent;

	Node(int data, int x, int y){ 
		this.data = data;
		this.x = x;
		this.y = y;
	}
}
