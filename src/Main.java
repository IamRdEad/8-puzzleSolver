import java.util.*;
public class Main {
	static Scanner reader = new Scanner(System.in);
	public static void main(String[] args) {
		
		int[][] board = new int[3][3];
		System.out.println("Welcome to 8 tiles game please.");
		System.out.println("enter one series of numbers to build a new board");
		System.out.println("0 will represent a space");
		int[] numbers = input(); 
		for(int i = 0; i < 9; i++) {
			board[i/3][i%3] = numbers[i];
		}
		PuzzleSolverBFS BFS = new PuzzleSolverBFS(board);
		BFS.solve();
		int maxDepth = 20;
		PuzzleSolverIDDFS IDDFS = new PuzzleSolverIDDFS(board, maxDepth);
		IDDFS.solve();
		PuzzleSolverGBFS GBFS = new PuzzleSolverGBFS(board);
		GBFS.solve();
		PuzzleSolverAstar Astar = new PuzzleSolverAstar(board);
		Astar.solve();
	}
	
	private static int[] input() {
		String input;
		int[] board = new int[9]; 
		do {
			try {
				input = reader.nextLine();
				String[] numbers = input.split(" ");
				if(numbers.length < 9)
					throw new Exception();
				for(int i = 0; i < board.length; i++) {
					board[i] = Integer.parseInt(numbers[i]);
				}
				return board;
			}catch(Exception e) {
				System.out.println("your input is Invalid please enter again ");
			}
		}while(true); 
	}

	private static void game(int[][] board) {
		int algo = 0;
		System.out.println("Please chose one of the following algorithmes:");
		System.out.println("1. BFS\n2.IDDFS (you will be asked for max depth)\n3.GBFS\n4.A*");
		algo = reader.nextInt();
		while(algo < 1 || algo > 4) {
			System.out.print("Invalid option try again: ");
			algo = reader.nextInt();
		}
		switch (algo) {
		case 1: 
			PuzzleSolverBFS BFS = new PuzzleSolverBFS(board);
			BFS.solve();
			break;
		case 2:
			System.out.println("Enter max Depth for IIDFS");
			int maxDepth = reader.nextInt();
			PuzzleSolverIDDFS IDDFS = new PuzzleSolverIDDFS(board, maxDepth);
			IDDFS.solve();
			break;
		case 3:
			PuzzleSolverGBFS GBFS = new PuzzleSolverGBFS(board);
			GBFS.solve();
			break;
		case 4:
			PuzzleSolverAstar Astar = new PuzzleSolverAstar(board);
			Astar.solve();
			break;
		}
	}
}
